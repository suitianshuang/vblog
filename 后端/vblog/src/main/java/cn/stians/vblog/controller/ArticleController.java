package cn.stians.vblog.controller;

import cn.stians.vblog.config.loginConfig.UserLoginToken;
import cn.stians.vblog.model.Article;
import cn.stians.vblog.model.Support;
import cn.stians.vblog.model.Topic;
import cn.stians.vblog.service.ArticleService;
import cn.stians.vblog.service.TopicService;
import cn.stians.vblog.utils.RespBean;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @Autowired
    TopicService topicService;

    @GetMapping("/article/indexArticleInfo")
    public RespBean getIndexInfo(int id, int pageNum) {
        if (articleService.findArticleInfo(id, pageNum) != null) {
            return RespBean.ok("success", articleService.findArticleInfo(id, pageNum));
        }
        return RespBean.error("fail");
    }

    @PostMapping("/search/article")
    public RespBean ArticleSearch(String keyWord, int pageNum) {
        System.out.println(keyWord + pageNum);

        if (articleService.ArticleSearch(keyWord, pageNum) != null) {
            return RespBean.ok("success", articleService.ArticleSearch(keyWord, pageNum));
        }
        return RespBean.error("fail");
    }

    @UserLoginToken
    @PostMapping("/article/support")
    public RespBean support(@RequestBody Support support) {
        if (articleService.support(support) == 1) {

            return RespBean.ok("操作成功");
        }
        return RespBean.error("操作失败");
    }

    //   上传文章分为两个接口 这里是上传文章的，不管用户是否发布都会上传到服务器上来
//    @UserLoginToken
    @PostMapping("/article/updateImage")
    public RespBean updateImage(@RequestParam("imglist[]") MultipartFile file, HttpServletRequest req) {
        int userId = Integer.parseInt(req.getParameter("userId"));
        System.out.println(userId);
        if (file.isEmpty()) { //若文件选择为空,就上传失败
            return RespBean.error("图片为空！上传失败");
        }
        String filePath = "Z:/articleImage"; //指定到上传的文件路径

        File dir = new File(filePath);
        if (!dir.exists()) {  //若路径不存在,则创建一个这样的文件夹
            dir.mkdir();
        }
        String oldName = file.getOriginalFilename();//获取文件上传的文件名
        long time = new Date().getTime();
        String newName = time + UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            File file1 = new File(filePath, newName);
            //创建了一个File对象，名字是file1 ，路径是filePath，名字是fileName。
            //然后就可以调用这个对象的相关方法完成文件创建，删除，读取，写入等操作
            file.transferTo(file1);    //将上传的文件写入创建好的文件中
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/" + newName;
//                根据用户的userId将url 写入数据库image 表中

            return RespBean.ok("上传成功", articleService.updateImage(userId, url));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return RespBean.error("上传图片失败，未知错误");
    }

    @UserLoginToken
    @PostMapping("/article/publish")
    public RespBean articlePublish(@RequestParam(value = "imageIdList") String[] imageIdList,
                                   int userId, String title, String titlepic,
                                   String content, int isopen, int topicId, String topicTitle,
                                   int articleClassId, int rewardNum, HttpServletRequest req) {
        System.out.println(topicId+"/"+"/"+topicTitle+"/"+articleClassId);
//       先判断是不是新话题,是新话题就先insert，返回topicId 让文章表关联
        if (topicId == 0) {
            String titlePic[] = {"/topic1.jpg", "/topic2.jpg"};
            Random random = new Random();
            int i = random.nextInt(2);
            String titlePicName = titlePic[i];
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + titlePicName;

            topicId = topicService.insertNewTopic(topicTitle, url);
        }
//        将总的和今日话题参与数+1
        topicService.increaseTopicNum(topicId);
//        将用户的第一张图片的路径作为封面图片
        int articleId = articleService.publishArticle(userId, title, titlepic, content, isopen, topicId, articleClassId, rewardNum);
//    将articleId和文章在关联表中进行关联
        int imageId;
        for (int i = 0; i < imageIdList.length; i++) {

            String regEx = "[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";

//可以在中括号内加上任何想要替换的字符，实际上是一个正则表达式
            String aa = "";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(imageIdList[i]);//这里把想要替换的字符串传进来
            imageId = Integer.parseInt(m.replaceAll(aa).trim());
//            循环插入
            articleService.insertImageIdAndArticleId(imageId, articleId);
        }

//如果没选文章就去查话题分类id
        if (articleClassId == 0) {
            int topicClassId = articleService.getTopicClass(articleId);
            return RespBean.ok("话题分类", topicClassId);
        }
//        如果没选话题就去查话题分类id
        if (topicTitle.equals("未选择话题")) {
            int articleClassId1 = articleService.getArticleClassId(articleId);
            return RespBean.ok("文章分类", articleClassId1);
        }
//        如果都选了还是去查文章分类的id
        if (articleClassId != 0 && topicTitle != null) {
            int articleClassId1 = articleService.getArticleClassId(articleId);
            return RespBean.ok("文章分类", articleClassId1);
        }
        return RespBean.error("服务器有点异常，请重试");
    }

    //    @UserLoginToken
    @GetMapping("/user/me/hottestarticle")
    public RespBean getMyHottestArticle(int userId, int pageNum) {
        PageInfo<Article> articlePageInfo = articleService.getMyHottestArticle(userId, pageNum);
        if (articlePageInfo != null) {
            return RespBean.ok("获取成功", articlePageInfo);
        }
        return RespBean.error("获取失败");
    }

    //    @UserLoginToken
    @GetMapping("/user/me/newestarticle")
    public RespBean getMyNewestArticle(int userId, int pageNum) {
        PageInfo<Article> articlePageInfo = articleService.getMyNewestArticle(userId, pageNum);
        if (articlePageInfo != null) {
            return RespBean.ok("获取成功", articlePageInfo);
        }
        return RespBean.error("获取失败");
    }

    @GetMapping("/user/others/hottestarticle")
    public RespBean getOthersHottestArticle(int userId, int pageNum) {
        PageInfo<Article> articlePageInfo = articleService.getOthersHottestArticle(userId, pageNum);
        if (articlePageInfo != null) {
            return RespBean.ok("获取成功", articlePageInfo);
        }
        return RespBean.error("获取失败");
    }

    @GetMapping("/user/others/newestarticle")
    public RespBean getOthersNewestArticle(int userId, int pageNum) {
        PageInfo<Article> articlePageInfo = articleService.getOthersNewestArticle(userId, pageNum);
        if (articlePageInfo != null) {
            return RespBean.ok("获取成功", articlePageInfo);
        }
        return RespBean.error("获取失败");
    }

    @UserLoginToken
    @PostMapping("/article/delete")
    public RespBean ArticleDelete(@RequestBody JSONObject jsonObject) {
        int userId = (int) jsonObject.get("userId");
        int articleId = (int) jsonObject.get("articleId");
        System.out.println(userId);

        int result = articleService.ArticleDelete(userId, articleId);

        if (result == 1) {
            return RespBean.ok("删除文章成功");
        }
        return RespBean.error("你所删除的文章已经不存在了~");
    }

    @PostMapping("/article/updateViewnum")
    public RespBean updateViewNum(int articleId) {
        articleService.updateViewNum(articleId);
        return RespBean.ok("更新浏览量成功");
    }
}
