package cn.yunhe.pojo;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/9 9:17
 */
public class DeptVo {
    private String deptId;
    private String deptName;
    private String parentId;
    private Integer state;
    private String deptNo;
    private String parentName;

    public DeptVo() {
    }

    public DeptVo(String deptId, String deptName, String parentId, Integer state, String deptNo, String parentName) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.parentId = parentId;
        this.state = state;
        this.deptNo = deptNo;
        this.parentName = parentName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        return "DeptVo{" +
                "deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", parentId='" + parentId + '\'' +
                ", state=" + state +
                ", deptNo='" + deptNo + '\'' +
                ", parentName='" + parentName + '\'' +
                '}';
    }
}
