package cn.edu.scujcc.loginandregister.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    //单例模式
    private static volatile ToastUtils INSTANCE;
    private static Context context = null;
    private static Toast toast = null;

    public ToastUtils() {

    }

    public static ToastUtils getInstance() {
        if (INSTANCE == null) {
            synchronized (ToastUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ToastUtils();
                }
            }
        }
        return INSTANCE;
    }

    public static void showToast(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}