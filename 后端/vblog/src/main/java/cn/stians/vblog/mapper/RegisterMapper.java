package cn.stians.vblog.mapper;

import org.apache.ibatis.annotations.Param;

public interface RegisterMapper {
     int  updateCodeAndEmail(@Param("email") String email,@Param("code") String code,@Param("createTime") Long createTime);

    int checkEmailAndCode(@Param("email") String email,@Param("code") String code);

    int findEmail(String email);

    void insertRegisterUser(@Param("email") String email,@Param("code") String code,@Param("createTime") Long createTime);
}
