package cn.stians.vblog.service;

import cn.stians.vblog.mapper.BlackListMapper;
import cn.stians.vblog.mapper.FansMapper;
import cn.stians.vblog.mapper.UserMapper;
import cn.stians.vblog.model.Users;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    FansMapper fansMapper;
    @Autowired
    BlackListMapper blackListMapper;

    public Users findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    public Users findByUsername(Users users) {
        String username = users.getUsername();
        return userMapper.findByUsername(username);
    }


    //    修改头像
    public int updateUserpic(String url, int id) {
        return userMapper.updateUserpic(url, id);
    }

    //修改密码
    public int updatePassword(String newPassword, String email) {
        return userMapper.updatePassword(newPassword, email);
    }


    //模糊查询用户
    public PageInfo<Users> getSearchUsersList(String keyWord, int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Users> lists = userMapper.getSearchUsersList(keyWord);
        PageInfo<Users> pageInfo = new PageInfo(lists);
        return pageInfo;

    }


    public Map<String,Object> getUserInfo(int userId,int visitId) {
        //          查询当前用户是不是要访问的用户的fans
//        查询粉丝列表
        HashMap<String,Object> map = new HashMap<>();
        int isfans = fansMapper.findRelation(userId,visitId);
        int isblack = blackListMapper.findRelationBlack(userId,visitId);

        map.put("users",userMapper.getUserInfo(userId));
        map.put("isfans",isfans);
        map.put("isblack",isblack);
        return map;
    }

    public Users findById(String userId) {
        return userMapper.findById(userId);
    }
}
