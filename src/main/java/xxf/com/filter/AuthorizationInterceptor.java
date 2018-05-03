package xxf.com.filter;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import xxf.com.config.annotation.Authorization;
import xxf.com.utils.Constants;
import xxf.com.utils.RedisKeys;
import xxf.com.utils.RedisUtils;
import xxf.com.utils.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class  AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //加了不需要验证注解直接通过
        if (method.getAnnotation(Authorization.class)== null) {
            return true;
        }

        //从header中得到token
        String authorization = request.getParameter(Constants.AUTHORIZATION);

        if (tokenUtil == null) {//解决service为null无法注入问题
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            tokenUtil = (TokenUtil) factory.getBean("tokenUtil");
            redisUtils=(RedisUtils) factory.getBean("redisUtils");
        }

        //验证token
        if (!tokenUtil.checkToken(authorization)) {
            //如果验证token失败，并且方法注明了Authorization，返回401错误
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }else{
            String authsStr=redisUtils.getObjectByKey(RedisKeys.TOKEN_AUTH(authorization));
            if(authsStr==null||"".equals(authsStr)||"[]".equals(authsStr)){
                response.setStatus(553);
                return false;
            }
            JsonArray ja=new JsonParser().parse(authsStr).getAsJsonArray();

            for (int i = 0; i <ja.size() ; i++) {
                Object o=ja.get(i).getAsJsonObject().get("authMethod");
                if (o!=null){
                    String authMethod=o.toString();
                    authMethod=authMethod.replaceAll("\"","");
                    String[] urlArray=request.getRequestURL().toString().split("/");
                    String urlFile=urlArray[urlArray.length-2];
                    String urlMeth=urlArray[urlArray.length-1];
                    String url =urlFile+"/"+urlMeth;
                    String authArrays[]=authMethod.split("##");
                    for (int j = 0; j <authArrays.length ; j++) {
                        String authUrl=authArrays[j];
                        String[] authUrlArray=authUrl.split("/");
                        if(authUrlArray[authUrlArray.length-1].equals("*")){
                            if(url.contains(authUrlArray[authUrlArray.length-2])){
                                response.setStatus(200);
                                return true;
                            }else{
                                response.setStatus(553);
                            }
                        }else{
                            if(url.equals(authUrl)){
                                return true;
                            }else{
                                response.setStatus(553);
                            }
                        }
                    }
                }

            }
        }

        return false;
    }
}
