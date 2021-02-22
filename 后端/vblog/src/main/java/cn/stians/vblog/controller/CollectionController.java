package cn.stians.vblog.controller;

import cn.stians.vblog.config.loginConfig.UserLoginToken;
import cn.stians.vblog.model.Article;
import cn.stians.vblog.service.CollectionService;
import cn.stians.vblog.utils.RespBean;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    CollectionService collectionService;
    @UserLoginToken
    @PostMapping("/article")
    public RespBean collectionArticle(@RequestBody JSONObject jsonObject){
        int userId = (int) jsonObject.get("userId");
        int articleId = (int) jsonObject.get("articleId");
        int result = collectionService.collectionArticle(userId, articleId);
        if (result==1){
            return RespBean.ok("收藏成功");
        }
        return RespBean.error("服务器被吃了~");
    }

    @UserLoginToken
    @PostMapping("/cancel")
    public RespBean cancelCollection(int userId,int articleId){
        int result = collectionService.cancelCollection(userId, articleId);
        if (result==1){
            return RespBean.ok("取消收藏成功");
        }
        return RespBean.error("这篇文章已经不存在了~");
    }

    @UserLoginToken
    @GetMapping("/collectionList")
    public RespBean getCollectionList(int userId,int pageNum){
        PageInfo<Article> collectionList = collectionService.getCollectionList(userId, pageNum);
        if (collectionList!=null){
            return RespBean.ok("获取收藏列表成功",collectionList);
        }
        return RespBean.error("服务器被吃了~");
    }
}
