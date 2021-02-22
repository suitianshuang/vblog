package cn.stians.vblog.service;

import cn.stians.vblog.mapper.*;
import cn.stians.vblog.model.Article;
import cn.stians.vblog.model.Image;
import cn.stians.vblog.model.Support;
import cn.stians.vblog.utils.CurrentTimeUtil;
import cn.stians.vblog.utils.RespBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    SupportMapper supportMapper;
    @Autowired
    ImageMapper imageMapper;
    @Autowired
    SignMapper signMapper;
    @Autowired
    CommentMapper commentMapper;

    public PageInfo<Article> findArticleInfo(int id, int pageNum) {
//        查询文章信息带着文章作者
        PageHelper.startPage(pageNum, 10);
        List<Article> lists = articleMapper.findArticleByClassifyName(id);
        PageInfo<Article> pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    public PageInfo<Article> ArticleSearch(String keyWord, int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Article> lists = articleMapper.ArticleSearchByKeyWords(keyWord);
        PageInfo<Article> pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    public int support(Support support) {
        support.setCreateTime(CurrentTimeUtil.getCurrentTime());
//        在support表中还没有这条数据就insert一条
        int result = supportMapper.findSupport(support);
        if (result == 0) {
//            更新文章表中的顶踩数量
            int id = support.getArticleId();
            Integer type = support.getType();
            if (type == 0) {
                articleMapper.updateDingCount(id);
            }
            if (type == 1) {
                articleMapper.updateCaiCount(id);
            }
            return supportMapper.support(support);
        }
//        已有了这条数据 就update
//        如果当前的状态和传进来的状态一直 就返回你已经操作过了

//        if(){
//
//        }
        else if (result == 1) {
            int id = support.getArticleId();
            Integer type = support.getType();
            if (type == 0) {
                articleMapper.updateDingCount1(id);
            }
            if (type == 1) {
                articleMapper.updateCaiCount1(id);
            }
            int num = supportMapper.updateType(support);
            System.out.println(num);
            return num;
        }

        return 0;
    }

    public List<Image> updateImage(int userId, String url) {
        if (imageMapper.updateImage(userId, url) == 1) {
            return imageMapper.findImageByUrl(userId, url);
        }
        return null;
    }
    @Transactional
    public int publishArticle(int userId, String title, String titlepic, String content, int isopen, int topicId, int articleClassId,int rewardNum) {
        System.out.println(userId + title + titlepic + content + isopen + topicId);
        Long createTime = CurrentTimeUtil.getCurrentTime();
        if (articleMapper.publishArticle(userId, title, titlepic, content, isopen, topicId, articleClassId, createTime,rewardNum) == 1) {
//            修改用户的贝利数
            signMapper.updateKissNum(userId,rewardNum);
            //            添加成功 然后查询出id
            Article article = articleMapper.findArticleIdByTitlepic(titlepic);
            int articleId = article.getId();
            return articleId;
        }
        return 0;
    }

    public int insertImageIdAndArticleId(int imageId, int articleId) {
        return articleMapper.insertImageIdAndArticleId(imageId, articleId);
    }

    public int getArticleClassId(int articleId) {
        return articleMapper.getArticleClassId(articleId);
    }

    public int getTopicClass(int articleId) {
        return articleMapper.getTopicClass(articleId);
    }


    public PageInfo<Article> getMyHottestArticle(int userId, int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Article> lists = articleMapper.getMyHottestArticle(userId);
        PageInfo<Article> pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    public PageInfo<Article> getMyNewestArticle(int userId, int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Article> lists = articleMapper.getMyNewestArticle(userId);
        PageInfo<Article> pageInfo = new PageInfo(lists);
        return pageInfo;

    }

    public PageInfo<Article> getOthersHottestArticle(int userId, int pageNum) {

        PageHelper.startPage(pageNum, 10);
        List<Article> lists = articleMapper.getOthersHottestArticle(userId);
        PageInfo<Article> pageInfo = new PageInfo(lists);
        return pageInfo;

    }

    public PageInfo<Article> getOthersNewestArticle(int userId, int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Article> lists = articleMapper.getOthersNewestArticle(userId);
        PageInfo<Article> pageInfo = new PageInfo(lists);
        return pageInfo;

    }

    @Transactional
    public int ArticleDelete(Integer userId, Integer articleId) {
        if (articleMapper.ArticleDelete(userId, articleId) == 1) {
//            将文章相关的评论也删掉
            commentMapper.deleteComment(articleId);
//            相关点赞也要撒删除
            supportMapper.deleteSupport(articleId);
            return 1;
        }
        return 0;
    }

    @Transactional
    public void updateViewNum(int articleId) {
        articleMapper.updateViewNum(articleId);
    }
}
