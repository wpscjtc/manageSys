package xxf.other.mapper;

import xxf.com.entities.RoleEntity;
import xxf.com.utils.SqlUtil;

public class OtherRoleMapperProvider {
    /**
     *@Author:wangping
     *@Despribtion:获取列表用户信息
     *@Date 2018/4/13 11:26
     */
    public String getAllRoles(RoleEntity role){
        StringBuffer sql=new StringBuffer("SELECT ID, Name,Category, RoleCode FROM lbRole o where 1=1 ");
        return SqlUtil.getSqlByEentity(role,sql);
    }

    public String getRoleByOderId(RoleEntity role){
        StringBuffer sql=new StringBuffer("select o.ID,o.Type,o.Name,o.Category,o.RoleCode from lbrole o,lbOrganization orz,lbMember m where o.ID=m.RoleID and m.OrgID=orz.ID and m.OrgID= "+role.getMorgID());
        role.setMorgID(null);
        String sqlT=SqlUtil.getSqlByEentity(role,sql)+" group by o.ID,o.Type,o.Name,o.Category,o.RoleCode";
        return sqlT;
    }

}
