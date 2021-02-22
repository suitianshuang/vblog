package cn.stians.vblog.mapper;

import cn.stians.vblog.model.Article;
import cn.stians.vblog.model.ArticleClass;

import java.util.List;


public interface ArticleClassMapper {

    List<ArticleClass> findAllArticleClass();

    List<Article> findArticleByClassifyName();
}