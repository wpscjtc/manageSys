package xxf.com.entities;

import java.util.Date;

public class UsersEntity {
    private String token;
    private String userID;
    private String password;
    private String name;
    private Integer id;

    //0|内部员工;1|外部客户;2|开发人员;3|系统管理员;4|Webservice调用用户
    private Integer grade;
    private String gradeName;

    private Date lastLogin;
    private Integer logins;
    private Date chgPwdTime;
    private Integer chgPwdLimit;
    private Integer status;//1|是;2|否(是否允许登录)
    private String statusName;
    private String ipLimit;
    private String certNo;
    private Integer orgID;
    private String photo;
    private Integer userAttribute;
    private Date femployed;
    private Integer farea;
    private String orgNmae;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getOrgNmae() {
        return orgNmae;
    }

    public void setOrgNmae(String orgNmae) {
        this.orgNmae = orgNmae;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getLogins() {
        return logins;
    }

    public void setLogins(Integer logins) {
        this.logins = logins;
    }

    public Date getChgPwdTime() {
        return chgPwdTime;
    }

    public void setChgPwdTime(Date chgPwdTime) {
        this.chgPwdTime = chgPwdTime;
    }

    public Integer getChgPwdLimit() {
        return chgPwdLimit;
    }

    public void setChgPwdLimit(Integer chgPwdLimit) {
        this.chgPwdLimit = chgPwdLimit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIpLimit() {
        return ipLimit;
    }

    public void setIpLimit(String ipLimit) {
        this.ipLimit = ipLimit;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getUserAttribute() {
        return userAttribute;
    }

    public void setUserAttribute(Integer userAttribute) {
        this.userAttribute = userAttribute;
    }

    public Date getFemployed() {
        return femployed;
    }

    public void setFemployed(Date femployed) {
        this.femployed = femployed;
    }

    public Integer getFarea() {
        return farea;
    }

    public void setFarea(Integer farea) {
        this.farea = farea;
    }


    @Override
    public String toString() {
        return "UsersEntity{" +
                "token='" + token + '\'' +
                ", userID='" + userID + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", grade=" + grade +
                ", lastLogin=" + lastLogin +
                ", logins=" + logins +
                ", chgPwdTime=" + chgPwdTime +
                ", chgPwdLimit=" + chgPwdLimit +
                ", status=" + status +
                ", ipLimit='" + ipLimit + '\'' +
                ", certNo='" + certNo + '\'' +
                ", orgID=" + orgID +
                ", photo='" + photo + '\'' +
                ", userAttribute=" + userAttribute +
                ", femployed=" + femployed +
                ", farea=" + farea +
                '}';
    }
}
