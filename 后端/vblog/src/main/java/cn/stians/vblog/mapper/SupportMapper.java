package cn.stians.vblog.mapper;

import cn.stians.vblog.model.Support;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SupportMapper {

//根据文章的id查找support表中的顶踩信息
   List<Support> findSupportsByArticleId(int id);


//   查出当前登录的人有没有点赞当前查出的文章.


//    将用户顶踩的文章id和用户id存到support表中
    int support(Support support);
//    查一下用户对这个文章有没有操作过

    @Select("select count(*) from support where article_id=#{articleId} and user_id = #{userId}")
    int findSupport(Support support);


    int updateType(Support support);

    @Select("select Count(*) from support where user_id=#{userId} and  create_time > #{todayTime} ")
    int getTodayDingCount(@Param("userId") int userId,@Param("todayTime")Long todayTime);

    @Select("delete from support where article_id = #{articleId}")
    void deleteSupport(Integer articleId);
}