package cn.stians.vblog.controller;

import cn.stians.vblog.model.Register;
import cn.stians.vblog.service.RegisterService;
import cn.stians.vblog.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/users")
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @PostMapping("/code")
    public RespBean getCode(String email) {
        int result = registerService.getCode(email);
        if (result == 0) {
            return RespBean.error("未知错误，请重新发送~");
        } else if (result == 3) {
            return RespBean.error("该账号已经注册过了~");
        }
        return RespBean.ok("获取验证码成功，服务器正在发送中~");
    }
    @PostMapping("/register")
    public RespBean register(String email, String code, String password, String username , HttpServletRequest req) {



            String userpic =req.getScheme()+"://" +req.getServerName()+":"+req.getServerPort()+"/"+"dog.jpg";
            int result = registerService.register(email, code, password, username,userpic);
            if (result ==1){
                return RespBean.ok("注册成功~");
            }
            if(result ==2){
                return RespBean.error("该用户名已经被占用了,换一个吧");
            }
            if(result ==3){
                return RespBean.error("该账号已经注册过了");
            }
        return RespBean.error("填写完整信息~");
    }

}
