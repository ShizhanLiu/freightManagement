package cn.yunhe.service.impl;

import cn.yunhe.mapper.UserinfoPMapper;
import cn.yunhe.pojo.UserinfoP;
import cn.yunhe.service.UserinfoPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/14 11:28
 */
@Service
public class UserinfoPServiceImpl implements UserinfoPService {
    @Autowired
    private UserinfoPMapper userinfoPMapper;
    @Override
    public List<UserinfoP> listUserinfo() {
        return userinfoPMapper.selectByExample(null);
    }

    @Override
    public UserinfoP getUserInfoPById(String id) {
        return userinfoPMapper.selectByPrimaryKey(id);
    }
}
