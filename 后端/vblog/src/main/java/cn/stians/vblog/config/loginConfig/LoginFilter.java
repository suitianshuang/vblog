package cn.stians.vblog.config.loginConfig;


import com.auth0.jwt.JWT;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

//@WebFilter
//        (urlPatterns = "/**", filterName = "reqResFilter")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String token = ((HttpServletRequest) request).getHeader("token");
        System.out.println(token);
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(req) {
            @Override
            public String[] getParameterValues(String name) {
                if (name.equals("userId")) {
                    return new String[] { userId .toString() };
                }
                return super.getParameterValues(name);
            }
            @Override
            public Enumeration<String> getParameterNames() {
                Set<String> paramNames = new LinkedHashSet<>();
                paramNames.add("userId");
                Enumeration<String> names =  super.getParameterNames();
                while(names.hasMoreElements()) {
                    paramNames.add(names.nextElement());
                }
                return Collections.enumeration(paramNames);
            }
        };
        chain.doFilter(requestWrapper, resp);
    }
}
