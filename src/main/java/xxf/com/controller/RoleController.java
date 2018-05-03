package xxf.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xxf.com.config.annotation.Authorization;
import xxf.com.entities.RoleEntity;
import xxf.com.service.RoleService;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    RoleService roleService;
    
    /**
      *@Author:wangping
      *@Despribtion:读取用户
      *@Date 2018/4/20 13:54
    */
    @RequestMapping(value = "/listRoles")
    @Authorization
    public String listRoles(RoleEntity role,int pageNum){
        String result=roleService.listRole(role,pageNum,15);
        return result;
    }

    @RequestMapping(value = "/getRoleByOderId")
    @Authorization
    public String getRoleByOderId(RoleEntity role,int orderId,int pageNum){
        String result=roleService.getRoleByOderId(role,orderId,pageNum,15);
        return result;
    }


}
