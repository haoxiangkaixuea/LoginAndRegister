package cn.edu.scujcc.loginandregister;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.loginandregister.listener.EditTextUtils;

public class LoginActivity extends AppCompatActivity {
    private EditText editTellPhone;
    private EditText editVerify;
    private Button btnNext;
    private CheckBox checkBox;
    private ImageView imageTellPhone;
    private ImageView imageVerify;
    private ImageView imageBack;
    private TextView tvGetVerity;
    private int times = 60;
    private Handler handler = new Handler();
    private MyRunnable runnable = new MyRunnable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTellPhone = findViewById(R.id.edit_tellPhone);
        tvGetVerity = findViewById(R.id.get_verify);
        editVerify = findViewById(R.id.edit_verify);
        btnNext = findViewById(R.id.btn_login);
        checkBox = findViewById(R.id.cb_login);
        imageTellPhone = findViewById(R.id.image_tellPhone);
        imageVerify = findViewById(R.id.image_verify);
        imageBack = findViewById(R.id.image_back);
        imageBack.setOnClickListener(v -> {
            Intent intentBack = new Intent(this, RegisterActivity.class);
            startActivity(intentBack);
        });

        EditTextUtils.clearButtonListener(editTellPhone, imageTellPhone);
        EditTextUtils.clearButtonListener(editVerify, imageVerify);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked()) {
                    boolean signUsername = editTellPhone.getText().length() > 0;
                    boolean signPassword = editVerify.getText().length() > 0;
                    if (signUsername & signPassword) {
                        btnNext.setBackgroundResource(R.drawable.btn_focus_on);
                        btnNext.setTextColor(R.drawable.button_font_style);
                    }
                } else {
                    btnNext.setBackgroundResource(R.drawable.btn_normal);
                    btnNext.setTextColor(Color.WHITE);
                }
            }
        });
        btnNext.setOnClickListener(v -> {

        });
        tvGetVerity.setOnClickListener(view -> {
            times = 60;
            handler.postDelayed(runnable, 1000);
            tvGetVerity.setTextColor(Color.GRAY);
        });
    }

    //倒计时
    class MyRunnable implements Runnable {
        @Override
        public void run() {
            times--;
            tvGetVerity.setText(times + "s后重新发送");
            if (times == 0) {
                tvGetVerity.setText("重新发送");
                times = 60;
                tvGetVerity.setClickable(true);
                tvGetVerity.setTextColor(Color.BLUE);
            } else {
                tvGetVerity.setClickable(false);
                handler.postDelayed(runnable, 1000);
            }
        }
    }
}