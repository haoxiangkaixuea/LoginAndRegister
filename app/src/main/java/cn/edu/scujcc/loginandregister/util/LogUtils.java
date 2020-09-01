package cn.edu.scujcc.loginandregister.util;

import android.util.Log;

/**
 * @author Administrator
 */
public class LogUtils {

    /**
     * 默认的TAG标签名称
     */

    private LogUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }


}
