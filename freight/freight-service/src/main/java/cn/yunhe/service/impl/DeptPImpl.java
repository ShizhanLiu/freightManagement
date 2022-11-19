package cn.yunhe.service.impl;

import cn.yunhe.mapper.DeptPMapper;
import cn.yunhe.pojo.DeptP;
import cn.yunhe.pojo.DeptVo;
import cn.yunhe.pojo.PageBean;
import cn.yunhe.service.DeptPService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/9 10:27
 */
@Service
public class DeptPImpl implements DeptPService {
    @Autowired
    private DeptPMapper deptPMapper;

    @Override
    public PageBean listDeptOfPage(PageBean pageBean) {
        //设置当前页和分页单位
        PageHelper.startPage(pageBean.getCurPage(),pageBean.getPageSize());
        //调用DeptpMapper查询部门信息
        List<DeptVo> list = deptPMapper.listDeptAndParent();
        System.out.println("部门信息是"+list);
        //创建Pageinfo对象
        PageInfo<DeptVo> pageInfo = new PageInfo<>(list);
        //创建pageBean对象封装数据
        PageBean pb = new PageBean();
        pb.setData(pageInfo.getList()); //设置分页数据
        pb.setCurPage(pageInfo.getPageNum());//当前页
        pb.setPageSize(pageInfo.getPageSize());//分页单位
        pb.setTotalRows(pageInfo.getTotal()); //设置总记录数
        pb.setTotalPages(pageInfo.getPages()); //设置总页数
        return pb;

    }

    /*查询所有dept*/
    @Override
    public List<DeptP> listDept(){
        return deptPMapper.selectByExample(null);
    }

    /*创建新的dept*/
    @Override
    public void createDept(DeptP deptP)  {
        //设置deptId
        deptP.setDeptId(UUID.randomUUID().toString());
        //设置deptNo
        deptP.setDeptNo(String.valueOf(System.currentTimeMillis()));
        deptP.setState(1);
        deptPMapper.insertSelective(deptP);

    }

    @Override
    public DeptP findByDeptId(String deptId) {
        return deptPMapper.selectByPrimaryKey(deptId);
    }

    @Override
    public void updateDept(DeptP deptP) {
        deptPMapper.updateByPrimaryKeySelective(deptP);

    }
}
