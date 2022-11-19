package cn.yunhe.mapper;

import cn.yunhe.pojo.UserinfoP;
import cn.yunhe.pojo.UserinfoPExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserinfoPMapper {
    int countByExample(UserinfoPExample example);

    int deleteByExample(UserinfoPExample example);

    int deleteByPrimaryKey(String userInfoId);

    int insert(UserinfoP record);

    int insertSelective(UserinfoP record);

    List<UserinfoP> selectByExample(UserinfoPExample example);

    UserinfoP selectByPrimaryKey(String userInfoId);

    int updateByExampleSelective(@Param("record") UserinfoP record, @Param("example") UserinfoPExample example);

    int updateByExample(@Param("record") UserinfoP record, @Param("example") UserinfoPExample example);

    int updateByPrimaryKeySelective(UserinfoP record);

    int updateByPrimaryKey(UserinfoP record);
}