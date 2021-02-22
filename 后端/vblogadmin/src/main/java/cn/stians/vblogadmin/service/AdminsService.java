package cn.stians.vblogadmin.service;

import cn.stians.vblogadmin.mapper.AdminsMapper;
import cn.stians.vblogadmin.model.Admins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminsService implements UserDetailsService {
    @Autowired
    AdminsMapper adminsMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admins admin = adminsMapper.loadAdminByUsername(username);
        if (admin == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println(admin.toString());

        admin.setRoles(adminsMapper.getAdminRolesById(admin.getId()));
        return admin;
    }
}
