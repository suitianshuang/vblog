package cn.stians.vblog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Article {

    private Integer id;

    private Integer userid;

    private String title;

    private String titlepic;

    private String content;

    private Integer sharenum;
    private Integer collectionNum;

    private String location;

    private Integer type;

    private Integer pageviewNum;

    private Integer articleClassId;

    private Integer shareId;

    private Integer isopen;
    private Integer dingCount;
    private Integer caiCount;
    private Integer commentCount;

//    文章所属的话题分类 如果为空就不属于任何话题
    private Integer topicId;

    private Long createTime;

    private Integer rewardNum;

    private Integer isAccepted;
    private Users users;

    //    文章的点赞信息也属于文章的属性，属于一对多关系
    private List<Support> support;
    //    文章的点赞信息也属于文章的属性，属于一对多关系
//    文章的图片也属于文章的属性，属于一对多的关系
    private List<Image> images;
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Integer getRewardNum() {
        return rewardNum;
    }

    public void setRewardNum(Integer rewardNum) {
        this.rewardNum = rewardNum;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getDingCount() {
        return dingCount;
    }

    public void setDingCount(Integer dingCount) {
        this.dingCount = dingCount;
    }

    public Integer getCaiCount() {
        return caiCount;
    }

    public void setCaiCount(Integer caiCount) {
        this.caiCount = caiCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }



    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Support> getSupport() {
        return support;
    }

    public void setSupport(List<Support> support) {
        this.support = support;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTitlepic() {
        return titlepic;
    }

    public void setTitlepic(String titlepic) {
        this.titlepic = titlepic == null ? null : titlepic.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getSharenum() {
        return sharenum;
    }

    public void setSharenum(Integer sharenum) {
        this.sharenum = sharenum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPageviewNum() {
        return pageviewNum;
    }

    public void setPageviewNum(Integer pageviewNum) {
        this.pageviewNum = pageviewNum;
    }

    public Integer getArticleClassId() {
        return articleClassId;
    }

    public void setArticleClassId(Integer articleClassId) {
        this.articleClassId = articleClassId;
    }

    public Integer getShareId() {
        return shareId;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    public Integer getIsopen() {
        return isopen;
    }

    public void setIsopen(Integer isopen) {
        this.isopen = isopen;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }

    public Integer getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Integer isAccepted) {
        this.isAccepted = isAccepted;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", userid=" + userid +
                ", title='" + title + '\'' +
                ", titlepic='" + titlepic + '\'' +
                ", content='" + content + '\'' +
                ", sharenum=" + sharenum +
                ", collectionNum=" + collectionNum +
                ", location='" + location + '\'' +
                ", type=" + type +
                ", pageviewNum=" + pageviewNum +
                ", articleClassId=" + articleClassId +
                ", shareId=" + shareId +
                ", isopen=" + isopen +
                ", dingCount=" + dingCount +
                ", caiCount=" + caiCount +
                ", commentCount=" + commentCount +
                ", topicId=" + topicId +
                ", createTime=" + createTime +
                ", rewardNum=" + rewardNum +
                ", isAccepted=" + isAccepted +
                ", users=" + users +
                ", support=" + support +
                ", images=" + images +
                ", comments=" + comments +
                '}';
    }
}