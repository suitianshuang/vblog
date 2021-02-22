package cn.stians.vblogadmin.controller.topic;

import cn.stians.vblogadmin.model.RespPageBean;
import cn.stians.vblogadmin.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/topic/basic")
public class TopicController {
    @Autowired
    TopicService topicService;

    @GetMapping("/")
    public RespPageBean getTopicByPage(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size){
        return topicService.getTopicByPage(page,size);
    }

}
