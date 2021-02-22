package cn.stians.vblogadmin.config;

import cn.stians.vblogadmin.model.admin.Menu;
import cn.stians.vblogadmin.model.admin.RoleAdmin;
import cn.stians.vblogadmin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/*
* 这个类的作用是， 根据用户传来的请求地址 ，分析出请求需要什么角色
* */
@Component
public class MyFilter implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;
//比对的工具
    AntPathMatcher antPathMatcher= new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
          String requestUrl = ((FilterInvocation )o).getRequestUrl();
        List<Menu> menus = menuService.getAllMenusWithRole();

        for (Menu menu : menus) {
//            /第一个参数是数据库中url的规则，第二个参数是要进行比对的url
            if (antPathMatcher.match(menu.getUrl(), requestUrl)){
                List<RoleAdmin> roles = menu.getRoles();
                String [] str = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    str[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(str);
            }
        }
//        没匹配上url 给一个标记
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
