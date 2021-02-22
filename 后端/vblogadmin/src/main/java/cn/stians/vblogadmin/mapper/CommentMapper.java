package cn.stians.vblogadmin.mapper;

import cn.stians.vblogadmin.model.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper {

    int checkParentId(Comment comment);

    int updateById(Integer parentId);

    List<Comment> getCommentList(int articleId);

    int insertCurrentComment(@Param("userId") Integer userId, @Param("parentId") Integer parentId, @Param("message") String message, @Param("createTime") Long createTime, @Param("articleId") Integer articleId);

    @Select("select count(*) from comment where user_id =#{userId}")
    int getCommentCount(int userId);

    List<Comment> findCommentByArticleId(int id);

    void deleteComment(Integer articleId);
}