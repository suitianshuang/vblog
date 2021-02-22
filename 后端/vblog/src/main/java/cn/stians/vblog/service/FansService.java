package cn.stians.vblog.service;

import cn.stians.vblog.mapper.ArticleMapper;
import cn.stians.vblog.mapper.FansMapper;
import cn.stians.vblog.model.Article;
import cn.stians.vblog.model.Fans;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FansService {
    @Autowired
    FansMapper fansMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Transactional
    public int follow(Fans fans)
    {
        if (fansMapper.findFens(fans)==0){
            return fansMapper.follow(fans);
        }
        return 0;

    }

    public PageInfo getFallow(int id, int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Fans> lists = fansMapper.getFallow(id);
        PageInfo<Fans> pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    public PageInfo getFansList(int id, int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Fans> lists = fansMapper.getFansList(id);
        PageInfo<Fans> pageInfo = new PageInfo(lists);
        return pageInfo;
    }


    public PageInfo getFriendsList(int id, int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Fans> lists = fansMapper.getFriendsList(id);
        PageInfo<Fans> pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    public PageInfo<Article> getFollowListArticle(int userId, int pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Article> lists = articleMapper.getFollowListArticle(userId);
        PageInfo<Article> pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    @Transactional
    public int unFollow(Fans fans) {
//        确定当前用户有没有关注这个用户
        if(fansMapper.findFens(fans)==1){
            return fansMapper.unFollow(fans);
        }
        return 0;
    }
}
