package cn.stians.vblog.utils;

import cn.stians.vblog.mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledUtil {
//    每天凌晨执行一次
    @Autowired
    TopicMapper topicMapper;
    @Scheduled(cron =" 0 0 0 * * ?")
    public void updateTodayNum(){
        topicMapper.updateTodayNum();
    }
}
