package cn.stians.vblogadmin.mapper;

import org.apache.ibatis.annotations.Param;

public interface CollectionMapper {

    int collectionArticle(@Param("userId") int userId, @Param("articleId") int articleId, @Param("createTime") Long createTime);

    int cancelCollection(@Param("userId") int userId, @Param("articleId") int articleId);

}
