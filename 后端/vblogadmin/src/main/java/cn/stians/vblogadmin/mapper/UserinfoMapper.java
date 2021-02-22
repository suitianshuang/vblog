package cn.stians.vblogadmin.mapper;

import cn.stians.vblogadmin.model.Userinfo;
import cn.stians.vblogadmin.model.Users;

public interface UserinfoMapper {

//    查询一条users表中信息
    Userinfo findAll(int id);
//    根据userid来查
    Userinfo findUserInfoByUserId(int id);

    int updateMessage(Userinfo userinfo);


    void saveUserMassage(Users users);
}