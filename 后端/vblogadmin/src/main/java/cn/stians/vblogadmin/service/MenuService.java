package cn.stians.vblogadmin.service;

import cn.stians.vblogadmin.mapper.admin.MenuMapper;
import cn.stians.vblogadmin.mapper.admin.MenuRoleMapper;
import cn.stians.vblogadmin.model.Admins;
import cn.stians.vblogadmin.model.admin.Menu;
import cn.stians.vblogadmin.model.admin.MenuRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;
    @Autowired
    MenuRoleMapper menuRoleMapper;

    public  List<Integer> getMenuByRid(Integer id) {
        return menuRoleMapper.getMenuByRid(id);
    }


    public List<Menu> getMenusByAdminId() {
        return menuMapper.getMenusByAdminId(((Admins) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

//   加一个缓存 因为变得比较少，但现在还在改。。。
//    @Cacheable
    public List<Menu> getAllMenusWithRole (){
        return menuMapper.getAllMenusWithRole();
    }
    @Transactional
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.deleteMenuByRid(rid);
        if(mids==null){
           return true;
        }
        int result = menuRoleMapper.updateMenuRole(rid,mids);
        return result == mids.length;
    }

}
