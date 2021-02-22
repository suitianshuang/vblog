package cn.stians.vblog.controller;

import cn.stians.vblog.config.MyException;
import cn.stians.vblog.config.loginConfig.UserLoginToken;
import cn.stians.vblog.service.ArticleClassifyService;
import cn.stians.vblog.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleClassifyController {
    @Autowired
    ArticleClassifyService articleClassifyService;

//    @UserLoginToken
    @GetMapping("/articleClassify")
    public RespBean findAllArticleClass(){
        if(articleClassifyService.findAllArticleClass()!=null){
            return RespBean.ok("success",articleClassifyService.findAllArticleClass());
        }
        return RespBean.error("fail");
    }
}
