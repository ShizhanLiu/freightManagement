package cn.yunhe.pojo;

import java.io.Serializable;

public class DeptP implements Serializable {
    private String deptId;

    private String deptName;

    private String parentId;

    private Integer state;

    private String deptNo;

    public DeptP(String deptId, String deptName, String parentId, Integer state, String deptNo) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.parentId = parentId;
        this.state = state;
        this.deptNo = deptNo;
    }

    public DeptP() {
        super();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
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
        this.deptNo = deptNo == null ? null : deptNo.trim();
    }
}