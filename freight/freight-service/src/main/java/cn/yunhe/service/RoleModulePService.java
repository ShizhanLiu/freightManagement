package cn.yunhe.service;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/15 11:11
 */
public interface RoleModulePService {
    /**
     * 为角色分配权限
     * 思路：1.先删除角色原有权限2. 保存新权限
     *
     * @param roleId
     * @param moduleIds
     * @throws Exception
     */
    public void createRoleModule(String roleId, String[] moduleIds) throws Exception;
}
