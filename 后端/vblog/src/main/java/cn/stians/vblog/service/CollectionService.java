package cn.stians.vblog.service;

import cn.stians.vblog.mapper.ArticleMapper;
import cn.stians.vblog.mapper.CollectionMapper;
import cn.stians.vblog.model.Article;
import cn.stians.vblog.utils.CurrentTimeUtil;
import cn.stians.vblog.utils.RespBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CollectionService {
    @Autowired
    CollectionMapper collectionMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Transactional
    public int collectionArticle(int userId, int articleId) {
        Long createTime = CurrentTimeUtil.getCurrentTime();
        if( collectionMapper.collectionArticle(userId,articleId,createTime)==1){
            articleMapper.addCollectionNum(articleId);
            return 1;
        }
        return 0;
    }

    @Transactional
    public int cancelCollection(int userId, int articleId) {

        if( collectionMapper.cancelCollection(userId,articleId)==1){
            articleMapper.cutCollectionNum(articleId);
            return 1;
        }
        return 0;
    }


    public PageInfo<Article> getCollectionList(int userId, int pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Article> list = articleMapper.getCollectionList(userId);
        PageInfo<Article> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
