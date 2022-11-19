package cn.yunhe.service;

import cn.yunhe.pojo.UserinfoP;

import java.util.List;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/14 11:28
 */
public interface UserinfoPService {
    public List<UserinfoP> listUserinfo();
    public UserinfoP getUserInfoPById(String id);


}
