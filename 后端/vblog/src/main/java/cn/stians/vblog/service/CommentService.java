package cn.stians.vblog.service;

import cn.stians.vblog.mapper.ArticleMapper;
import cn.stians.vblog.mapper.CommentMapper;
import cn.stians.vblog.mapper.SignMapper;
import cn.stians.vblog.model.Article;
import cn.stians.vblog.model.Comment;
import cn.stians.vblog.utils.CurrentTimeUtil;
import cn.stians.vblog.utils.RespBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    SignMapper signMapper;
    private static  final  int COMMENT_NOT_FOUNT = 0;
    private static  final  int COMMENT_INSERT_SUCCESS=1;
    private static final int  COMMENT_NOT_INSERT= 2;

    /*
    * 评论的验证规则，
        1、如果parentId不是0 要查找这个parentId是否存在 ，不存在就直接return
        2 、添加评论，insert 的参数userId  message parentId articleId create_time  除了comment_num意外 它默认是0
        2、评论成功之后如果这条评论的parentId是>0的， 就根据它的parentId找到评论表的id，将这一列的comment_num +1
    * */
    public int insertComment(Comment comment) {
//        1 判断当前的parentId是否存在
        Integer parentId = comment.getParentId();
        Integer articleId = comment.getArticleId();
        Integer userId = comment.getUserId();
        String message = comment.getMessage();

       Long  createTime = CurrentTimeUtil.getCurrentTime();
        if(parentId>0){
            if (commentMapper.checkParentId(comment) == 0) {
                return COMMENT_NOT_FOUNT;
            }
        }
//        2添加当前评论
        if (commentMapper.insertCurrentComment(userId,parentId,message,createTime,articleId)==1){
//            3判断当前的parentId
            if(parentId>0){
//                修改parentId 对应的id那一列的 comment_num +1
                if(commentMapper.updateById(parentId)==1){
                    articleMapper.updateCommentNum(articleId);
                    int id = articleMapper.findId(createTime,articleId,userId);
                    return id;
                }
                return COMMENT_NOT_INSERT;
            }
            articleMapper.updateCommentNum(articleId);
            int id = articleMapper.findId(createTime,articleId,userId);
            return id;
        }
        return COMMENT_NOT_INSERT;

    }

    public List<Comment> getCommentList(int articleId) {
        return commentMapper.getCommentList(articleId);
    }

    public List<Article> getUserComment(int userId, int pageNum) {

        List<Article> lists = articleMapper.getUserComment(userId);
        Iterator<Article> iterator = lists.iterator();
        while (iterator.hasNext()){
            Article obj = iterator.next();
            if(obj.getComments().isEmpty()){
                iterator.remove();
            }
        }

//        PageInfo<Article> pageInfo = new PageInfo(lists);
        return lists;

    }

    @Transactional
    public int acceptOthersComment(int userId, int acceptedUserId, int rewardNum,int articleId) {
//        增加评论人的kissnumm
         signMapper.increaseKissNum(acceptedUserId,rewardNum);
//        将文章的状态设置为已采纳过，返回的数据是文章是否被采纳的字段
         articleMapper.updateIsAccepted(articleId);

         return 1;
    }
}
