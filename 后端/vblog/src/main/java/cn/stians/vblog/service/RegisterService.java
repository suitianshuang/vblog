package cn.stians.vblog.service;

import cn.stians.vblog.mapper.RegisterMapper;
import cn.stians.vblog.mapper.UserMapper;
import cn.stians.vblog.mapper.UserinfoMapper;
import cn.stians.vblog.model.Users;
import cn.stians.vblog.utils.CurrentTimeUtil;
import cn.stians.vblog.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Random;

@Service
@Transactional
public class RegisterService {


    @Autowired
    UserMapper userMapper;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    RegisterMapper registerMapper;
    @Autowired
    UserinfoMapper userinfoMapper;

    public static final int EMAIL_HAS_REGISTER = 3;
    public static int code = 0;
    public static final int SUCCESS = 1;
    public static final int UNKNOWN_FAIL = 0;
    public static final int USERNAME_HAS_USED = 2;

    public void sendCodeToUserQQEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("xx验证码");
        message.setFrom("1422780320@qq.com");
        message.setTo(email);
        message.setSentDate(new Date());

        message.setText("您的注册验证码是：" + code + " 请妥善保管您的验证码，不要透露给他人");
        javaMailSender.send(message);
    }
    @Transactional
    public int getCode(String email) {
//        先判断users表里现在有没有这个邮箱
        if (userMapper.checkEmail(email) != 0) {
            return EMAIL_HAS_REGISTER;
        }
//        没注册过就给用户发邮件
        Random random = new Random();
        int code = random.nextInt(10000) + 1000;
        String codes = code + "";
        sendCodeToUserQQEmail(email);
//        再把验证码存到注册表中
//        判断register表有没有这个用户
        long createTime = CurrentTimeUtil.getCurrentTime();

        if(registerMapper.findEmail(email)==0){
            registerMapper.insertRegisterUser(email,codes,createTime);
        }else if (registerMapper.updateCodeAndEmail(email, codes,createTime) == 1) {
                return SUCCESS;

        }
        return UNKNOWN_FAIL;
    }
@Transactional
    public int register(String email, String code, String password, String username, String userpic) {
//            看users表中有没有这份用户
            if (userMapper.checkEmail(email) != 0) {
                return EMAIL_HAS_REGISTER;
            }
//            验证验证码表中的email和code存不存在于register表中
            if (registerMapper.checkEmailAndCode(email, code) == 1) {
                // 3.	用户设置的这个昵称是否已经存在于用户表中 如果存在 停止，提醒用户换一个
                if (userMapper.checkUsername(username) == 1) {
                    return USERNAME_HAS_USED;
                }
                //  将用户的邮箱 密码存到用户表中
//                   密码加密
                String newPassword = MD5Util.getMD5(username.concat(password));
//                   存入一个默认头像，存入注册时间戳
                Users users = new Users();
                users.setEmail(email);
                users.setPassword(newPassword);
                users.setUserpic(userpic);
                users.setUsername(username);
                String createTime = String.valueOf(new Date().getTime());
                users.setCreateTime(createTime);

                if (userMapper.saveUserMassage(users) == 1) {
                    userinfoMapper.saveUserMassage(users);
                    return SUCCESS;
                }
                return UNKNOWN_FAIL;
            }

        return UNKNOWN_FAIL;
    }


}
