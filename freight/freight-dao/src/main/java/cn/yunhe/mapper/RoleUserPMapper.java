package cn.yunhe.mapper;

import cn.yunhe.pojo.RoleUserPExample;
import cn.yunhe.pojo.RoleUserPKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleUserPMapper {
    int countByExample(RoleUserPExample example);

    int deleteByExample(RoleUserPExample example);

    int deleteByPrimaryKey(RoleUserPKey key);

    int insert(RoleUserPKey record);

    int insertSelective(RoleUserPKey record);

    List<RoleUserPKey> selectByExample(RoleUserPExample example);

    int updateByExampleSelective(@Param("record") RoleUserPKey record, @Param("example") RoleUserPExample example);

    int updateByExample(@Param("record") RoleUserPKey record, @Param("example") RoleUserPExample example);
}