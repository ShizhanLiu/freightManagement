package cn.yunhe.service.impl;

import cn.yunhe.mapper.DeptPMapper;
import cn.yunhe.mapper.ModulePMapper;
import cn.yunhe.mapper.UserPMapper;
import cn.yunhe.mapper.UserinfoPMapper;
import cn.yunhe.pojo.*;
import cn.yunhe.service.UserPService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/6 12:16
 */
@Service
public class UserService implements UserPService {
    @Autowired
    private UserPMapper userPMapper;
    @Autowired
    private DeptPMapper deptPMapper;
    @Autowired
    private UserinfoPMapper userinfoPMapper;
    @Autowired
    private ModulePMapper modulePMapper;


    @Override
    public UserP loginP(String username) {
        //创建userExample对象
        UserPExample userPExample = new UserPExample();
        UserPExample.Criteria criteria = userPExample.createCriteria();
        //通过criteria构建查询条件
        criteria.andUserNameEqualTo(username);
        //用户状态为启用：1 用户状态为禁用 0，再多加一个条件即可
        criteria.andStateEqualTo(1);//合起来的状态时where username=xxx and state = 1
        List<UserP> list = userPMapper.selectByExample(userPExample);
        if (list!=null&&list.size()>0){
            return list.get(0);
        }


        return null;
    }

    @Override
    public CurrentUser findUserInfoDeptByUser(UserP user) {
        CurrentUser currentUser = new CurrentUser();
        currentUser.setUserP(user);// 设置用户基本信息
        currentUser.setDeptP(deptPMapper.selectByPrimaryKey(user.getDeptId()));// 设置用户所属部门信息
        currentUser.setUserInfoP(userinfoPMapper.selectByPrimaryKey(user.getUserId()));// 设置用户详细信息
        return currentUser;
    }

    @Override
    public List<ModuleP> getPermissionByUserId(String userid) {
        return modulePMapper.getPermissionsByUserId(userid);
    }

    @Override
    public PageBean listUserOfPage(PageBean pageBean) {
        //设置当前页和分页单位
        PageHelper.startPage(pageBean.getCurPage(),pageBean.getPageSize());
        //查询用户数据
        UserPExample userPExample = new UserPExample();
        userPExample.setOrderByClause("UPDATE_TIME desc");//按照修改时间倒叙排列
        List<UserP> list = userPMapper.selectByExample(userPExample);
        //创建pageinfo对象
        PageInfo pageInfo = new PageInfo(list);
        //将数据封装成pagebean对象
        PageBean pb = new PageBean();
        pb.setData(pageInfo.getList());
        pb.setPageSize(pageInfo.getPageSize());
        pb.setCurPage(pageInfo.getPageNum());
        pb.setTotalPages(pageInfo.getPages());
        pb.setTotalRows(pageInfo.getTotal());
        return pb;


    }

    /**
     * 新增用户信息和用户扩展信息
     * @param userP
     * @param userInfoP
     */
    @Override
    public void createUser(UserP userP, UserinfoP userInfoP) {
        //获取登陆的用户信息（创建者和创建部门）
        Subject subject = SecurityUtils.getSubject();
        //获取身份信息
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        UserP user = currentUser.getUserP();
        DeptP dept = currentUser.getDeptP();
        userP.setPassword("111");
        //补全用户信息
        userP.setUserId(UUID.randomUUID().toString()); //用户编号
        //指定初始密码是111
        userP.setPassword(new Md5Hash(userP.getPassword(), userP.getUserName()+ userP.getUserId(),3).toString());
        userP.setCreateBy(user.getUserId()); //创建者
        userP.setCreateDept(dept.getDeptId()); //创建部门
        userP.setUpdateBy(user.getUserId()); //更新者
        userP.setCreateTime(new Date()); //创建时间
        userP.setUpdateTime(new Date()); //更新时间
        //补全用户扩展信息表的信息
        userInfoP.setUserInfoId(userP.getUserId()); //主键关联
        userInfoP.setCreateBy(user.getUserId());
        userInfoP.setCreateDept(dept.getDeptId());
        userInfoP.setUpdateBy(user.getUserId());
        userInfoP.setCreateTime(new Date());
        userInfoP.setUpdateTime(new Date());

        //新增用户
        userPMapper.insertSelective(userP);
        //新增用户扩展信息
        userinfoPMapper.insertSelective(userInfoP);



    }

    @Override
    public List<UserP> listUser() {
        return userPMapper.selectByExample(null);

    }
}
