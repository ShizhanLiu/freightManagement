package cn.yunhe.service;

import cn.yunhe.pojo.*;

import java.util.List;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/6 12:15
 */
public interface UserPService {
    public UserP loginP(String username);

    /**
     * 根据用户查询用户扩展信息和部门信息
     * @param user
     * @return
     */
    public CurrentUser findUserInfoDeptByUser(UserP user);

    /**
     * 根据用户编号查询权限信息
     * @param userid
     * @return
     */
    public List<ModuleP> getPermissionByUserId(String userid);

    public PageBean listUserOfPage(PageBean pageBean);

    public void createUser(UserP userP, UserinfoP userinfoP);

    public List<UserP> listUser() ;
}
