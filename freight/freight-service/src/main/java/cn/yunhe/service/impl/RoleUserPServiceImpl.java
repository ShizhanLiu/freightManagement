package cn.yunhe.service.impl;

import cn.yunhe.mapper.RoleUserPMapper;
import cn.yunhe.pojo.RoleUserPExample;
import cn.yunhe.pojo.RoleUserPKey;
import cn.yunhe.service.RoleUserPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/14 15:25
 */
@Service
public class RoleUserPServiceImpl implements RoleUserPService {
    @Autowired
    private RoleUserPMapper roleUserPMapper;

    @Override
    public List<RoleUserPKey> listRoleUser(String userid) throws Exception {
        RoleUserPExample roleUserPExample = new RoleUserPExample();
        RoleUserPExample.Criteria criteria = roleUserPExample.createCriteria();
        criteria.andUserIdEqualTo(userid);
        return roleUserPMapper.selectByExample(roleUserPExample);
    }

    @Override
    public void addRoleUser(String userid, String[] roleIds) throws Exception {
        //1.删除原有角色
        //必须先删除用户原来的角色
        RoleUserPExample roleUserPExample = new RoleUserPExample();
        RoleUserPExample.Criteria criteria = roleUserPExample.createCriteria();
        criteria.andUserIdEqualTo(userid); //where userId=xxx
        roleUserPMapper.deleteByExample(roleUserPExample);
        //2 添加新角色
        for (String roleId:roleIds){
            RoleUserPKey roleUserPKey = new RoleUserPKey();
            roleUserPKey.setUserId(userid);
            roleUserPKey.setRoleId(roleId);
            roleUserPMapper.insertSelective(roleUserPKey);
        }


    }
}
