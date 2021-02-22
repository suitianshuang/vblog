package cn.stians.vblog.mapper;

import cn.stians.vblog.model.Role;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);


    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}