package xxf.com.entities;

/**
  *@Author:wangping
  *@Despribtion:组织机构
  *@Date 2018/4/20 9:20
*/
public class OrgnazationEntity {
    private Integer id;
    private Integer fid;
    private Integer grade;//组织级别
    private Integer type;//节点类型 0|普通节点;1|根节点;2|末节点
    private Integer orgType;//1.集团 2.公司 3.部门 4.小组 5.中心
    private String orgCode;
    private String name;
    private String describe;
    private String fdnCode;//相关联节点
    private String bs;
    private Integer fState;//组织状态 1 使用 2 停止
    private String stopDate;//停止时间
    private Integer perNum;//人数

    @Override
    public String toString() {
        return "Orgnazation{" +
                "id=" + id +
                ", fid=" + fid +
                ", grade=" + grade +
                ", type=" + type +
                ", orgType=" + orgType +
                ", orgCode='" + orgCode + '\'' +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", fdnCode='" + fdnCode + '\'' +
                ", bs='" + bs + '\'' +
                ", fState=" + fState +
                ", stopDate='" + stopDate + '\'' +
                ", perNum=" + perNum +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getFdnCode() {
        return fdnCode;
    }

    public void setFdnCode(String fdnCode) {
        this.fdnCode = fdnCode;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public Integer getfState() {
        return fState;
    }

    public void setfState(Integer fState) {
        this.fState = fState;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    public Integer getPerNum() {
        return perNum;
    }

    public void setPerNum(Integer perNum) {
        this.perNum = perNum;
    }
}
