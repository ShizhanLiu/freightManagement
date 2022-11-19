package cn.yunhe.pojo;

public class RoleModulePKey {
    private String moduleId;

    private String roleId;

    public RoleModulePKey(String moduleId, String roleId) {
        this.moduleId = moduleId;
        this.roleId = roleId;
    }

    public RoleModulePKey() {
        super();
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}