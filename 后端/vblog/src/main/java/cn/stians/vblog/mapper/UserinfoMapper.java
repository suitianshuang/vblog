package cn.stians.vblog.mapper;

import cn.stians.vblog.model.Userinfo;

import java.util.List;

import cn.stians.vblog.model.Users;
import org.apache.ibatis.annotations.Param;

public interface UserinfoMapper {

//    查询一条users表中信息
    Userinfo findAll(int id);
//    根据userid来查
    Userinfo findUserInfoByUserId(int id);

    int updateMessage(Userinfo userinfo);


    void saveUserMassage(Users users);
}