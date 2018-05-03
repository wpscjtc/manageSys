package xxf.com.service;

import xxf.com.entities.UsersEntity;

public interface UserService {

    /**
     *@Author:wangping
     *@Despribtion:读取用户
     *@Date 2018/4/12 9:49
     */
    String listUsers(UsersEntity user, int pageNum, int pageSize);
}
