package cn.yunhe.service.impl;

import cn.yunhe.mapper.ModulePMapper;
import cn.yunhe.pojo.TreeNode;
import cn.yunhe.service.ModulePService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/14 19:44
 */
@Service
public class ModuleServiceImpl implements ModulePService {
    @Autowired
    private ModulePMapper modulePMapper;
    @Override
    public List<TreeNode> listModuleOfTreeBean() throws Exception {
        return modulePMapper.listModelOfTreeBean();
    }

    @Override
    public List<TreeNode> listModuleOfTreeBeanByRoleId(String roleid) {
        return modulePMapper.listModuleOfTreeBeanByRoleId(roleid);
    }
}
