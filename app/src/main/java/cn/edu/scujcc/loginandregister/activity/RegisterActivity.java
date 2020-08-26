package cn.edu.scujcc.loginandregister.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.loginandregister.R;
import cn.edu.scujcc.loginandregister.model.RegisterUser;
import cn.edu.scujcc.loginandregister.presenter.RegisterPresenter;
import cn.edu.scujcc.loginandregister.util.EditTextUtils;
import cn.edu.scujcc.loginandregister.util.SmsTimeUtils;
import cn.edu.scujcc.loginandregister.view.RegisterView;

/**
 * @author Administrator
 */
public class RegisterActivity extends AppCompatActivity implements RegisterView {
    private static final int TELL_MAX = 11;
    private static final String TAG = "RegisterActivity";
    private final SpannableStringBuilder style = new SpannableStringBuilder();
    RegisterPresenter presenter;
    private EditText editTellPhone;
    private EditText editVerify;
    private Button btnNext;
    private CheckBox checkBox;
    private ImageView imageTellPhone;
    private ImageView imageVerify;
    private ImageView imageBack;
    private TextView tvGetVerity;
    private TextView tvGoLogin;
    private TextView tvDealFront;
    private String showVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        presenter = new RegisterPresenter(this);
        editTellPhone = findViewById(R.id.edit_tellPhone);
        tvGetVerity = findViewById(R.id.get_verify);
        editVerify = findViewById(R.id.edit_verify);
        btnNext = findViewById(R.id.btn_login);
        checkBox = findViewById(R.id.cb_login);
        imageTellPhone = findViewById(R.id.image_tellPhone);
        imageVerify = findViewById(R.id.image_verify);
        imageBack = findViewById(R.id.image_back);
        tvGoLogin = findViewById(R.id.text_go_login);
        tvDealFront = findViewById(R.id.deal_front);
        SpannableString spannableString = new SpannableString(getResources().getString(R.string.deal_front));
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA1A6B3")), 0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#477BEF")), 9, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA1A6B3")), 34, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#477BEF")), 36, 54, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDealFront.setText(spannableString);

        style.append(getResources().getString(R.string.deal_after));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };
        style.setSpan(clickableSpan, 8, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvGoLogin.setMovementMethod(LinkMovementMethod.getInstance());
        tvGoLogin.setText(style);

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
                    if (signUsername && signPassword) {
                        btnNext.setBackgroundResource(R.drawable.btn_focus_on);
                        btnNext.setTextColor(R.drawable.button_font_style);
                        btnNext.setEnabled(true);
                    }
                } else {
                    btnNext.setBackgroundResource(R.drawable.btn_normal);
                    btnNext.setTextColor(Color.WHITE);
                    btnNext.setEnabled(false);
                }
            }
        });
        editTellPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvGetVerity.setTextColor(Color.parseColor("#FFA1A6B3"));
                tvGetVerity.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && s.length() == TELL_MAX) {
                    tvGetVerity.setEnabled(true);
                    tvGetVerity.setTextColor(Color.parseColor("#FF477BEF"));
                } else {
                    tvGetVerity.setTextColor(Color.parseColor("#FFA1A6B3"));
                    tvGetVerity.setEnabled(false);
                }
            }
        });
        btnNext.setOnClickListener(v -> {
            Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_success), Toast.LENGTH_LONG).show();
        });
        tvGetVerity.setOnClickListener(view -> {
            boolean tellPhone = editTellPhone.getText().length() == TELL_MAX;
            if (tellPhone) {
                String tell = editTellPhone.getText().toString().trim();
                String verify = editVerify.getText().toString().trim();
                RegisterUser registerUser = new RegisterUser(tell, verify);
                presenter.register(registerUser);
                Toast.makeText(this, getResources().getString(R.string.have_sent_msg), Toast.LENGTH_SHORT).show();
                CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(tvGetVerity, 60000, 1000);
                mCountDownTimerUtils.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void registerSuccess(String result) {
    }

    @Override
    public void registerFailure(String msg) {

    }

    @Override
    public void networkError(Throwable t) {
        Toast.makeText(this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getData(String result) {
        editVerify.setText(result);
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

        @SuppressLint("SetTextI18n")
        @Override
        public void onTick(long millisUntilFinished) {
            mTextView.setClickable(false);
            //设置倒计时时间
            mTextView.setText(millisUntilFinished / 1000 + getResources().getString(R.string.again_send));
            mTextView.setTextColor(Color.parseColor("#FFA1A6B3"));
            SmsTimeUtils.check(SmsTimeUtils.SETTING_FINANCE_ACCOUNT_TIME, false);
            SmsTimeUtils.startCountdown(tvGetVerity);
        }

        @Override
        public void onFinish() {
            mTextView.setText(getResources().getString(R.string.get_verify));
            mTextView.setClickable(true);
            mTextView.setTextColor(Color.parseColor("#FF477BEF"));
        }
    }
}
