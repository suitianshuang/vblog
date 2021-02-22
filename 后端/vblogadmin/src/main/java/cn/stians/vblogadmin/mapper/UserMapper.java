package cn.stians.vblogadmin.mapper;

import cn.stians.vblogadmin.model.Userinfo;
import cn.stians.vblogadmin.model.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;

import java.util.Date;
import java.util.List;

public interface UserMapper {

//根据文章获取users表的信息
    Users findUserByArticleId(int id);

//    根据关注表的follow_id来查用户信息
    Users findUsersById(int followId);
//    根据评论表的userid 来获得 user信息
    Users findUsersByCommentId(int userId);


    int checkEmail(String email);
//保存用户注册信息
    int saveUserMassage(Users users);


//查询出users表和userinfo表中的信息
//    Users userLogin(@Param("email") String email,@Param("password") String password);

    int updateUserpic(@Param("userpic") String url, @Param("id") int id);


    int updatePassword(@Param("password") String newPassword, @Param("email") String email);

    List<Users> getSearchUsersList(String keyWord);

    Users getUserInfo(int userId);

    int checkUsername(String username);

    Users findUserByEmail(String email);

    Users findByUsername(String username);
    @Select("select * from users where id = #{userId}")
    Users findById(String userId);

    List<Users> searchUser(String keyWords);

    List<Users> getUsersByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("user") Users user, @Param("userinfo")Userinfo userinfo, @Param("time") Long[] time);

    Integer getTotal( @Param("user")  Users user,@Param("userinfo")Userinfo userinfo,@Param("time") Long[] time);

    int updateEnable(@Param("id") Integer id, @Param("isenable") Integer isenable);
}