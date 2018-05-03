package xxf.com.mapper;

import xxf.com.entities.AuthEntity;
import xxf.com.utils.SqlUtil;

public class OtherAuthMapperProvider {
    /**
     *@Author:wangping
     *@Despribtion:获取列表用户信息
     *@Date 2018/4/13 11:26
     */
    public String getAllAuths(AuthEntity authEntity){
        StringBuffer sql=new StringBuffer("SELECT AuthID,AuthName,AuthDesc,AuthType,AuthWay FROM TAUTH where 1=1 ");
        return SqlUtil.getSqlByEentity(authEntity,sql);
    }
    public String delAuth(String authIds){
        StringBuffer sql=new StringBuffer("delete from TAUTH where AuthId in ("+authIds+") ");
        return sql.toString();
    }

    public String delRoleAuthByAuthId(String authIds){
        StringBuffer sql=new StringBuffer("delete from RROLEAUTH where authId in ("+authIds+") ");
        return sql.toString();
    }

    public String getAuthByRoleIds(String roleIds,Integer orgId){
        StringBuffer sql=new StringBuffer("select TAUTH.AUTHID,TAUTH.AuthName,TAUTH.AuthWay,TAUTH.authMethod,TAUTH.authType from TAUTH,RROLEAUTH where TAUTH.AuthID=RROLEAUTH.AuthId and RROLEAUTH.RoleId in ("+roleIds+") and RROLEAUTH.OrgId="+orgId);
        return sql.toString();
    }

    public String getAuthByRoleId(int roleId,int orgId){
        StringBuffer sql=new StringBuffer("SELECT AUTHID,AuthName,AuthWay,authMethod, CASE WHEN ta.AuthId in (SELECT RR.AuthId FROM DBO.RROLEAUTH RR WHERE  RR.RoleId="+roleId+" and RR.orgId="+orgId+")  THEN 1 ELSE 0 END ISCHECK FROM TAUTH ta");
        return sql.toString();
    }


}
