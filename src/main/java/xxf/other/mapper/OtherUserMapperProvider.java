package xxf.other.mapper;

import xxf.com.entities.UsersEntity;
import xxf.com.utils.SqlUtil;

public class OtherUserMapperProvider {
    /**
     *@Author:wangping
     *@Despribtion:获取列表用户信息
     *@Date 2018/4/13 11:26
     */
    public String getAllUsers(UsersEntity user){

        StringBuffer sql=new StringBuffer("select o.UserID,o.Name,case when o.Status=1 then '是' else '否' end statusName,o.Grade,orz.name orgNmae from tUser o,lbOrganization orz where orz.ID=o.OrgId");
        return SqlUtil.getSqlByEentity(user,sql);
    }
}
