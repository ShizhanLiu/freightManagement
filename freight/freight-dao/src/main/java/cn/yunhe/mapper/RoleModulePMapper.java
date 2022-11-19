package cn.yunhe.mapper;

import cn.yunhe.pojo.RoleModulePExample;
import cn.yunhe.pojo.RoleModulePKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleModulePMapper {
    int countByExample(RoleModulePExample example);

    int deleteByExample(RoleModulePExample example);

    int deleteByPrimaryKey(RoleModulePKey key);

    int insert(RoleModulePKey record);

    int insertSelective(RoleModulePKey record);

    List<RoleModulePKey> selectByExample(RoleModulePExample example);

    int updateByExampleSelective(@Param("record") RoleModulePKey record, @Param("example") RoleModulePExample example);

    int updateByExample(@Param("record") RoleModulePKey record, @Param("example") RoleModulePExample example);
}