package xxf.com.service;

import xxf.com.entities.OrgnazationEntity;

public interface OrgService {
    /**
      *@Author:wangping
      *@Despribtion:获取组织数据
      *@Date 2018/4/22 10:42
    */
    String getOrgList(OrgnazationEntity orgnazationEntity, int pageNum,int size);

    /**
      *@Author:wangping
      *@Despribtion:生成组织树
      *@Date 2018/4/22 12:55
    */
    String getOrgTree();
}
