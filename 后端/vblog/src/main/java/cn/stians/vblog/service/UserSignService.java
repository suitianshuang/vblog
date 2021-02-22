package cn.stians.vblog.service;

import cn.stians.vblog.mapper.SignMapper;
import cn.stians.vblog.model.Sign;
import cn.stians.vblog.utils.CurrentTimeUtil;
import cn.stians.vblog.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSignService {

    @Autowired
    SignMapper signMapper;
    Long signTime = CurrentTimeUtil.getCurrentTime();
    int continueSignDay = 0;//连续签到天数
    Long lastSignTime = null;//上次签到时间
    int kissNum = 0;//总飞吻数
    private static Long todayStartTime = CurrentTimeUtil.getTodayStartTime();//今天开始时间戳
    private static long yesterdayStartTime = CurrentTimeUtil.getYesterdayStartTime();//昨天起始时间戳
    private static long todayEndTime = CurrentTimeUtil.getTodayEndTime();//今天结束时间戳

    @Transactional
    public Sign userSign(Sign sign) {
//    先判断用户表中有没有这个用户，如果没有就先insert
        if (signMapper.findUser(sign) != 1) {
//            添加用户首次签到信息
            sign.setIsTodaySign(1);
            sign.setContinueSignDay(1);
//           首次签到送十个飞吻
            sign.setKissNum(10);
            sign.setSignTime(signTime);
            if (signMapper.insertFirstSign(sign) == 1) {
                return signMapper.findSign(sign);
            }
            return null;
        }
        // 用户在签到表里面已经有了 直接update
        // 判断用户上一次的签到时间是处于哪个时间段
        lastSignTime = signMapper.findLastSignTime(sign);
        continueSignDay = signMapper.findContinueSignDay(sign);//获得连续签到次数
        kissNum = signMapper.findKissNum(sign);
        if (lastSignTime > todayStartTime && lastSignTime < todayEndTime) {
            return null;//今天已经签到过了
        } else if (lastSignTime > yesterdayStartTime && lastSignTime < todayStartTime) {
            continueSignDay++;
        }
//        更新今天是否签到、签到时间、连续签到天数
        sign.setIsTodaySign(1);
        sign.setContinueSignDay(continueSignDay);
        sign.setSignTime(signTime);
//        根据连续签到天数来更新飞吻数
        int todayKissNum;
        if (continueSignDay > 7) {
            todayKissNum = continueSignDay % 7 * 2;
        } else {
            todayKissNum = continueSignDay * 2;
        }
        kissNum += todayKissNum;
        sign.setKissNum(kissNum);
        if (signMapper.updateSign(sign) == 1) {
            return signMapper.findSign(sign);
        }
        return null;
    }
}
