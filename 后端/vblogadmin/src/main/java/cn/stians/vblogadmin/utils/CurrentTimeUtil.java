package cn.stians.vblogadmin.utils;

public class CurrentTimeUtil {
    private static  Long newTime;
    private static Long time;
    private static   Long todayStartTime;
    private static Long   oneDayTimestamps= Long.valueOf(60*60*24*1000);;
    private static long yesterdayStartTime;
    private static  long todayEndTime;
    //    截取当前时间戳
    public static Long getCurrentTime(){
        newTime  = System.currentTimeMillis()/1000;

        return newTime;
    }

//    获得今天的起始时间戳
    public static Long getTodayStartTime(){
        Long currentTimestamps=System.currentTimeMillis();
        time =currentTimestamps - (currentTimestamps + 60 * 60 * 8 * 1000) % oneDayTimestamps;
         todayStartTime = time/1000;
        return todayStartTime;
    }
//昨天开始的时间戳
    public static long getYesterdayStartTime(){
        time = getTodayStartTime()*1000-oneDayTimestamps;
        yesterdayStartTime = time/1000;
        return yesterdayStartTime;
    }
//   获取今天结束的时间戳
    public static long getTodayEndTime(){
        time = getTodayStartTime()*1000+oneDayTimestamps;
        todayEndTime = time/1000;
        return todayEndTime;
    }

}
