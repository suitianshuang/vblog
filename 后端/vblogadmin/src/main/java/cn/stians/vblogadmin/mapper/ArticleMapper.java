package cn.stians.vblogadmin.mapper;


import cn.stians.vblogadmin.model.Article;
import cn.stians.vblogadmin.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleMapper {

    List<Article> findArticleByClassifyName(int id);

    List<Article> ArticleSearchByKeyWords(String keyWord);

    List<Article> getArticleByTopicId(int id);

    @Select("update article set ding_count = ding_count+1 where id = #{id} ")
    void updateDingCount(int id);

    @Select("update article set cai_count = cai_count+1 where id = #{id} ")
    void updateCaiCount(int id);

    //   能调用此方法说明用户之前就踩过了，现在要把顶的数目加一 把踩减一
    @Select("update article set ding_count = ding_count+1 ,cai_count=cai_count-1 where id = #{id}")
    void updateDingCount1(int id);

    //   能调用此方法说明用户之前就顶过了，现在要把踩的数目加一 把顶减一
    @Select("update article set cai_count = cai_count+1 ,ding_count=ding_count-1 where id = #{id}")
    void updateCaiCount1(int id);

    //    id,userid,title,titlepic,content,isopen,topic_id,article_class_id
    int publishArticle(@Param("userId") int userId, @Param("title") String title, @Param("titlepic") String titlepic, @Param("content") String content, @Param("isopen") int isopen, @Param("topicId") int topicId, @Param("articleClassId") int articleClassId, @Param("createTime") Long createTime, @Param("rewardNum") int rewardNum);

    @Select("select id from article where titlepic =#{titlepic}")
    Article findArticleIdByTitlepic(String titlepic);

    int insertImageIdAndArticleId(@Param("imageId") int imageId, @Param("articleId") int articleId);

    int getArticleClassId(int articleId);

    int getTopicClass(int articleId);

    @Select("update article set comment_count = comment_count+1 where id = #{articleId}")
    void updateCommentNum(Integer articleId);

    @Select("select id from comment where create_time = #{createTime} and article_id =#{articleId} and user_id =#{userId}")
    int findId(@Param("createTime") Long createTime, @Param("articleId") Integer articleId, @Param("userId") Integer userId);

    //获取当前关注用户的文章列表
    List<Article> getFollowListArticle(int userId);

    @Select("select Count(*) from article where  userid = #{userId}")
    int getArticleCount(int userId);

    int getTodayArticleCount(@Param("userId") int userId, @Param("todayTime") Long todayTime);

//获取自己的最热文章
    List<Article> getMyHottestArticle(int userId);
    //获取自己的最新文章
    List<Article> getMyNewestArticle(int userId);

    //获取他人的最热文章
    List<Article> getOthersHottestArticle(int userId);

    //获取他人的最新文章
    List<Article> getOthersNewestArticle(int userId);

    int ArticleDelete(@Param("userId") Integer userId, @Param("articleId") Integer articleId);

    @Select("update article set collectionnum = collectionnum+1 where id =#{articleId}")
    void addCollectionNum(int articleId);
    @Select("update article set collectionnum = collectionnum-1 where id =#{articleId}")
    void cutCollectionNum(int articleId);

    List<Article> getCollectionList(int userId);

    @Select("update  article set pageview_num = pageview_num+1 where id  = #{articleId}")
    void updateViewNum(int articleId);

    List<Article> getUserComment(int userId);

    int updateIsAccepted(int articleId);


    List<Article> getArticleByPage(@Param("page") Integer page,@Param("size") Integer size);

    Integer getTotal();
}

