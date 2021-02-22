package cn.stians.vblog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Userinfo {
    private Integer id;

    private Integer userId;

    private Integer sex;

    private Integer age;

    private Integer emotion;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
    private String job;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date birthday;

    private String hometown;

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getEmotion() {
        return emotion;
    }

    public void setEmotion(Integer emotion) {
        this.emotion = emotion;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "Userinfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", sex=" + sex +
                ", age=" + age +
                ", emotion=" + emotion +
                ", job='" + job + '\'' +
                ", birthday=" + birthday +
                ", hometown='" + hometown + '\'' +
                '}';
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
}