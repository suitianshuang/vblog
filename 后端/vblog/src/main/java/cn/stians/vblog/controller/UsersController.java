package cn.stians.vblog.controller;

import cn.stians.vblog.config.loginConfig.UserLoginToken;
import cn.stians.vblog.model.UserCounts;
import cn.stians.vblog.model.Users;
import cn.stians.vblog.service.UserCountService;
import cn.stians.vblog.service.UserService;
import cn.stians.vblog.utils.RespBean;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class UsersController {
    @Autowired
    UserService userService;
    @Autowired
    UserCountService userCountService;

    //   上传文件 单个图片/ 修改头像
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

//    @UserLoginToken
    @PostMapping("/uploadFile")
    public RespBean uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest req) {

        if (file.isEmpty()) { //若文件选择为空,就上传失败
            return RespBean.error("图片为空！上传失败");
        }
        String filePath = "Z:/image"; //指定到上传的文件路径

        File dir = new File(filePath);
        if (!dir.exists()) {  //若路径不存在,则创建一个这样的文件夹
            dir.mkdir();
        }
        String oldName = file.getOriginalFilename();//获取文件上传的文件名
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            File file1 = new File(filePath, newName);
            //创建了一个File对象，名字是file1 ，路径是filePath，名字是fileName。
            //然后就可以调用这个对象的相关方法完成文件创建，删除，读取，写入等操作
            file.transferTo(file1);    //将上传的文件写入创建好的文件中
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/" + newName;
//                根据用户的id将url 写入数据库
            int id = Integer.parseInt(req.getParameter("id"));
            userService.updateUserpic(url, id);
            return RespBean.ok(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RespBean.error("上传图片失败，未知错误");
    }

    //        统计用户的各类信息数目
    @UserLoginToken
    @GetMapping("/users/getcounts")
    public RespBean getUserCounts(int userId) {
        UserCounts userCounts = userCountService.getUserCounts(userId);
        if (userCounts != null) {
            return RespBean.ok("获取成功", userCounts);
        }
        return RespBean.error("获取失败");
    }


//    public String  updateUserpic(MultipartFile file,HttpServletRequest req){
//
//        String format = sdf.format(new Date());
//        String realPath  =req.getServletContext().getRealPath("/img")+format;
//        File folder = new File(realPath);
//        if (!folder.exists()){
//            folder.mkdir();
//        }
//        String name = req.getParameter("name");
//        String id = req.getParameter("id");
//        System.out.println(name+id);
//        String oldName = file.getOriginalFilename();
//        System.out.println(oldName);
//        String newName = UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."));
//        try{
//            file.transferTo(new File(folder,newName));
//            String url =req.getScheme()+"://" +req.getServerName()+":"+req.getServerPort()+"/img"+format+newName;
//            System.out.println(url);
//            return url;
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return "shibai";
//    }

    @UserLoginToken
    @PostMapping("/users/updatePassword")
    public RespBean updatePassword(@RequestBody Users users) {
        String newPassword = users.getPassword();
        String email = users.getEmail();
        if (userService.updatePassword(newPassword, email) == 1) {
            return RespBean.ok("修改密码成功");
        }
        return RespBean.error("旧密码不正确，修改密码失败");
    }

    @PostMapping("/search/user")
    public RespBean getSearchUsersList(String keyWord, int pageNum) {
        PageInfo<Users> pageInfo = userService.getSearchUsersList(keyWord, pageNum);
        if (pageInfo != null) {
            return RespBean.ok("获取用户列表成功", pageInfo);
        }
        return RespBean.error("获取用户列表失败");
    }

    @GetMapping("/users/userinfo")
    public RespBean getUserInfo(int userId, int visitId) {
        Map<String, Object> map = userService.getUserInfo(userId, visitId);

        if (map.get("users") != null) {
            return RespBean.ok("获取成功", map);
        }
        return RespBean.error("该用户由于违反社区规定，已被封号");
    }

}
