package xxf.com.service;

import xxf.com.entities.RoleEntity;

public interface RoleService {

    /**
     *@Author:wangping
     *@Despribtion:读取角色
     *@Date 2018/4/12 9:49
     */
    String listRole(RoleEntity role, int pageNum, int pageSize);

    String getRoleByOderId(RoleEntity role, int orderId, int pageNum, int pageSize);
}
