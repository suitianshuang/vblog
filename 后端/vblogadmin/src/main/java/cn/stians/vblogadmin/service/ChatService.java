package cn.stians.vblogadmin.service;

import cn.stians.vblogadmin.mapper.AdminsMapper;
import cn.stians.vblogadmin.model.Admins;
import cn.stians.vblogadmin.utils.AdminUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    AdminsMapper adminsMapper;

    public List<Admins> getAllAdminsExceptCurrentAdmin() {
        return adminsMapper.getAllAdminsExceptCurrentAdmin(AdminUtil.getCurrentAdmin().getId());
    }
}
