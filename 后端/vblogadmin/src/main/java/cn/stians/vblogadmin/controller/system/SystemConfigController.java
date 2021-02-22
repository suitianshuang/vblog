package cn.stians.vblogadmin.controller.system;

import cn.stians.vblogadmin.model.admin.Menu;
import cn.stians.vblogadmin.service.MenuService;
import cn.stians.vblogadmin.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SystemConfigController {

    @Autowired
    MenuService menuService;
    @GetMapping("/config/menus")
    public List<Menu> getMenus(){
        return menuService.getMenusByAdminId();
    }
}
