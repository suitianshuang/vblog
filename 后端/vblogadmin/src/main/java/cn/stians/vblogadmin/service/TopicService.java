package cn.stians.vblogadmin.service;

import cn.stians.vblogadmin.mapper.TopicMapper;
import cn.stians.vblogadmin.model.Article;
import cn.stians.vblogadmin.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    @Autowired
    TopicMapper topicMapper;


    public RespPageBean getTopicByPage(Integer page, Integer size) {

        if (page != null && size !=null) {
            page = (page-1)*size;
        }
        List<Article> data = topicMapper.getTopicByPage(page,size);
        Integer total = topicMapper.getTotal();
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
}
