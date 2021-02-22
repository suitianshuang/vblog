package cn.stians.vblogadmin.service;

import cn.stians.vblogadmin.mapper.TopicClassMapper;
import cn.stians.vblogadmin.model.TopicClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicClassService {
    @Autowired
    TopicClassMapper topicClassMapper;

    public List<TopicClass> getTopicClassList(){
        return topicClassMapper.findAll();
    }
    public void deleteTopicClass(int id){
         topicClassMapper.deleteById(id);
    }
    public void updataTopicClassInEnable(TopicClass topicClass){
        topicClassMapper.saveAndFlush(topicClass);
    }
}
