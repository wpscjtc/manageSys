package xxf.com.utils;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
      *@Author:wangping
      *@Despribtion:将对象以key存在redis中 带过期时间
      *@Date 2018/4/27 16:52
    */
    public boolean putObjectByKey(Object o,String key,int expired){
        try{
            String oJsonStr = new Gson().toJson(o);
            redisTemplate.opsForValue().set(key,oJsonStr,expired,TimeUnit.MINUTES);
        }catch (Exception e){
            logger.error("RedisUtils putObjectByKey e="+e);
            return false;
        }
        return true;

    }

    public String getObjectByKey(String key){
        try{
            Object o= redisTemplate.opsForValue().get(key);
            if(o!=null&&o.toString().length()>0){
                return o.toString();
            }
        }catch (Exception e){
            logger.error("RedisUtils putObjectByKey e="+e);
            return null;
        }
        return null;

    }

    /**
     *@Author:wangping
     *@Despribtion:将对象以key存在redis中 不带过期时间
     *@Date 2018/4/27 16:52
     */
    public boolean putObjectByKey(Object o,String key){
        try{
            String oJsonStr = new Gson().toJson(o);
            redisTemplate.opsForValue().set(key,GsonUtil.objectToJson(oJsonStr));
        }catch (Exception e){
            logger.error("RedisUtils putObjectByKey e="+e);
            return false;
        }
        return true;
    }

    public boolean delObjectByKey(String key){
        try{
            redisTemplate.delete(key);
        }catch (Exception e){
            logger.error("RedisUtils putObjectByKey e="+e);
            return false;
        }
        return true;
    }

    public boolean putToMap(String key,Map object){
        /*try{
            String oJsonStr = new Gson().toJson(o);
            redisTemplate.opsForValue().set(RedisKeys.TOKEN_AUTH(key),GsonUtil.objectToJson(oJsonStr));
        }catch (Exception e){
            logger.error("RedisUtils putObjectByKey e="+e);
            return false;
        }*/
        return true;
    }

    public boolean putToMap(String key,String mapKey,String mapVlue,int expired){
        try{
            redisTemplate.opsForHash().put(key,mapKey,mapVlue);
            redisTemplate.expire(key,expired,TimeUnit.MINUTES);
        }catch (Exception e){
            logger.error("RedisUtils putToMap e="+e);
            return false;
        }
        return true;
    }

    public boolean putToMap(String key,String mapKey,String mapVlue){
        try{
            redisTemplate.opsForHash().put(key,mapKey,mapVlue);
        }catch (Exception e){
            logger.error("RedisUtils putToMap e="+e);
            return false;
        }
        return true;
    }

}
