package cn.stians.vblog.config.loginConfig;


import cn.stians.vblog.model.Users;
import cn.stians.vblog.service.UserService;
import cn.stians.vblog.utils.RespBean;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    RespBean respBean =RespBean.error("请先登录~");
                    String s = new ObjectMapper().writeValueAsString(respBean);
                    out.write(s);
                    out.flush();
                    out.close();
                    return false;
                }
                // 获取 token 中的 userId
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
//                    Integer userId2 = Integer.parseInt(userId);
                } catch (JWTDecodeException j) {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    RespBean respBean =RespBean.error("你还没登录呢~");
                    String s = new ObjectMapper().writeValueAsString(respBean);
                    out.write(s);
                    out.flush();
                    out.close();
                    return false;
                }
                Users users = userService.findById(userId);
                if (users == null) {
                      httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    RespBean respBean =RespBean.error("该用户还没注册过~");
                    String s = new ObjectMapper().writeValueAsString(respBean);
                    out.write(s);
                    out.flush();
                    out.close();
                    return false;
                }
                // 验证 token
                 userId = String.valueOf(users.getId());
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(users.getUsername()+userId)).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
//                    throw new RuntimeException("401");
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    RespBean respBean =RespBean.error("请重新登录~");
                    String s = new ObjectMapper().writeValueAsString(respBean);
                    out.write(s);
                    out.flush();
                    out.close();
                    return false;
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {
//        System.out.println("token1");

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
//        System.out.println("token2");
    }



}
