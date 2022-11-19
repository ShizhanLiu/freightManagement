package cn.yunhe.service.impl;

import cn.yunhe.mapper.RolePMapper;
import cn.yunhe.pojo.*;
import cn.yunhe.service.RolePService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/14 16:03
 */
@Service
public class RolePServiceImpl implements RolePService {
    @Autowired
    private RolePMapper rolePMapper;
    @Override
    public List<RoleP> listRole() {
        return rolePMapper.selectByExample(null);
    }

    @Override
    public PageBean listRoleOfPage(PageBean pageBean) {
        //设置当前页和分页单位
        PageHelper.startPage(pageBean.getCurPage(),pageBean.getPageSize());
        RolePExample rolePExample = new RolePExample();
        rolePExample.setOrderByClause("ORDER_NO asc");


        //调用DeptpMapper查询部门信息
        List<RoleP> list = rolePMapper.selectByExample(null);

        //创建Pageinfo对象
        PageInfo<RoleP> pageinfo = new PageInfo<>(list);
        //创建pageBean对象封装数据
        PageBean pb = new PageBean();
        pb.setData(pageinfo.getList()); //设置分页数据
        pb.setCurPage(pageinfo.getPageNum());//当前页
        pb.setPageSize(pageinfo.getPageSize());//分页单位
        pb.setTotalRows(pageinfo.getTotal()); //设置总记录数
        pb.setTotalPages(pageinfo.getPages()); //设置总页数
        return pb;
    }

    @Override
    public void createRole(RoleP roleP) throws Exception {
        //获取登陆的用户信息（创建者和创建部门）
        Subject subject = SecurityUtils.getSubject();
        //获取身份信息
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        UserP user = currentUser.getUserP();
        DeptP dept = currentUser.getDeptP();

        //补全角色信息中的数据
        roleP.setRoleId(UUID.randomUUID().toString());//角色编号
        roleP.setCreateBy(user.getUserId());  //创建人
        roleP.setCreateDept(dept.getDeptId());//创建部门
        roleP.setCreateTime(new Date());
        roleP.setUpdateBy(user.getUserId());
        roleP.setUpdateTime(new Date());
        //设置排序号（获得最大的order_no）
        Integer maxOrderNo = rolePMapper.getMaxOrderNo();
        roleP.setOrderNo(maxOrderNo);
        //新增
        rolePMapper.insertSelective(roleP);


    }

    @Override
    public RoleP findByRoleId(String roleid) {
        return rolePMapper.selectByPrimaryKey(roleid);
    }
}
