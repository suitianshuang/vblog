package cn.stians.vblog.service;

import cn.stians.vblog.mapper.TopicClassMapper;
import cn.stians.vblog.model.TopicClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicClassifyService {

    @Autowired
    TopicClassMapper topicClassifyMapper;

    public List<TopicClass> GetAllTopicClass(){
        return topicClassifyMapper.getAllTopicClass();
    }
}
