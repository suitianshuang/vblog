package cn.stians.vblogadmin.mapper;

import cn.stians.vblogadmin.model.Fans;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FansMapper {

    List<Fans> findFansByUserId(int id);

//先查新一下是否关注过
    @Select("select count(*) from fans where follow_id=#{followId} and user_id = #{userId}")
    int findFens(Fans fans);
    int follow(Fans fans);
//获取我关注的人的列表
    List<Fans> getFallow(int id);
//获取关注我的人的列表
    List<Fans> getFansList(int id);
//获取互相关注的人的列表
    List<Fans> getFriendsList(int id);
    @Select("select count(*) from fans where user_id =#{userId}")
    int getFollowCount(int userId);
    @Select("select count(*) from fans where follow_id = #{userId}")
    int getFensCount(int userId);

    int getFriendCount(int userId);

    int findRelation(@Param("userId") int userId, @Param("visitId") int visitId);

    int unFollow(Fans fans);

}