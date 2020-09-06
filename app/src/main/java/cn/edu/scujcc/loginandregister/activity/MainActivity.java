package cn.edu.scujcc.loginandregister.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.loginandregister.R;

public class MainActivity extends AppCompatActivity {
    private ScrollView scrollView1;

    /**
     * 让ScrollView滚动到底部
     *
     * @param decorView
     * @param scroll
     */
    public static void scrollToBottom(final View decorView, final View scroll) {

        Handler mHandler = new Handler();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (scroll == null || decorView == null) {
                    return;
                }
                int offset = decorView.getMeasuredHeight() - scroll.getHeight();
                if (offset < 0) {
                    offset = 0;
                }
                scroll.scrollTo(0, offset);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        //scrollView1为布局文件中ScrollView的id
        // 监听键盘弹起或关闭(ScrollView)
        scrollView1 = findViewById(R.id.scrollView2);
        scrollView1.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(final View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > 100)) {
//                    Toast.makeText(getApplicationContext(), "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();
//                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);(会导致输入框失去焦点)
                    // 获得屏幕
                    View decorView = getWindow().getDecorView();
                    scrollToBottom(decorView, v);
                } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > 100)) {
//                    Toast.makeText(getApplicationContext(), "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}