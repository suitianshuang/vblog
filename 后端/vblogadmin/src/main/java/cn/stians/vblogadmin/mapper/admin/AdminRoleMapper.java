package cn.stians.vblogadmin.mapper.admin;


import cn.stians.vblogadmin.model.admin.RoleAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRoleMapper extends JpaRepository<RoleAdmin,Integer> {
}
