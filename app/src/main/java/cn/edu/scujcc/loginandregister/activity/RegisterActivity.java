package cn.edu.scujcc.loginandregister.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import cn.edu.scujcc.loginandregister.Utils.SmsTimeUtils;
import cn.edu.scujcc.loginandregister.presenter.UserLab;

/**
 * @author Administrator
 */
public class RegisterActivity extends AppCompatActivity {
    public static final String ACTION = "cn.edu.scujcc.loginandregister.activity";
    private static final int VERIFY_SUCCESS = 5;
    private static final String TAG = "RegisterActivity";
    private EditText editTellPhone;
    private EditText editVerify;
    private Button btnNext;
    private CheckBox checkBox;
    private ImageView imageTellPhone;
    private ImageView imageVerify;
    private ImageView imageBack;
    private TextView tvGetVerity;
    private TextView tvGoLogin;
    private String showVerify;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.d(TAG, "msg.arg1" + msg.arg1);
            switch (msg.what) {
                case UserLab.MSG_REGISTER_SUCCESS:
                    showVerify = String.valueOf(msg.arg1);
                    editVerify.setText(showVerify);
                    break;
                case UserLab.MSG_PASSWORD_ERROR:
                    registerPasswordError();
                    break;
                case UserLab.MSG_NETWORK_ERROR:
                    registerNetworkError();
                    break;
                default:
                    break;
            }
        }
    };
    private UserLab userLab = UserLab.getInstance();

    private void registerSuccess() {
        Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_success), Toast.LENGTH_LONG).show();
    }

    private void registerPasswordError() {
        Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_failure), Toast.LENGTH_LONG).show();
    }

    private void registerNetworkError() {
        Toast.makeText(RegisterActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_LONG).show();
    }

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
        tvGoLogin = findViewById(R.id.text_go_login);

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
        editTellPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvGetVerity.setTextColor(getResources().getColor(R.color.colorGray, null));
                tvGetVerity.setEnabled(false);
                tvGetVerity.setClickable(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && s.length() == 11) {
                    tvGetVerity.setEnabled(true);
                    tvGetVerity.setClickable(true);
                    tvGetVerity.setTextColor(getResources().getColor(R.color.colorBlue, null));
                } else {
                    tvGetVerity.setTextColor(getResources().getColor(R.color.colorGray, null));
                    tvGetVerity.setEnabled(false);
                    tvGetVerity.setClickable(false);
                }
            }
        });

        btnNext.setOnClickListener(v -> {
            registerSuccess();
        });
        tvGetVerity.setOnClickListener(view -> {
            userLab.register(null, handler);
            Toast.makeText(this, getResources().getString(R.string.have_sent_msg), Toast.LENGTH_SHORT).show();
            CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(tvGetVerity, 60000, 1000);
            mCountDownTimerUtils.start();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获取验证码倒计时
     */
    public class CountDownTimerUtils extends CountDownTimer {
        private TextView mTextView;

        public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            this.mTextView = textView;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTextView.setClickable(false);
            //设置倒计时时间
            mTextView.setText(millisUntilFinished / 1000 + getResources().getString(R.string.again_send));
            mTextView.setTextColor(Color.GRAY);
            SmsTimeUtils.check(SmsTimeUtils.SETTING_FINANCE_ACCOUNT_TIME, false);
            SmsTimeUtils.startCountdown(tvGetVerity);
        }

        @Override
        public void onFinish() {
            mTextView.setText(getResources().getString(R.string.get_verify));
            mTextView.setClickable(true);
            mTextView.setTextColor(getResources().getColor(R.color.colorBlue, null));
        }
    }
}
