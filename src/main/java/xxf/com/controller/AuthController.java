package xxf.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xxf.com.config.annotation.Authorization;
import xxf.com.entities.AuthEntity;
import xxf.com.service.AuthService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    
    /**
      *@Author:wangping
      *@Despribtion:读取用户
      *@Date 2018/4/20 13:54
    */
    @RequestMapping(value = "/listAuths")
    @Authorization
    public String listAuths(AuthEntity auth,int pageNum){
        String result=authService.listAuth(auth,pageNum,15);
        return result;
    }
    /**
      *@Author:wangping
      *@Despribtion:根据角色id获取所有权限
      *@Date 2018/4/23 15:41
    */
    @RequestMapping(value = "/getAuthByRoleId")
    @Authorization
    public String getAuthByRoleId(int roleId,int orgId) {
        String result=authService.getAuthByRoleId(roleId,orgId);
        return result;
    }

    /**
      *@Author:wangping
      *@Despribtion:分配权限给角色
      *@Date 2018/4/23 17:06
    */
    @RequestMapping(value = "/chanegRoleAuth")
    @Authorization
    public String chanegRoleAuth(int roleId,int authId,int orgId) {
        String result=authService.chanegRoleAuth(roleId,authId,orgId);
        return result;
    }
    /**
      *@Author:wangping
      *@Despribtion:增加权限
      *@Date 2018/4/25 9:13
    */
    @RequestMapping(value = "/addAuth")
    @Authorization
    public String addAuth(AuthEntity auth){
        String result=authService.addAuth(auth);
        return result;
    }

    /**
     *@Author:wangping
     *@Despribtion:修改权限
     *@Date 2018/4/25 9:13
     */
    @RequestMapping(value = "/updateAuth")
    @Authorization
    public String updateAuth(AuthEntity auth){
        String result=authService.updateAuth(auth);
        return result;
    }

    /**
     *@Author:wangping
     *@Despribtion:删除权限
     *@Date 2018/4/25 9:13
     */
    @RequestMapping(value = "/del")
    @Authorization
    public String del(String authIds){
        String result=authService.delAuth(authIds);
        return result;
    }

    @RequestMapping(value = "/getAuthById")
    @Authorization
    public String getAuthById(int authId) {
        String result=authService.getAuthById(authId);
        return result;
    }

}
