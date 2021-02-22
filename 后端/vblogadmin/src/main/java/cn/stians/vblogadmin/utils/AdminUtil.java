package cn.stians.vblogadmin.utils;

import cn.stians.vblogadmin.model.Admins;
import org.springframework.security.core.context.SecurityContextHolder;

public class AdminUtil {
    public static Admins getCurrentAdmin() {
        return ((Admins) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
