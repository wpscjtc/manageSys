package xxf.com.utils;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import xxf.com.vo.LoginInfo;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class TokenUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    /**
      *@Author:wangping
      *@Despribtion:生成token并存入redis
      *@Date 2018/4/17 14:38
    */
    public  String createLoginToken(LoginInfo loginInfo) throws Exception{
        String loginToken=MD5Util.Md5Value(loginInfo.getLoginUserID()+(new Date().getTime()));
        redisTemplate.opsForValue().set(RedisKeys.USER_SESSION(loginToken),GsonUtil.objectToJson(loginInfo),30,TimeUnit.MINUTES);
        return loginToken;
    }

    /**
      *@Author:wangping
      *@Despribtion:检查token并更新
      *@Date 14:38
    */
    public  boolean checkToken(String loginToken) throws Exception{

        if(loginToken!=null&&redisTemplate.hasKey(RedisKeys.USER_SESSION(loginToken))){
            redisTemplate.expire(loginToken,30,TimeUnit.MINUTES);
            return true;
        }
        return false;
    }

    /**
      *@Author:wangping
      *@Despribtion:清除token
      *@Date 2018/4/17 14:40
    */
    public  void delToken(String loginToken) throws Exception{

        redisTemplate.delete(loginToken);
    }

    /**
      *@Author:wangping
      *@Despribtion:根据token获取当前登录的用户
      *@Date 2018/4/17 14:47
    */
    public  LoginInfo getUserByToken(String loginToken) throws Exception{
        String loginInfoJson=redisTemplate.opsForValue().get(RedisKeys.USER_SESSION(loginToken))+"";
        LoginInfo loginInfo=new Gson().fromJson(loginInfoJson,LoginInfo.class);
        return loginInfo;
    }
}
