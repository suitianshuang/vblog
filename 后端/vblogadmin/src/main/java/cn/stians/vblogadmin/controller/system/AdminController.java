package cn.stians.vblogadmin.controller.system;

import cn.stians.vblogadmin.model.Admins;
import cn.stians.vblogadmin.model.admin.RoleAdmin;
import cn.stians.vblogadmin.service.AdminService;
import cn.stians.vblogadmin.service.RoleService;
import cn.stians.vblogadmin.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sys/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    RoleService roleService;
    @GetMapping("/")
    public RespBean getAllAdmins(String keyWords){
        List<Admins> allAdmins = adminService.getAllAdmins(keyWords);
        if ( allAdmins!= null) {
            return RespBean.ok(allAdmins);
        }
        return RespBean.error("获取失败！");
    }

    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admins admins){

        if (adminService.updateAdmin(admins) == 1) {
          return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }
    @GetMapping("/role")
    public RespBean getAllRoles(){
        List<RoleAdmin> roleList = roleService.getRoleList();
        if(roleList != null){
            return RespBean.ok(roleList);
        }
        return RespBean.error("获取失败");
    }
    @PutMapping("/role")
    public RespBean updateAdminRoles(Integer id,Integer ids[]){
        if(adminService.updateAdminRoles(id,ids)){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id){
        if(adminService.deleteAdmin(id)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}



