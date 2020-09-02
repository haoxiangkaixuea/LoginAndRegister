package cn.edu.scujcc.loginandregister.util;

import cn.edu.scujcc.loginandregister.constant.Constants;

/**
 * @author Administrator
 */
public class TimeUtils {
    /**
     * @param d 倒计时的时间
     * @return 倒计时
     */
    public static String currentlyTime(long d) {
        StringBuilder stringBuilder = new StringBuilder();
        long hour = 0;
        long minute = d / 1000 / Constants.CURRENTLY_TIME;
        long second;
        if (minute >= Constants.CURRENTLY_TIME) {
            hour = minute / Constants.CURRENTLY_TIME;
            minute = minute - (hour * Constants.CURRENTLY_TIME);
            second = (d / 1000) - (hour * Constants.CURRENTLY_TIME * Constants.CURRENTLY_TIME) - (minute * Constants.CURRENTLY_TIME);
        } else {
            second = (d / 1000) - (minute * Constants.CURRENTLY_TIME);
        }
        if (hour == 0) {
            stringBuilder.append(append0(second));
        } else {
            stringBuilder.append(append0(hour)).append(":").append(append0(minute)).append(":").append(append0(second));
        }
        return stringBuilder.toString();
    }
    
    private static String append0(long d) {
        if (d >= 0 && d < 10) {
            return "" + d;
        }
        return d + "";
    }
}
