package cn.stians.vblog.service;

import cn.stians.vblog.mapper.ArticleClassMapper;
import cn.stians.vblog.model.ArticleClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ArticleClassifyService {

    @Autowired
     ArticleClassMapper articleClassMapper;

    public List<ArticleClass> findAllArticleClass(){
        return articleClassMapper.findAllArticleClass();
    }

}
