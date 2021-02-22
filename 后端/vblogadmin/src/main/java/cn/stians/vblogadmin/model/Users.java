package cn.stians.vblogadmin.model;

import org.springframework.security.core.userdetails.UserDetails;

import javax.websocket.Session;
import java.util.List;

public class Users   {
    private Integer id;

    public Users() {
    }
    private Session session;

    public Users(String username) {
        this.username = username;
    }

    public Users(Integer id ,String username, Session session) {
        this.id = id;
        this.session = session;
        this.username = username;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    private String username;

    private String userpic;

    private String password;

    private String roleId;

    private String email;
    private String createTime;

    private Integer  isenable;
    private Userinfo userinfo;

    private List<Fans> fans;



    public Userinfo getUserinfo() {
        return userinfo;
    }

    public List<Fans> getFans() {
        return fans;
    }

    public void setFans(List<Fans> fans) {
        this.fans = fans;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic == null ? null : userpic.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getIsenable() {
        return isenable;
    }

    public void setIsenable(Integer isenable) {
        this.isenable = isenable;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", session=" + session +
                ", username='" + username + '\'' +
                ", userpic='" + userpic + '\'' +
                ", password='" + password + '\'' +
                ", roleId='" + roleId + '\'' +
                ", email='" + email + '\'' +
                ", createTime='" + createTime + '\'' +
                ", isenable=" + isenable +
                ", userinfo=" + userinfo +
                ", fans=" + fans +
                '}';
    }
}