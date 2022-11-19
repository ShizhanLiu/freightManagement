package cn.yunhe.pojo;

public class RoleUserPKey {
    private String roleId;

    private String userId;

    public RoleUserPKey(String roleId, String userId) {
        this.roleId = roleId;
        this.userId = userId;
    }

    public RoleUserPKey() {
        super();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}