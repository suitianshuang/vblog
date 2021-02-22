package cn.stians.vblogadmin.controller.system;

import cn.stians.vblogadmin.model.admin.Menu;
import cn.stians.vblogadmin.model.admin.RoleAdmin;
import cn.stians.vblogadmin.service.MenuService;
import cn.stians.vblogadmin.service.RoleService;
import cn.stians.vblogadmin.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统管理接口
 * 2020年4月26日12:15:26
 */
@RestController
@RequestMapping("/sys/basic")
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;
    @GetMapping("/role")
    public RespBean getRoleList(){
        List<RoleAdmin> roleList = roleService.getRoleList();
        if (roleList!=null){
            return RespBean.ok(roleList);
        }
        return RespBean.error("获取列表失败");
    }
    @GetMapping("/menus/")
    public RespBean getAllMenu(){
        List<Menu> allMenus = roleService.getAllMenus();
        if (allMenus!= null){
            return RespBean.ok(allMenus);
        }
        return RespBean.error("获取权限组失败！");
    }

    @GetMapping("/rid/{id}")
    public List<Integer> getMenuByRid(@PathVariable Integer id){
        return menuService.getMenuByRid(id);
    }

    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid,Integer mids []){
        if(menuService.updateMenuRole(rid,mids)){
            return RespBean.ok("修改权限成功");
        }
        return RespBean.error("修改权限失败");
    }
    @PostMapping("/role")
    public RespBean insetRole(@RequestBody RoleAdmin roleAdmin){

        if(!roleAdmin.name.startsWith("ROLE_")){
            roleAdmin.setName("ROLE_"+roleAdmin.name);
        }
        if (roleService.insertRole(roleAdmin) !=null){
            return RespBean.ok("添加角色成功");
        }
        return RespBean.error("添加角色失败！");
    }
    @DeleteMapping("/role/{id}")
    public RespBean deleteRole(@PathVariable  Integer id){
         roleService.deleteRole(id);
         return RespBean.ok("删除成功");
    }


}
