package xxf.com.entities;

public class AuthEntity {

    private Integer AuthID;
    private String authName;
    private String authType;
    private String authDesc;
    private String authWay;
    private String authMethod;
    private String isCheck;

    @Override
    public String toString() {
        return "AuthEntity{" +
                "AuthID=" + AuthID +
                ", authName='" + authName + '\'' +
                ", authType='" + authType + '\'' +
                ", authDesc='" + authDesc + '\'' +
                ", authWay='" + authWay + '\'' +
                ", authMethod='" + authMethod + '\'' +
                ", isCheck='" + isCheck + '\'' +
                '}';
    }

    public String getAuthMethod() {
        return authMethod;
    }

    public void setAuthMethod(String authMethod) {
        this.authMethod = authMethod;
    }

    public Integer getAuthID() {
        return AuthID;
    }

    public void setAuthID(Integer authID) {
        AuthID = authID;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getAuthDesc() {
        return authDesc;
    }

    public void setAuthDesc(String authDesc) {
        this.authDesc = authDesc;
    }

    public String getAuthWay() {
        return authWay;
    }

    public void setAuthWay(String authWay) {
        this.authWay = authWay;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }
}
