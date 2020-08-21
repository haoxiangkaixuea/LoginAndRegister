package cn.edu.scujcc.loginandregister.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.loginandregister.R;
import cn.edu.scujcc.loginandregister.Utils.EditTextUtils;
import cn.edu.scujcc.loginandregister.listener.UserLab;
import cn.edu.scujcc.loginandregister.model.PostUser;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTellPhone;
    private EditText editVerify;
    private Button btnNext;
    private CheckBox checkBox;
    private ImageView imageTellPhone;
    private ImageView imageVerify;
    private ImageView imageBack;
    private TextView tvGetVerity;
    private int times = 60;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case UserLab.MSG_LOGIN_SUCCESS:
                    Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_LONG).show();
                    break;
                case UserLab.MSG_PASSWORD_ERROR:
                    Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_LONG).show();
                    break;
                case UserLab.MSG_NETWORK_ERROR:
                    Toast.makeText(RegisterActivity.this, "网络错误！", Toast.LENGTH_LONG).show();
                    break;
                default:
            }
        }
    };
    private MyRunnable runnable = new MyRunnable();
    private UserLab userLab = UserLab.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTellPhone = findViewById(R.id.edit_tellPhone);
        tvGetVerity = findViewById(R.id.get_verify);
        editVerify = findViewById(R.id.edit_verify);
        btnNext = findViewById(R.id.btn_login);
        checkBox = findViewById(R.id.cb_login);
        imageTellPhone = findViewById(R.id.image_tellPhone);
        imageVerify = findViewById(R.id.image_verify);
        imageBack = findViewById(R.id.image_back);
        imageBack.setOnClickListener(v -> {
            Intent intentBack = new Intent(this, LoginActivity.class);
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
            String tellPhone = editTellPhone.getText().toString();
            String verify = editVerify.getText().toString();
            PostUser postUser = null;
            userLab.register(postUser, handler);
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
            tvGetVerity.setText(times + getResources().getString(R.string.again_send));
            if (times == 0) {
                tvGetVerity.setText(getResources().getString(R.string.get_verify));
                tvGetVerity.setClickable(true);
                tvGetVerity.setTextColor(getResources().getColor(R.color.colorBlue));
            } else {
                tvGetVerity.setClickable(false);
                handler.postDelayed(runnable, 1000);
            }
        }
    }
}