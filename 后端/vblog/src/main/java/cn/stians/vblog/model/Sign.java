package cn.stians.vblog.model;

/**
 * 签到表
* */
public class Sign {
    private Integer id;
    private Integer userId;
    private Integer isTodaySign;
    private Integer continueSignDay;
    private Long signTime;
    private Integer kissNum;
    private Integer isEnable;

    public Integer getKissNum() {
        return kissNum;
    }

    public void setKissNum(Integer kissNum) {
        this.kissNum = kissNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsTodaySign() {
        return isTodaySign;
    }

    public void setIsTodaySign(Integer isTodaySign) {
        this.isTodaySign = isTodaySign;
    }

    public Integer getContinueSignDay() {
        return continueSignDay;
    }

    public void setContinueSignDay(Integer continueSignDay) {
        this.continueSignDay = continueSignDay;
    }

    public Long getSignTime() {
        return signTime;
    }

    public void setSignTime(Long signTime) {
        this.signTime = signTime;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }
}
