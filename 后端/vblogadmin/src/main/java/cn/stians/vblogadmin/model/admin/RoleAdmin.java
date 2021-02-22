package cn.stians.vblogadmin.model.admin;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class RoleAdmin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;


    public String name_zh ;

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

    public String getname_zh() {
        return name_zh;
    }

    public void setname_zh(String name_zh) {
        this.name_zh = name_zh;
    }
}
