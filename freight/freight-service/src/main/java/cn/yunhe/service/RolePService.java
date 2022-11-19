package cn.yunhe.service;

import cn.yunhe.pojo.PageBean;
import cn.yunhe.pojo.RoleP;

import java.util.List;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/14 16:03
 */
public interface RolePService {
    /**
     * 查询所有角色
     * @return
     */
    public List<RoleP> listRole();

    /**
     * 查询分页
     * @param pageBean
     * @return
     */
    public PageBean listRoleOfPage(PageBean pageBean);



    public void createRole(RoleP roleP) throws Exception;


    public RoleP findByRoleId(String roleid);
}
