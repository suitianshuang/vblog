package cn.stians.vblog.controller;

import cn.stians.vblog.config.loginConfig.UserLoginToken;
import cn.stians.vblog.model.Users;
import cn.stians.vblog.service.TokenService;
import cn.stians.vblog.service.UserService;
import cn.stians.vblog.utils.MD5Util;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public Object login(@RequestBody Users users) {
        JSONObject jsonObject = new JSONObject();
        String username = users.getUsername();
        String password = users.getPassword();
        System.out.println(username + password);
        Users userForBase = userService.findByUsername(users);
        if (userForBase == null) {
            jsonObject.put("errorMsg", "登录失败,用户不存在");
            return jsonObject;
        } else {
            String newPassword = MD5Util.getMD5(username.concat(password));
            if (!userForBase.getPassword().equals(newPassword)) {
                jsonObject.put("errorMsg", "登录失败,密码错误");
                return jsonObject;
            } else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(Integer userId) {
        System.out.println(userId);
        return "你已通过验证";
    }
}
