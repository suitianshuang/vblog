package cn.stians.vblogadmin.service;

import cn.stians.vblogadmin.mapper.ArticleMapper;
import cn.stians.vblogadmin.model.Article;
import cn.stians.vblogadmin.model.RespPageBean;
import cn.stians.vblogadmin.model.Userinfo;
import cn.stians.vblogadmin.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    public RespPageBean getArticleByPage(Integer page, Integer size) {

        if (page != null && size !=null) {
            page = (page-1)*size;
        }
        List<Article> data = articleMapper.getArticleByPage(page,size);
        Integer total = articleMapper.getTotal();
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;


    }
}
