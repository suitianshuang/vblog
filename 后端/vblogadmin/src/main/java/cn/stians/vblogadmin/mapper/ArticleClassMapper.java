package cn.stians.vblogadmin.mapper;

import cn.stians.vblogadmin.model.Article;
import cn.stians.vblogadmin.model.ArticleClass;

import java.util.List;


public interface ArticleClassMapper {

    List<ArticleClass> findAllArticleClass();

    List<Article> findArticleByClassifyName();
}