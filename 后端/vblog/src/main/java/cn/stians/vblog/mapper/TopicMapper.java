package cn.stians.vblog.mapper;

import cn.stians.vblog.model.Article;
import cn.stians.vblog.model.Topic;

import java.util.List;

import net.sf.jsqlparser.statement.select.Top;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TopicMapper {


    List<Topic> getHotTopic();

    List<Topic> getTopicListByTopicClassId(int id);

    List<Topic> searchTopic(String keyWord);

    int topicCreate(Topic topic);

    Topic getOneTopic(String url);

    void insertNewTopic(@Param("topicTitle") String topicTitle,@Param("url") String url,@Param("createTime") Long createTime);

    int findTopicId(@Param("topicTitle") String topicTitle,@Param("url") String url,@Param("createTime") Long createTime);

    void increaseTopicNum(int topicId);

    void updateTodayNum();
}