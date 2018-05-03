package xxf.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xxf.com.config.annotation.Authorization;
import xxf.com.entities.UsersEntity;
import xxf.com.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;
    
    /**
      *@Author:wangping
      *@Despribtion:读取用户
      *@Date 2018/4/20 13:54
    */
    @RequestMapping(value = "/listUsers")
    @Authorization
    public String listUsers(UsersEntity user,int pageNum){
        String result=userService.listUsers(user,pageNum,15);
        return result;
    }


}
