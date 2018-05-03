package xxf.other.mapper;

import xxf.com.entities.OrgnazationEntity;
import xxf.com.utils.SqlUtil;

public class OtherOrgMapperProvider {
    /**
     *@Author:wangping
     *@Despribtion:获取列表用户信息
     *@Date 2018/4/13 11:26
     */
    public String getOrgList(OrgnazationEntity orgnazationEntity){
        StringBuffer sql=new StringBuffer("SELECT  ID,FID,Grade,Type,OrgType,OrgCode,Name,Describe,FDNCode,BS,FState,StopDate,PerNum from lbOrganization o where 1=1 ");
        return SqlUtil.getSqlByEentity(orgnazationEntity,sql);
    }


}
