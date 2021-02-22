package cn.stians.vblog.controller;

import cn.stians.vblog.config.loginConfig.UserLoginToken;
import cn.stians.vblog.model.Sign;
import cn.stians.vblog.service.UserSignService;
import cn.stians.vblog.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign")
public class SignController {
    @Autowired
    UserSignService signService;

    @UserLoginToken
    @PostMapping("/usersign")
    public RespBean userSign(@RequestBody Sign sign){
        Sign s =  signService.userSign(sign);
        if (s !=null){
            return RespBean.ok("签到成功",s);
        }
        return RespBean.error("今天已经签到过了");
    }

}
