package cn.stians.vblog.controller;

import cn.stians.vblog.service.TopicClassifyService;
import cn.stians.vblog.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic")
public class topicClassifyController {
    @Autowired
    TopicClassifyService topicClassifyService;

    @GetMapping("/topicClass")
    public RespBean getAllTopicClass(){
        if(topicClassifyService.GetAllTopicClass()!=null){
            return RespBean.ok("获取成功",topicClassifyService.GetAllTopicClass());
        }
        return RespBean.error("获取失败");
    }

}


