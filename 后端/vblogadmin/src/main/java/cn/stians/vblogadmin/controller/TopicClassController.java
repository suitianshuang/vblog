package cn.stians.vblogadmin.controller;

import cn.stians.vblogadmin.model.TopicClass;
import cn.stians.vblogadmin.service.TopicClassService;
import cn.stians.vblogadmin.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic/basic")
public class TopicClassController {
    @Autowired
    TopicClassService topicClassService;

    @GetMapping("/topicclass")
    public RespBean findAll(){
        List<TopicClass> topicClassList = topicClassService.getTopicClassList();
        if (topicClassList!=null){
            return RespBean.ok(topicClassList);
        }
        return RespBean.error("还没有话题分类！");
    }
    @DeleteMapping("/{id}")
    public RespBean deleteTopicClass(@PathVariable Integer id){
        topicClassService.deleteTopicClass(id);
        return RespBean.ok("删除话题成功");
    }
    @PostMapping("/")
    public RespBean updateTopicClass(@RequestBody TopicClass topicClass){
        topicClassService.updataTopicClassInEnable(topicClass);
        return RespBean.ok("修改分类成功");
    }
}
