package xxf.com.entities;

public class RoleEntity {
    private Integer id;
    private Integer type;
    private String name;
    private Integer Category;
    private String roleCode;
    private Integer orgID;
    private Integer morgID;

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", Category=" + Category +
                ", roleCode='" + roleCode + '\'' +
                ", orgID=" + orgID +
                ", morgID=" + morgID +
                '}';
    }

    public Integer getMorgID() {
        return morgID;
    }

    public void setMorgID(Integer morgID) {
        this.morgID = morgID;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return Category;
    }

    public void setCategory(Integer category) {
        Category = category;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }
}
