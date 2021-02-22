package cn.stians.vblogadmin.model;

import cn.stians.vblogadmin.model.admin.RoleAdmin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Admins implements UserDetails {
    private Integer id;
    private String name;
    private String adminPic;
    private String username;
    private String password;
    private String phone;
    private String address;
    private String email;
    private String mark;
    private Boolean enabled;



    private List<RoleAdmin> roles;



    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (RoleAdmin role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }


    @Override


    public boolean isAccountNonExpired() {
        return true;
    }

    @Override

    public boolean isAccountNonLocked() {
        return true;
    }

    @Override


    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override

    public boolean isEnabled() {
        return enabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminPic() {
        return adminPic;
    }

    public void setAdminPic(String adminPic) {
        this.adminPic = adminPic;
    }

    public List<RoleAdmin> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleAdmin> roles) {
        this.roles = roles;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Admins{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adminPic='" + adminPic + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", mark='" + mark + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}
