package cn.stians.vblog.service;

import cn.stians.vblog.mapper.UserMapper;
import cn.stians.vblog.mapper.UserinfoMapper;
import cn.stians.vblog.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserInfoService {
    @Autowired
    UserinfoMapper userinfoMapper;
    @Autowired
    UserMapper userMapper;
    public int updateMessage(@RequestBody Userinfo userinfo) {
        System.out.println(userinfo.toString());
        return userinfoMapper.updateMessage(userinfo);
    }

    public int updateUserName(Userinfo userinfo) {
        return userinfoMapper.updateMessage(userinfo);
    }
}
