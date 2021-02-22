package cn.stians.vblogadmin.service;

import cn.stians.vblogadmin.mapper.AdminsMapper;
import cn.stians.vblogadmin.model.Admins;
import cn.stians.vblogadmin.utils.AdminUtil;
import cn.stians.vblogadmin.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminsMapper adminsMapper;
    public List<Admins> getAllAdmins(String keyWords){

        return adminsMapper.getAllAdmins(AdminUtil.getCurrentAdmin().getId(),keyWords);
    }

    public Integer updateAdmin(Admins admins) {
        return adminsMapper.updateByPrimaryKeySelective(admins);

    }

    @Transactional
    public boolean updateAdminRoles(Integer id, Integer[] ids) {
        adminsMapper.deleteAdminRoles(id);
        return adminsMapper.updateAdminRoles(id,ids)==ids.length;
    }

    public int deleteAdmin(Integer id) {
        return adminsMapper.deleteAdmin(id);
    }
}
