package xxf.com.utils;

public class XxfUtils {
    /**
      *@Author:wangping
      *@Despribtion:判断多个参数是否为空
      *@Date 2018/5/2 9:13
    */
    public static boolean IsNull(Object... params){
        for (Object param:
             params) {
            if(param==null){
                return true;
            }
        }
        return false;
    }
}
