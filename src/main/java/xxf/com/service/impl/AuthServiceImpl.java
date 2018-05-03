package xxf.com.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xxf.com.entities.AuthEntity;
import xxf.com.entities.AuthRole;
import xxf.com.mapper.OtheAuthMapper;
import xxf.com.service.AuthService;
import xxf.com.utils.GsonUtil;

import java.util.List;

/**
 *@Author:wangping
 *@Despribtion:用户管理service
 *@Date 2018/4/12 9:49
 */

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private OtheAuthMapper otherAuthMapper;


    /**
     *@Author:wangping
     *@Despribtion:读取角色
     *@Date 2018/4/12 9:49
     */
    @Override
    public String listAuth(AuthEntity auth, int pageNum, int pageSize) {

        String resultGson;
        try{
            Page<AuthEntity> page =PageHelper.startPage(pageNum,pageSize,true);
            otherAuthMapper.getAllAuths(auth);
            resultGson=GsonUtil.setResult(1,page.toPageInfo()).toString();
        }catch (Exception e){
            resultGson=GsonUtil.setResult(0,e.getMessage()).toString();
        }
        return resultGson;
    }

    /**
      *@Author:wangping
      *@Despribtion:根据角色获取权限
      *@Date 2018/4/23 16:58
    */
    @Override
    public String getAuthByRoleId(int roleId,int orgId) {
        String resultGson;
        try{
            List<AuthEntity> authEntity=otherAuthMapper.getAuthByRoleId(roleId,orgId);
            resultGson=GsonUtil.setResult(1,authEntity).toString();
        }catch (Exception e){
            resultGson=GsonUtil.setResult(0,e.getMessage()).toString();
        }
        return resultGson;
    }

    @Override
    public String getAuthById(int authId) {
        String resultGson;
        try{
            List<AuthEntity> authEntity=otherAuthMapper.getAuthById(authId);
            resultGson=GsonUtil.setResult(1,authEntity).toString();
        }catch (Exception e){
            resultGson=GsonUtil.setResult(0,e.getMessage()).toString();
        }
        return resultGson;
    }

    /**
      *@Author:wangping
      *@Despribtion:新增权限
      *@Date 2018/4/23 16:58
    */
    @Override
    public String addRoleAuth(int roleId, int authId,int orgId) {
        String resultGson;
        AuthRole authRole=new AuthRole();
        authRole.setAuthId(authId);
        authRole.setRoleId(roleId);
        authRole.setOrgId(orgId);
        try{
            otherAuthMapper.addRoleAuth(authRole);
            resultGson=GsonUtil.setResult(1).toString();
        }catch (Exception e){
            resultGson=GsonUtil.setResult(0,e.getMessage()).toString();
        }
        return resultGson;
    }

    /**
      *@Author:wangping
      *@Despribtion:关联角色与权限，已关联则删除关联
      *@Date 2018/4/25 9:04
    */
    @Override
    public String chanegRoleAuth(int roleId, int authId,int orgId) {
        String resultGson;
        AuthRole authRole=new AuthRole();
        authRole.setAuthId(authId);
        authRole.setRoleId(roleId);
        authRole.setOrgId(orgId);
        try{
            int num=otherAuthMapper.getNumByRolIdAndAuthId(authRole);
            if(num>0){
                otherAuthMapper.delRoleAuth(authRole);
            }else{
                otherAuthMapper.addRoleAuth(authRole);
            }

            resultGson=GsonUtil.setResult(1).toString();
        }catch (Exception e){
            resultGson=GsonUtil.setResult(0,e.getMessage()).toString();
        }
        return resultGson;

    }

    /**
      *@Author:wangping
      *@Despribtion:增加权限
      *@Date 2018/4/25 9:05
    */
    @Override
    public String addAuth(AuthEntity auth){
        String resultGson;

        try{
            otherAuthMapper.addAuth(auth);
            resultGson=GsonUtil.setResult(1).toString();
        }catch (Exception e){
            resultGson=GsonUtil.setResult(0,e.getMessage()).toString();
        }
        return resultGson;
    }

    /**
      *@Author:wangping
      *@Despribtion:删除权限
      *@Date 2018/4/25 9:05
    */
    @Override
    public String delAuth(String authIds){
        String resultGson;
        try{
            String authIdStr="";
            String[] authIdsAaary=authIds.split(",");
            for(int i=0;i<authIdsAaary.length;i++){
                if(authIdsAaary[i]!=null&&!"".equals(authIdsAaary[i])){
                    authIdStr+=authIdsAaary[i]+",";
                }
            }
            authIdStr=authIdStr.substring(0,authIdStr.length()-1);
            //删除权限
            otherAuthMapper.delAuth(authIdStr);
            //删除权限角色管理
            otherAuthMapper.delRoleAuthByAuthId(authIdStr);
            resultGson=GsonUtil.setResult(1).toString();
        }catch (Exception e){
            resultGson=GsonUtil.setResult(0,e.getMessage()).toString();
        }
        return resultGson;
    }

    /**
     *@Author:wangping
     *@Despribtion:修改权限
     *@Date 2018/4/25 9:05
     */
    @Override
    public String updateAuth(AuthEntity auth){
        String resultGson;

        try{
            otherAuthMapper.updAuth(auth);
            resultGson=GsonUtil.setResult(1).toString();
        }catch (Exception e){
            resultGson=GsonUtil.setResult(0,e.getMessage()).toString();
        }
        return resultGson;
    }
}
