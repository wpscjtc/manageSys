package xxf.com.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xxf.com.entities.UsersEntity;
import xxf.com.service.UserService;
import xxf.com.utils.GsonUtil;
import xxf.other.mapper.OtherUserMapper;

/**
 *@Author:wangping
 *@Despribtion:用户管理service
 *@Date 2018/4/12 9:49
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private OtherUserMapper userMapper;


    /**
     *@Author:wangping
     *@Despribtion:读取用户
     *@Date 2018/4/12 9:49
     */
    @Override
    public String listUsers(UsersEntity user,int pageNum,int pageSize) {

        String resultGson;
        try{
            Page<UsersEntity> page =PageHelper.startPage(pageNum,pageSize,true);
            userMapper.getAllUsers(user);
            resultGson=GsonUtil.setResult(1,page.toPageInfo()).toString();
        }catch (Exception e){
            resultGson=GsonUtil.setResult(0,e.getMessage()).toString();
        }
        return resultGson;
    }
}
