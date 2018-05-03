package xxf.com.service;

import xxf.com.entities.UsersEntity;

public interface LoginService {

    String loginForIm(Integer userId);

    /**
      *@Author:wangping
      *@Despribtion:用户登录
      *@Date 2018/4/13 11:13
    */
    String login(UsersEntity user);

    /**
      *@Author:wangping
      *@Despribtion:登出
      *@Date 2018/4/19 14:40
    */
    String logout(String loginToken);

}
