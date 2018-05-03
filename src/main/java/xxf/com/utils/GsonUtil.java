package xxf.com.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class GsonUtil {

    public static String objectToJson(Object o){
        return new Gson().toJson(o);
    }

    /**
      *@Author:wangping
      *@Despribtion:返回对象处理
      *@Date 2018/4/12 10:34
    */
    public static JsonObject setResult(int status){
        JsonObject result=new JsonObject();
        result.addProperty("status",status);
        return result;
    }

    /**
      *@Author:wangping
      *@Despribtion:返回对象处理 带消息
      *@Date 2018/4/12 10:53
    */
    public static JsonObject setResult(int status,String msg){
        JsonObject result=new JsonObject();
        result.addProperty("status",status);
        result.addProperty("msg",msg);
        return result;
    }

    /**
      *@Author:wangping
      *@Despribtion:返回对象处理 带对象
      *@Date 2018/4/12 10:53
    */
    public static JsonObject setResult(int status,Object restMsg){
        JsonObject result=new JsonObject();
        result.addProperty("status",status);
        result.addProperty("restMsg",new Gson().toJson(restMsg));
        return result;
    }

    /**
      *@Author:wangping
      *@Despribtion:返回对象处理 带对象 带消息
      *@Date 2018/4/12 10:53
    */
    public static JsonObject setResult(int status,Object restMsg,String msg){
        JsonObject result=new JsonObject();
        result.addProperty("status",status);
        result.addProperty("restMsg",new Gson().toJson(restMsg));
        result.addProperty("msg",msg);
        return result;
    }
/*
    public static String setResult(int status,List<Object> restMsgs){
        JsonObject result=new JsonObject();
        result.addProperty("status",status);
        result.addProperty("restMsg",new Gson().toJson(restMsgs));
        return result.toString();
    }

    public static String setResult(int status,List<Object> restMsgs,String msg){
        JsonObject result=new JsonObject();
        result.addProperty("status",status);
        result.addProperty("msg","msg");
        result.addProperty("restMsg",new Gson().toJson(restMsgs));
        return result.toString();
    }*/

}
