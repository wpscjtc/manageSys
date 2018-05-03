package xxf.com.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xxf.com.entities.AuthEntity;
import xxf.com.entities.RoleEntity;
import xxf.com.entities.UsersEntity;
import xxf.com.mapper.OtheAuthMapper;
import xxf.com.service.LoginService;
import xxf.com.utils.*;
import xxf.com.vo.LoginInfo;
import xxf.other.mapper.OtheRoleMapper;
import xxf.other.mapper.OtherUserMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OtherUserMapper userMapper;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private OtheAuthMapper otheAuthMapper;
    @Autowired
    private OtheRoleMapper otheRoleMapper;

    /**
      *@Author:wangping
      *@Despribtion:登录
      *@Date 2018/4/19 14:40
    */
    @Override
    public String loginForIm(Integer userId) {
        UsersEntity userForLogin;
        try {
            userForLogin= userMapper.getUserForLogin(userId.toString());
        }catch (Exception e){
            logger.error("loginService:"+e);
            return GsonUtil.setResult(MsgUtil.MSGSTATUS_IM_FAIL,MsgUtil.ERRO_INFO).toString();
        }

        if(userForLogin==null){
            return GsonUtil.setResult(MsgUtil.MSGSTATUS_IM_FAIL,MsgUtil.NO_USER).toString();
        }
        //判断是否允许登录
        if(userForLogin.getStatus()==null||"0".equals(userForLogin.getStatus())){
            return GsonUtil.setResult(MsgUtil.MSGSTATUS_IM_FAIL,MsgUtil.LOGIN_FORBIDEN).toString();
        }
        //验证成功，生成token数据放入缓存中
        //用时间戳加userid生成token
        String loginToken;
        LoginInfo loginInfo=new LoginInfo();
        loginInfo.setLoginUserID(userForLogin.getUserID());
        loginInfo.setLoginUserName(userForLogin.getName());
        loginInfo.setLoginStatus(userForLogin.getStatus()+"");
        try {
            loginToken = tokenUtil.createLoginToken(loginInfo);
        } catch (Exception e) {
            logger.error("loginService:"+e);
            return GsonUtil.setResult(0,MsgUtil.ERRO_INFO).toString();
        }
        loginInfo.setLoginToken(loginToken);
        //设置登录成功后的数据
        setInfoAfterLogin(loginInfo,loginToken,userForLogin);

        return GsonUtil.setResult(1,loginInfo,MsgUtil.LOGIN_SUCCESS).toString();
    }

    @Override
    public String login(UsersEntity user) {
        //校验密码
        String password=user.getPassword();
        String userId=user.getUserID();
        if(userId==null||"".equals(userId)||password==null||"".equals(password)){
            return GsonUtil.setResult(0,MsgUtil.NO_PASSWORD_USERID).toString();
        }
        UsersEntity userForLogin;
        try {
            userForLogin= userMapper.getUserForLogin(userId);
        }catch (Exception e){
            logger.error("loginService:"+e);
            return GsonUtil.setResult(0,MsgUtil.ERRO_INFO).toString();
        }

        if(userForLogin==null||userForLogin.getPassword()==null){
            return GsonUtil.setResult(0,MsgUtil.NO_USER).toString();
        }
        //md5加密后校验密码
        String passwordOri=userForLogin.getPassword().toUpperCase();
        String passwordMd5=MD5Util.Md5Value(password);
        if(!passwordOri.equals(passwordMd5)){
            return GsonUtil.setResult(0,MsgUtil.PASSWORD_ERROR).toString();
        }
        //判断是否允许登录
        if(userForLogin.getStatus()==null||"0".equals(userForLogin.getStatus())){
            return GsonUtil.setResult(0,MsgUtil.LOGIN_FORBIDEN).toString();
        }
        //验证成功，生成token数据放入缓存中
        //用时间戳加userid生成token
        String loginToken;
        LoginInfo loginInfo=new LoginInfo();
        loginInfo.setLoginUserID(userForLogin.getUserID());
        loginInfo.setLoginUserName(userForLogin.getName());
        loginInfo.setLoginStatus(userForLogin.getStatus()+"");
        try {
            loginToken = tokenUtil.createLoginToken(loginInfo);
        } catch (Exception e) {
            logger.error("loginService:"+e);
            return GsonUtil.setResult(0,MsgUtil.ERRO_INFO).toString();
        }
        loginInfo.setLoginToken(loginToken);
        //设置登录成功后的数据
        loginInfo=setInfoAfterLogin(loginInfo,loginToken,userForLogin);

        return GsonUtil.setResult(1,loginInfo,MsgUtil.LOGIN_SUCCESS).toString();
    }

    private LoginInfo setInfoAfterLogin(LoginInfo loginInfo,String loginToken,UsersEntity userForLogin){
        //将用户权限以token:json数组的形式存入redis
        //根据用户userId获取用户角色
        List<RoleEntity> roles=otheRoleMapper.getRoleByUserId(userForLogin.getId());
        if(roles.size()>0){
            String ids="";
            for (RoleEntity role:
                    roles) {
                ids+=role.getId()+",";
            }
            ids=ids.substring(0,ids.length()-1);
            List<AuthEntity> auths=otheAuthMapper.getAuthByRoleIds(ids,userForLogin.getOrgID());
            Map<String,String> authsMap=new HashMap<>();
            for (AuthEntity authEntity:auths) {
                if(authsMap.get(authEntity.getAuthType())!=null){
                    authsMap.put(authEntity.getAuthType().trim(),authsMap.get(authEntity.getAuthType().trim())+","+authEntity.getAuthWay().trim());
                }else{
                    authsMap.put(authEntity.getAuthType().trim(),authEntity.getAuthWay().trim());
                }

            }
            loginInfo.setAuthMap(authsMap);
            redisUtils.putObjectByKey(auths,RedisKeys.TOKEN_AUTH(loginToken),30);
        }

        return loginInfo;
    }

    /**
      *@Author:wangping
      *@Despribtion:注销
      *@Date 2018/4/19 14:40
    */
    @Override
    public String logout(String loginToken) {
        if(loginToken!=null&&!"".equals(loginToken)){
            try {
                tokenUtil.delToken(loginToken);
                redisUtils.delObjectByKey(RedisKeys.TOKEN_AUTH(loginToken));
                return GsonUtil.setResult(1).toString();
            }catch (Exception e){
                logger.error("login:logout 登出失败 e="+e);
                return GsonUtil.setResult(0).toString();
            }
        }else{
            logger.error("login:logout 没取到token ");
            return GsonUtil.setResult(0).toString();
        }
    }

}
