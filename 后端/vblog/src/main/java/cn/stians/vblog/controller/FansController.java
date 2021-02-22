package cn.stians.vblog.controller;

import cn.stians.vblog.config.loginConfig.UserLoginToken;
import cn.stians.vblog.model.Fans;
import cn.stians.vblog.service.FansService;
import cn.stians.vblog.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fans")
public class FansController {
    @Autowired
    FansService fansService;

    @UserLoginToken
    @PostMapping("/follow")
    public RespBean follow(@RequestBody Fans fans){
        if(fansService.follow(fans)==1){
            return RespBean.ok("关注成功");
        }
        return RespBean.error("你已经关注过了");
    }
    @UserLoginToken
    @GetMapping("/followList")
    public RespBean getFallow(int id,int pageNum){
        if(fansService.getFallow(id,pageNum)!=null){
            return RespBean.ok("获取成功",fansService.getFallow(id,pageNum));
        }
        return RespBean.error("获取失败");
    }
    @UserLoginToken
    @GetMapping("/fansList")
    public RespBean getFansList(int id,int pageNum){
        if(fansService.getFansList(id,pageNum)!=null){
            return RespBean.ok("获取成功",fansService.getFansList(id,pageNum));
        }
        return RespBean.error("获取失败");
    }
    @UserLoginToken
    @GetMapping("/friendsList")
    public RespBean getFriendsList(int id,int pageNum){
        if(fansService.getFriendsList(id,pageNum)!=null){
            return RespBean.ok("获取成功",fansService.getFriendsList(id,pageNum));
        }
        return RespBean.error("获取失败");

    }
    @UserLoginToken
    @GetMapping("/article/followList")
    public RespBean getFollowListArticle(int userId,int pageNum){
        if(fansService.getFollowListArticle(userId,pageNum)!=null){
            return RespBean.ok("获取成功",fansService.getFollowListArticle(userId,pageNum));
        }
        return RespBean.error("获取失败");
    }
    @UserLoginToken
    @PostMapping("/unfollow")
    public RespBean unFollow(@RequestBody Fans fans){
        if(fansService.unFollow(fans)==1){
            return RespBean.ok("取消关注成功");
        }
        return RespBean.error("你还没有关注该用户");
    }

}
