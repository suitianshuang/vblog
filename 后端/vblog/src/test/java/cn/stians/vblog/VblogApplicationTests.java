package cn.stians.vblog;

import cn.stians.vblog.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class VblogApplicationTests {

    @Test
    void contextLoads() {

//        Long newTime = System.currentTimeMillis();
//        String time = newTime.toString().substring(0, 10);
//        System.out.println(time);
        String username[] = {"李秀丽","弓箭女皇","野蛮人之王","雷电巨龙","瓦基丽武神","皮卡超人"};
        String password[] ={"123456","123456","123456","123456","123456","123456"};

        for (int i =0;i<username.length;i++){

            String newPassword = MD5Util.getMD5(username[i].concat(password[i]));
            System.out.println(username[i]+"："+newPassword);
        }
    }
    @Test
    void method1(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        String date = simpleDateFormat.format(new Date());
        System.out.println(date);

        Long currentTimestamps=System.currentTimeMillis();
        Long oneDayTimestamps= Long.valueOf(60*60*24*1000);
        String time =String.valueOf(currentTimestamps - (currentTimestamps + 60 * 60 * 8 * 1000) % oneDayTimestamps);
        String todayStartTime = time.substring(0,10);
        System.out.println(todayStartTime);


    }
//    public static long getYesterdayStartTime(){
//        Long time = Long.parseLong(todayStartTime);
//        long time1 =time*1000-oneDayTimestamps;
//        yesterdayStartTime = time1/1000;
//        return yesterdayStartTime;
//    }

}
