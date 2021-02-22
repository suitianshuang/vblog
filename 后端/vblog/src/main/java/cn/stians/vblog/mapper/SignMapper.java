package cn.stians.vblog.mapper;

import cn.stians.vblog.model.Sign;
import org.apache.ibatis.annotations.Param;

public interface SignMapper {

     int  findUser(Sign sign);

    int insertFirstSign(Sign sign);

    Long findLastSignTime(Sign sign);

    int updateSign(Sign sign);

    int findContinueSignDay(Sign sign);

    int findKissNum(Sign sign);

    Sign findSign(Sign sign);
    
    int findKissNum1(int userId);

    int findContinueSignDay1(int userId);

    Sign findUser1(int userId);

    void updateKissNum(@Param("userId") int userId,@Param("rewardNum") int rewardNum);


    void increaseKissNum(@Param("acceptedUserId") int acceptedUserId,@Param("rewardNum") int rewardNum);
}
