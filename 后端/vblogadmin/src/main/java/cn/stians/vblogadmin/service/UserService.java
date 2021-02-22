package cn.stians.vblogadmin.service;

import cn.stians.vblogadmin.mapper.UserMapper;
import cn.stians.vblogadmin.model.RespPageBean;
import cn.stians.vblogadmin.model.Userinfo;
import cn.stians.vblogadmin.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    UserMapper userMapper;


    public RespPageBean getUsersByPage(Integer page, Integer size, Users user, Userinfo userinfo, Date[] createTime) {
        if (page != null && size !=null) {
            page = (page-1)*size;
        }
        Long time []= new Long[2];

        if(createTime!=null){
            for (int i = 0; i < createTime.length; i++) {
                time[i]=createTime[i].getTime()/1000;
            }
            System.out.println(">>time[]="+Arrays.toString(time));
        }else {
            time = null;
        }

        List<Users> data = userMapper.getUsersByPage(page,size,user,userinfo,time);
        Integer total = userMapper.getTotal(user,userinfo,time);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public int updateEnable(Integer id, Integer isenable) {
        return userMapper.updateEnable(id,isenable);
    }
}
