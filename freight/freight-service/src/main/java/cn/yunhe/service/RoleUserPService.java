package cn.yunhe.service;

import cn.yunhe.pojo.RoleUserPKey;

import java.util.List;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/14 15:15
 */
public interface RoleUserPService  {
    /**
     * 根据用户的编号查询该用户拥有的角色，用于做回显
     * @param userid
     * @return
     * @throws Exception
     */
    public List<RoleUserPKey> listRoleUser(String userid) throws Exception;

    /**
     * 为用户分配角色
     * 1.删除原有角色
     * 2.添加新角色
     * @param userid
     * @param roleIds
     * @throws Exception
     */
    public void addRoleUser(String userid, String[] roleIds) throws Exception;
}
