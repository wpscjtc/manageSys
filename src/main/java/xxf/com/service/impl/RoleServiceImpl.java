package xxf.com.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xxf.com.entities.RoleEntity;
import xxf.com.service.RoleService;
import xxf.com.utils.GsonUtil;
import xxf.other.mapper.OtheRoleMapper;

/**
 *@Author:wangping
 *@Despribtion:用户管理service
 *@Date 2018/4/12 9:49
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private OtheRoleMapper otheRoleMapper;


    /**
     *@Author:wangping
     *@Despribtion:读取角色
     *@Date 2018/4/12 9:49
     */
    @Override
    public String listRole(RoleEntity role, int pageNum, int pageSize) {

        String resultGson;
        try{
            Page<RoleEntity> page =PageHelper.startPage(pageNum,pageSize,true);
            otheRoleMapper.getAllRoles(role);
            resultGson=GsonUtil.setResult(1,page.toPageInfo()).toString();
        }catch (Exception e){
            resultGson=GsonUtil.setResult(0,e.getMessage()).toString();
        }
        return resultGson;
    }

    /**
      *@Author:wangping
      *@Despribtion:通过组织id获取角色（点击树显示角色）
      *@Date 2018/4/25 14:35
    */
    @Override
    public String getRoleByOderId(RoleEntity role, int orderId, int pageNum, int pageSize){
        String resultGson;
        try{
            role.setMorgID(orderId);
            Page<RoleEntity> page =PageHelper.startPage(pageNum,pageSize,true);
            otheRoleMapper.getRoleByOderId(role);
            resultGson=GsonUtil.setResult(1,page.toPageInfo()).toString();
        }catch (Exception e){
            resultGson=GsonUtil.setResult(0,e.getMessage()).toString();
        }
        return resultGson;
    }
}
