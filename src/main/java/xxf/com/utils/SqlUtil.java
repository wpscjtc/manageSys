package xxf.com.utils;

import java.lang.reflect.Field;

public class SqlUtil {
    public static String getSqlByEentity(Object object,StringBuffer sql){


        StringBuffer condition=new StringBuffer();
        Field[] roleFields = object.getClass().getDeclaredFields();
        for(int i = 0 , len = roleFields.length; i < len; i++) {
            // 对于每个属性，获取属性名
            String varName = roleFields[i].getName();
            try {
                // 获取原来的访问控制权限
                boolean accessFlag = roleFields[i].isAccessible();
                // 修改访问控制权限
                roleFields[i].setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量
                Object o;
                try {
                    o = roleFields[i].get(object);
                    if(o!=null){
                        //拼sql
                        String sqlVarName=varName.substring(0,1).toUpperCase()+varName.substring(1,varName.length());
                        condition.append(" and o.").append(sqlVarName).append("=#{").append(varName).append("}");

                    }
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // 恢复访问控制权限
                roleFields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }
        sql.append(condition);
        return  sql.toString();
    }
}
