package cn.stians.vblog.service;

import cn.stians.vblog.mapper.*;
import cn.stians.vblog.model.Sign;
import cn.stians.vblog.model.UserCounts;
import cn.stians.vblog.model.Users;
import cn.stians.vblog.utils.CurrentTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserCountService {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    FansMapper fansMapper;
    @Autowired
    SupportMapper supportMapper;
    @Autowired
    SignMapper signMapper;


    /**
     * 统计用户的相关信息
     * private Integer articleCount;
     * private Integer commentCount;
     * private Integer todayArticleCount;
     * private Integer followCount;
     * private Integer fensCount;
     * private Integer todayDingCount;
     * private Integer friendCount;
     */
    public UserCounts getUserCounts(int userId) {
        Long  todayTime = CurrentTimeUtil.getTodayStartTime();
        int articleCount =getArticleCount(userId);
        int commentCount =getCommentCount(userId);
        int todayArticleCount=getTodayArticleCount(userId,todayTime);
        int followCount = getFollowCount(userId);
        int fensCount = getFensCount(userId);
        int todayDingCount = getTodayDingCount(userId,todayTime);
        int friendCount = getFriendCount(userId);
        int kissNum = getKissNum(userId);
        int continueSignDay = getContinueSignDay(userId);
        UserCounts userCounts = new UserCounts(articleCount,commentCount,todayArticleCount,followCount,fensCount,todayDingCount,friendCount,kissNum,continueSignDay);
        System.out.println(userCounts.toString());
        return userCounts;
    }
    //        1，去文章表查 当前userid 的记录数
    public int getArticleCount(int userId) {
        return articleMapper.getArticleCount(userId);
    }
//    去评论表查评论数
    public int getCommentCount(int userId){
        return commentMapper.getCommentCount(userId);
    }
//    去文章表查找今日的文章数
    public int getTodayArticleCount(int userId,Long todayTime){
        return articleMapper.getTodayArticleCount(userId,todayTime);
    }
//    去粉丝表查找用户关注的人的数量
    public int getFollowCount(int userId){
        return fansMapper.getFollowCount(userId);
    }
//    去粉丝表查找用户fens的数量
    public int getFensCount(int userId){
        return fansMapper.getFensCount(userId);
    }

//    去顶踩表查找今日顶踩的数量
    public int getTodayDingCount(int userId,Long todayTime){
        System.out.println(todayTime);
        return supportMapper.getTodayDingCount(userId,todayTime);
    }
//    去fans表查找朋友的数量 ，互相的关注的
    public int getFriendCount(int userId){
        return fansMapper.getFriendCount(userId);
    }

    public int getKissNum(int userId){
        Sign user1 = signMapper.findUser1(userId);
        if (user1==null){
            return 0;
        }
        return signMapper.findKissNum1(userId);
    }
    public int getContinueSignDay(int userId){
        Sign user1 = signMapper.findUser1(userId);
        if (user1==null){
            return 0;
        }
        return signMapper.findContinueSignDay1(userId);
    }
}
