package cn.stians.vblog.controller;

import cn.stians.vblog.config.loginConfig.UserLoginToken;
import cn.stians.vblog.model.Article;
import cn.stians.vblog.model.Comment;
import cn.stians.vblog.service.ArticleService;
import cn.stians.vblog.service.CommentService;
import cn.stians.vblog.service.UserSignService;
import cn.stians.vblog.utils.RespBean;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;


    @UserLoginToken
    @PostMapping("/insertComment")
    public RespBean insertComment(@RequestBody Comment comment){

        int id = commentService.insertComment(comment);
        System.out.println(id);
        if(id != 0){
//            增加artilce 表中的文章评论量
            return RespBean.ok("评论成功",id);
        }
        return RespBean.error("该条评论已经被删除了");
    }
    @GetMapping("/commentList")
    public RespBean getCommentList(int articleId){
        List<Comment>  comment= commentService.getCommentList(articleId);
        if(comment!=null){
            return RespBean.ok("获取评论列表成功",comment);
        }
        return RespBean.error("获取评论失败");
    }
    @PostMapping("/getusercomment")
    public RespBean getUserComment( int userId, int pageNum){
                PageHelper.startPage(pageNum, 10);
         List<Article> list= commentService.getUserComment(userId,pageNum);
                PageInfo<Article> pageInfo = new PageInfo(list);

        if (list!=null){
             return RespBean.ok("获取成功",pageInfo);
         }
        return RespBean.error("还没有人评论");
    }

    @PostMapping("/accept")
    public RespBean acceptOthersComment(@RequestBody JSONObject jsonObject){
        int userId = (int) jsonObject.get("userId");
        int acceptedUserId = (int) jsonObject.get("acceptedUserId");
        int rewardNum=(int) jsonObject.get("rewardNum");
        int articleId=(int) jsonObject.get("articleId");
        int result = commentService.acceptOthersComment(userId,acceptedUserId,rewardNum,articleId);
        if(result ==1){
            return RespBean.ok("采纳成功",1);
        }
        return RespBean.error("服务器被吃了~");
    }


}
