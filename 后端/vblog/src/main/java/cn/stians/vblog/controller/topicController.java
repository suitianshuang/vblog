package cn.stians.vblog.controller;

import cn.stians.vblog.config.loginConfig.UserLoginToken;
import cn.stians.vblog.model.Topic;
import cn.stians.vblog.service.TopicService;
import cn.stians.vblog.utils.RespBean;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.UUID;

@RestController
public class topicController {
    @Autowired
    TopicService topicService;

    @GetMapping("/topic/hotTopic")
    public RespBean getHotTopic(){
        if(topicService.getHotTopic()!=null){
            return RespBean.ok("获取成功",topicService.getHotTopic());
        }
        return RespBean.error("获取失败");
    }

    @GetMapping("/topic/topiclist")
    public RespBean getTopicListByTopicClassId(int id,int pageNum){
        if(topicService.getTopicListByTopicClassId(id,pageNum)!=null){
            return RespBean.ok("获取成功",topicService.getTopicListByTopicClassId(id,pageNum));
        }
        return RespBean.error("获取失败");
    }
    @GetMapping("/topic/article")
    public RespBean getArticleByTopicId(int id,int pageNum){
        if(topicService.getArticleByTopicId(id,pageNum)!=null){
            return RespBean.ok("获取成功",topicService.getArticleByTopicId(id,pageNum));
        }
        return RespBean.error("获取失败");
    }

    @PostMapping("/search/topic")
    public RespBean searchTopic(String keyWord,int pageNum){
        if(topicService.searchTopic(keyWord,pageNum)!=null){
            return RespBean.ok("搜索成功",topicService.searchTopic(keyWord,pageNum));
        }
        return RespBean.error("搜索失败");
    }
    @UserLoginToken
    @PostMapping("/topic/create")
    public RespBean topicCreate(@RequestBody Topic topic, HttpServletRequest req){
//        给话题一个随机默认图片
        String titlePic[] = {"/topic1.jpg","/topic2.jpg"};
        Random random = new Random();
        int i = random.nextInt(2);
        String titlePicName = titlePic[i];
        String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() +titlePicName;
        topic.setTitlepic(url);
        Topic topic1 = topicService.topicCreate(topic, url);
        if(topic1!=null){
            return RespBean.ok("新建话题成功",topic1);
        }
        return RespBean.error("服务器被吃了~");
    }

}
