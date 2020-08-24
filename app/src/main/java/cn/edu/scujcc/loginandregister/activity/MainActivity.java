package cn.edu.scujcc.loginandregister.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.loginandregister.R;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        final SpannableStringBuilder style = new SpannableStringBuilder();
        //设置文字
        style.append(getResources().getString(R.string.deal_after));
        //设置部分文字点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };
        style.setSpan(clickableSpan, 8, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(style);
        //配置给TextView
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(style);
    }
}