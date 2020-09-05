package cn.edu.scujcc.loginandregister;

import androidx.multidex.MultiDexApplication;

import com.didichuxing.doraemonkit.DoraemonKit;

/**
 * </pre>
 */
public class BaseApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        DoraemonKit.install(this);
    }
}
