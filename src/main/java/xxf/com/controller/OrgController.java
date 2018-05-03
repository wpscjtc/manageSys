package xxf.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xxf.com.config.annotation.Authorization;
import xxf.com.entities.OrgnazationEntity;
import xxf.com.service.OrgService;

@RestController
@RequestMapping("/org")
public class OrgController {

    @Autowired
    OrgService orgService;

    /**
      *@Author:wangping
      *@Despribtion:获取组织
      *@Date 2018/4/22 10:58
    */
    @Authorization
    @RequestMapping("/getAllOrg")
    public String getAllOrg(OrgnazationEntity orgnazationEntity,int pageNum){

        String result=orgService.getOrgList(orgnazationEntity,pageNum,15);
        return result;
    }

    @Authorization
    @RequestMapping("/orgTree")
    public String getOrgTree(){

        String result=orgService.getOrgTree();
        return result;
    }
}
