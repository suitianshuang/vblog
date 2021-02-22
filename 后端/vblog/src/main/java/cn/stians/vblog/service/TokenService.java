package cn.stians.vblog.service;

import cn.stians.vblog.model.Users;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    public String getToken(Users user) {
        String token="";
        String userId = String.valueOf(user.getId());
        token= JWT.create().withAudience(userId)
                .sign(Algorithm.HMAC256(user.getUsername()+userId));

        return token;
    }
}
