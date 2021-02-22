package cn.stians.vblog.mapper;

import cn.stians.vblog.model.UserRole;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);



    UserRole selectByPrimaryKey(Integer id);



    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}