package cn.stians.vblog.service;

import cn.stians.vblog.mapper.ArticleMapper;
import cn.stians.vblog.mapper.TopicMapper;
import cn.stians.vblog.model.Article;
import cn.stians.vblog.model.Topic;
import cn.stians.vblog.utils.CurrentTimeUtil;
import cn.stians.vblog.utils.RespBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TopicService {
    @Autowired
    TopicMapper topicMapper;
    @Autowired
    ArticleMapper articleMapper;

    public List<Topic> getHotTopic(){
        return topicMapper.getHotTopic();
    }

    public PageInfo getTopicListByTopicClassId(int id, int pageNum) {

        PageHelper.startPage(pageNum,10);
        List<Topic> lists = topicMapper.getTopicListByTopicClassId(id);
        PageInfo<Topic> pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    public PageInfo getArticleByTopicId(int id, int pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Article> lists = articleMapper.getArticleByTopicId(id);
        PageInfo<Topic> pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    public PageInfo searchTopic(String keyWord, int pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Topic> lists = topicMapper.searchTopic(keyWord);
        PageInfo<Topic> pageInfo = new PageInfo(lists);
        return pageInfo;
    }
    @Transactional
    public Topic topicCreate(Topic topic,String url) {
        Long createTime = CurrentTimeUtil.getCurrentTime();
        topic.setCreateTime(createTime);
        if (topicMapper.topicCreate(topic)==1){
          return   topicMapper.getOneTopic(url);
        }

        return null;
    }

    public int insertNewTopic(String topicTitle,String url) {
        Long createTime = CurrentTimeUtil.getCurrentTime();
        topicMapper.insertNewTopic(topicTitle,url,createTime);
        return topicMapper.findTopicId(topicTitle,url,createTime);
    }

    public void increaseTopicNum(int topicId) {
        topicMapper.increaseTopicNum(topicId);
    }
}
