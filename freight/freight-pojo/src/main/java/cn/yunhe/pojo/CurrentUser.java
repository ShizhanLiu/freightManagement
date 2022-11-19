package cn.yunhe.pojo;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/7 19:28
 */
public class CurrentUser {
    private UserP userP;//封装用户信息
    private UserinfoP userInfoP; //用户扩展信息
    private DeptP deptP; //部门信息

    public UserP getUserP() {
        return userP;
    }

    public void setUserP(UserP userP) {
        this.userP = userP;
    }

    public UserinfoP getUserInfoP() {
        return userInfoP;
    }

    public void setUserInfoP(UserinfoP userInfoP) {
        this.userInfoP = userInfoP;
    }

    public DeptP getDeptP() {
        return deptP;
    }

    public void setDeptP(DeptP deptP) {
        this.deptP = deptP;
    }
}
