package cn.stians.vblogadmin.mapper;

import cn.stians.vblogadmin.model.Article;
import cn.stians.vblogadmin.model.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicMapper {


    List<Topic> getHotTopic();

    List<Topic> getTopicListByTopicClassId(int id);

    List<Topic> searchTopic(String keyWord);

    int topicCreate(Topic topic);

    Topic getOneTopic(String url);

    void insertNewTopic(@Param("topicTitle") String topicTitle, @Param("url") String url, @Param("createTime") Long createTime);

    int findTopicId(@Param("topicTitle") String topicTitle, @Param("url") String url, @Param("createTime") Long createTime);

    void increaseTopicNum(int topicId);

    void updateTodayNum();

    List<Article> getTopicByPage(@Param("page") Integer page,@Param("size") Integer size);

    Integer getTotal();
}