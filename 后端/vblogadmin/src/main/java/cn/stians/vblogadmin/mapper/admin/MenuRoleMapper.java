package cn.stians.vblogadmin.mapper.admin;


import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuRoleMapper  {

    List<Integer> getMenuByRid(Integer id);

    void deleteMenuByRid(Integer rid);

    int  updateMenuRole(@Param("rid") Integer rid,@Param("mids") Integer[] mids);
}
