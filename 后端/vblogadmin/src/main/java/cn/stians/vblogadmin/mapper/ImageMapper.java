package cn.stians.vblogadmin.mapper;


import cn.stians.vblogadmin.model.Image;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImageMapper {

//根据文章的id查找文章图片
   List<Image> findImageByArticleId(int id);

   int updateImage(@Param("userId") int userId, @Param("url") String url);
   //根据url来查找刚刚存放的图片信息
   List<Image> findImageByUrl(@Param("userId") int userId, @Param("url") String url);
}