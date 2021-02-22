package cn.stians.vblogadmin.controller.article;

import cn.stians.vblogadmin.model.Article;
import cn.stians.vblogadmin.model.RespPageBean;
import cn.stians.vblogadmin.model.Userinfo;
import cn.stians.vblogadmin.model.Users;
import cn.stians.vblogadmin.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/article/basic")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    public RespPageBean getArticleByPage(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer size){
        return articleService.getArticleByPage(page,size);
    }

}
