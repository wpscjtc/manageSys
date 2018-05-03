package xxf.com.utils;

public class RedisKeys {

    /**
      *@Author:wangping
      *@Despribtion:存session
      *@Date 2018/4/17 13:42
    */
    public static String USER_SESSION(String token){
        return "USER_SESSION:"+token;
    }

    public static String TOKEN_AUTH(String token){
        return "TOKEN_AUTH:"+token;
    }
}
