package cn.stians.vblog.controller;

import cn.stians.vblog.config.loginConfig.UserLoginToken;
import cn.stians.vblog.model.BlackList;
import cn.stians.vblog.service.BlackListService;
import cn.stians.vblog.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blackList")
public class BlackListController {

    @Autowired
    BlackListService blackListService;
    @UserLoginToken
    @PostMapping("/addblaclist")
    public RespBean addBlackList(@RequestBody BlackList blackList){
        int result = blackListService.addBlackList(blackList);
        if(result==1){
            return RespBean.ok("加入黑名单成功~",result);
        }
        return RespBean.error("用户已经在黑名单中了~");
    }
    @UserLoginToken
    @PostMapping("/removeblacklist")
    public RespBean removeBlacklist(@RequestBody BlackList blackList){
        int result = blackListService.removeBlacklist(blackList);
        if(result==1){
            return RespBean.ok("移除黑名单成功~",result);
        }
        return RespBean.error("用户还没有在黑名单中~");
    }

}
