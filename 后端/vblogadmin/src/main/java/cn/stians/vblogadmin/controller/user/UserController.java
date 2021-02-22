package cn.stians.vblogadmin.controller.user;

import cn.stians.vblogadmin.model.RespPageBean;
import cn.stians.vblogadmin.model.Userinfo;
import cn.stians.vblogadmin.model.Users;
import cn.stians.vblogadmin.service.UserService;
import cn.stians.vblogadmin.utils.Poiutil;
import cn.stians.vblogadmin.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users/basic")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public RespPageBean getUsersByPage(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer size,
                                       Users user, Userinfo userinfo, Date[] createTime){
        System.out.println(user.toString());
        System.out.println(Arrays.toString(createTime));
        return userService.getUsersByPage(page,size,user,userinfo,createTime);
    }
    @PutMapping("/")
    public RespBean updateEnable(@RequestParam Integer id,@RequestParam Integer isenable){
        if(userService.updateEnable(id,isenable)==1){
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData(){
        List<Users> list = (List<Users>) userService.getUsersByPage(null,null,null,null,null).getData();
        return Poiutil.users2Excel(list);
    }

}
