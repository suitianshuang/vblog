package cn.stians.vblog.mapper;

import cn.stians.vblog.model.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionMapper {

    int collectionArticle(@Param("userId") int userId,@Param("articleId") int articleId, @Param("createTime") Long createTime);

    int cancelCollection(@Param("userId") int userId,@Param("articleId") int articleId);

}
