package xxf.com.service;

import xxf.com.entities.AuthEntity;

public interface AuthService {

    /**
     *@Author:wangping
     *@Despribtion:读取权限
     *@Date 2018/4/12 9:49
     */
    String listAuth(AuthEntity role, int pageNum, int pageSize);
    String getAuthByRoleId(int roleId,int orgId);

    String getAuthById(int authId);

    String addRoleAuth(int roleId, int authId, int orgId);

    String chanegRoleAuth(int roleId, int authId, int orgId);

    String addAuth(AuthEntity auth);

    String delAuth(String authIds);

    String updateAuth(AuthEntity auth);
}
