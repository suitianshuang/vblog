package cn.stians.vblogadmin.mapper;

import cn.stians.vblogadmin.model.Admins;
import cn.stians.vblogadmin.model.admin.RoleAdmin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminsMapper {

    Admins loadAdminByUsername(String username);

    List<RoleAdmin> getAdminRolesById(Integer id);

    List<Admins> getAllAdmins(@Param("id") Integer id,@Param("keyWords") String keyWords);

    Integer updateByPrimaryKeySelective(Admins admins);

    void deleteAdminRoles(Integer id);

    int updateAdminRoles(@Param("id") Integer id, @Param("ids") Integer[] ids);

    int deleteAdmin(Integer id);

    List<Admins> getAllAdminsExceptCurrentAdmin(Integer id);
}
