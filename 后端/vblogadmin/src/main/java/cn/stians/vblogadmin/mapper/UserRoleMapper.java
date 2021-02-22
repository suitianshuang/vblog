package cn.stians.vblogadmin.mapper;

import cn.stians.vblogadmin.model.UserRole;

public interface UserRoleMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);



    UserRole selectByPrimaryKey(Integer id);



    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}