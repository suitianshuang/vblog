package cn.stians.vblogadmin.service;

import cn.stians.vblogadmin.mapper.admin.AdminRoleMapper;
import cn.stians.vblogadmin.mapper.admin.MenuMapper;
import cn.stians.vblogadmin.model.admin.Menu;
import cn.stians.vblogadmin.model.admin.RoleAdmin;
import cn.stians.vblogadmin.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    AdminRoleMapper adminRoleMapper;
    @Autowired
    MenuMapper menuMapper;
    public List<RoleAdmin> getRoleList(){
        return adminRoleMapper.findAll();
    }

    public List<Menu> getAllMenus() {
      return   menuMapper.getAllMenusAndChildren();
    }

    public RoleAdmin insertRole(RoleAdmin roleAdmin) {
        RoleAdmin roleAdmin1 = adminRoleMapper.saveAndFlush(roleAdmin);
        return roleAdmin1;
    }

    public void deleteRole(Integer id) {
        adminRoleMapper.deleteById(id);
    }
}
