package cn.stians.vblog.controller;

import cn.stians.vblog.config.loginConfig.UserLoginToken;
import cn.stians.vblog.model.Userinfo;
import cn.stians.vblog.service.UserInfoService;
import cn.stians.vblog.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @UserLoginToken
    @PostMapping("/updateMessage")
    public RespBean updateMessage(@RequestBody Userinfo userinfo){

        if(userInfoService.updateMessage(userinfo)==1){
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }

}
