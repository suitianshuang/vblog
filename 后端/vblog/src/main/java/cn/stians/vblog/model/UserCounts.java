package cn.stians.vblog.model;

public class UserCounts {
    private Integer articleCount;
    private Integer commentCount;
    private Integer todayArticleCount;
    private Integer followCount;
    private Integer fensCount;
    private Integer todayDingCount;
    private Integer friendCount;
    private Integer kissNum;
    private Integer continueSignDay;

    public UserCounts(Integer articleCount, Integer commentCount, Integer todayArticleCount, Integer followCount, Integer fensCount, Integer todayDingCount, Integer friendCount, Integer kissNum, Integer continueSignDay) {
        this.articleCount = articleCount;
        this.commentCount = commentCount;
        this.todayArticleCount = todayArticleCount;
        this.followCount = followCount;
        this.fensCount = fensCount;
        this.todayDingCount = todayDingCount;
        this.friendCount = friendCount;
        this.kissNum = kissNum;
        this.continueSignDay = continueSignDay;
    }

    public Integer getKissNum() {
        return kissNum;
    }

    public void setKissNum(Integer kissNum) {
        this.kissNum = kissNum;
    }

    public Integer getContinueSignDay() {
        return continueSignDay;
    }

    public void setContinueSignDay(Integer continueSignDay) {
        this.continueSignDay = continueSignDay;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getTodayArticleCount() {
        return todayArticleCount;
    }

    public void setTodayArticleCount(Integer todayArticleCount) {
        this.todayArticleCount = todayArticleCount;
    }

    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
    }

    public Integer getFensCount() {
        return fensCount;
    }

    public void setFensCount(Integer fensCount) {
        this.fensCount = fensCount;
    }

    public Integer getTodayDingCount() {
        return todayDingCount;
    }

    public void setTodayDingCount(Integer todayDingCount) {
        this.todayDingCount = todayDingCount;
    }

    public Integer getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(Integer friendCount) {
        this.friendCount = friendCount;
    }


    @Override
    public String toString() {
        return "UserCounts{" +
                "articleCount=" + articleCount +
                ", commentCount=" + commentCount +
                ", todayArticleCount=" + todayArticleCount +
                ", followCount=" + followCount +
                ", fensCount=" + fensCount +
                ", todayDingCount=" + todayDingCount +
                ", friendCount=" + friendCount +
                ", kissNum=" + kissNum +
                ", continueSignDay=" + continueSignDay +
                '}';
    }
}
