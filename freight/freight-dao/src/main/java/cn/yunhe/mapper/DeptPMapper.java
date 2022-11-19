package cn.yunhe.mapper;

import cn.yunhe.pojo.DeptP;
import cn.yunhe.pojo.DeptPExample;
import cn.yunhe.pojo.DeptVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptPMapper {
    int countByExample(DeptPExample example);

    int deleteByExample(DeptPExample example);

    int deleteByPrimaryKey(String deptId);

    int insert(DeptP record);

    int insertSelective(DeptP record);

    List<DeptP> selectByExample(DeptPExample example);

    DeptP selectByPrimaryKey(String deptId);

    int updateByExampleSelective(@Param("record") DeptP record, @Param("example") DeptPExample example);

    int updateByExample(@Param("record") DeptP record, @Param("example") DeptPExample example);

    int updateByPrimaryKeySelective(DeptP record);

    int updateByPrimaryKey(DeptP record);

    /**
     * 查询部门信息和父部门名称，自连接
     * @return
     */
    List<DeptVo> listDeptAndParent();
}