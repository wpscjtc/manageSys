package xxf.com.vo;

import java.util.Map;

/**
  *@Author:wangping
  *@Despribtion:登录存的信息
  *@Date 2018/4/19 11:56
*/
public class LoginInfo {
    private String loginToken;//登录token
    private String loginUserID;//当前登录用户名
    private String loginUserName;//当前登录用户名
    private String loginStatus;//当前登录用户状态
    private Map<String,String> authMap;//页面权限控制

    @Override
    public String toString() {
        return "LoginInfo{" +
                "loginToken='" + loginToken + '\'' +
                ", loginUserID='" + loginUserID + '\'' +
                ", loginUserName='" + loginUserName + '\'' +
                ", loginStatus='" + loginStatus + '\'' +
                ", authMap=" + authMap +
                '}';
    }

    public Map<String, String> getAuthMap() {
        return authMap;
    }

    public void setAuthMap(Map<String, String> authMap) {
        this.authMap = authMap;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getLoginUserID() {
        return loginUserID;
    }

    public void setLoginUserID(String loginUserID) {
        this.loginUserID = loginUserID;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }
}
