package cn.yunhe.service;

import cn.yunhe.pojo.TreeNode;

import java.util.List;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/14 19:41
 */
public interface ModulePService {
    public List<TreeNode> listModuleOfTreeBean() throws Exception;

    /**
     * 查询该角色拥有的权限信息，支持数据回显
     * @param roleid
     * @return
     */
    List<TreeNode> listModuleOfTreeBeanByRoleId(String roleid);
}
