package cn.yunhe.service.impl;

import cn.yunhe.mapper.RoleModulePMapper;
import cn.yunhe.pojo.RoleModulePExample;
import cn.yunhe.pojo.RoleModulePKey;
import cn.yunhe.service.RoleModulePService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/15 11:14
 */
@Service
public class RoleModuleServiceImpl implements RoleModulePService {
    @Autowired
    private RoleModulePMapper roleModulePMapper;

    @Override
    public void createRoleModule(String roleId, String[] moduleIds) throws Exception {
            //删除角色原有的权限
        RoleModulePExample roleModulePExample = new RoleModulePExample();
        RoleModulePExample.Criteria criteria = roleModulePExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        roleModulePMapper.deleteByExample(roleModulePExample);
        //保存新权限
        for (String moduleId:moduleIds){
             RoleModulePKey roleModulePKey = new RoleModulePKey();
             roleModulePKey.setRoleId(roleId);
             roleModulePKey.setModuleId(moduleId);
             roleModulePMapper.insertSelective(roleModulePKey);
        }


    }
}
