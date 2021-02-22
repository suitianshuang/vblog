package cn.stians.vblog.utils;


import org.springframework.util.DigestUtils;


/**
 * MD5工具类
 *
 * @author pibigstar
 */
public class MD5Util {
    //盐，用于混交md5
    private static final String slat = "&%5123***&&%%$$#@";

    public static String getMD5(String str) {
        String base = str + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
