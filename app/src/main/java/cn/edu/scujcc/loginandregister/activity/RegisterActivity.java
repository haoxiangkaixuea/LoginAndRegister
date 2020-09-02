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
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;

import cn.edu.scujcc.loginandregister.R;
import cn.edu.scujcc.loginandregister.constant.Constants;
import cn.edu.scujcc.loginandregister.presenter.RegisterPresenter;
import cn.edu.scujcc.loginandregister.util.EditTextUtils;
import cn.edu.scujcc.loginandregister.util.TimeUtils;
import cn.edu.scujcc.loginandregister.util.ToastUtils;
import cn.edu.scujcc.loginandregister.view.RegisterView;

/**
 * @author Administrator
 */
public class RegisterActivity extends AppCompatActivity implements RegisterView {
    private static final int TELL_MAX = 11;
    private final SpannableStringBuilder style = new SpannableStringBuilder();
    RegisterPresenter presenter;
    private EditText editTellPhone;
    private EditText editVerify;
    private Button btnNext;
    private CheckBox checkBox;
    private TextView tvGetVerity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImmersionBar.with(this).statusBarDarkFont(true).init();
        presenter = new RegisterPresenter(this);
        editTellPhone = findViewById(R.id.edit_tellPhone);
        tvGetVerity = findViewById(R.id.get_verify);
        editVerify = findViewById(R.id.edit_verify);
        btnNext = findViewById(R.id.btn_login);
        checkBox = findViewById(R.id.cb_login);
        ImageView imageTellPhone = findViewById(R.id.image_tellPhone);
        ImageView imageVerify = findViewById(R.id.image_verify);
        ImageView imageBack = findViewById(R.id.image_back);

        //设置协议
        setDealFront();

        //设置底部登录事件
        setBottomLogin();

        imageBack.setOnClickListener(v -> {
            Intent intentBack = new Intent(this, LoginActivity.class);
            startActivity(intentBack);
        });

        //输入框的清除按钮事件
        EditTextUtils.clearButtonListener(editTellPhone, imageTellPhone);
        EditTextUtils.clearButtonListener(editVerify, imageVerify);

        //按钮背景转换事件
        setRegisterButtonChange();

        //设置文字验证码颜色改变事件
        setVerifyChange();

        //设置文字验证码倒计时事件
        setVerifyTime();

        //添加验证码输入框点击事件
        setVerifyClick();
    }

    private void setVerifyClick() {
        editVerify.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editTellPhone.getText().length() < Constants.VERITY_LENGTH) {
                    ToastUtils.shortToast(RegisterActivity.this, getResources().getString(R.string.tell_true));
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setVerifyTime() {
        tvGetVerity.setOnClickListener(view -> {
            boolean tellPhone = editTellPhone.getText().length() == TELL_MAX;
            if (tellPhone) {
                presenter.register();
                ToastUtils.shortToast(RegisterActivity.this, getResources().getString(R.string.have_sent_msg));
                new Time(60000, 1000).start();
            }
        });
    }

    private void setVerifyChange() {
        editTellPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvGetVerity.setTextColor(Color.parseColor("#FFA1A6B3"));
                tvGetVerity.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvGetVerity.setTextColor(Color.parseColor("#FFA1A6B3"));
                tvGetVerity.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == TELL_MAX) {
                    tvGetVerity.setEnabled(true);
                    tvGetVerity.setTextColor(Color.parseColor("#FF477BEF"));
                } else {
                    tvGetVerity.setTextColor(Color.parseColor("#FFA1A6B3"));
                    tvGetVerity.setEnabled(false);
                }
            }
        });
    }

    private void setEditText() {

        editTellPhone.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (TextUtils.isEmpty(editTellPhone.getText()) || TextUtils.isEmpty(editVerify.getText())) {
                    btnNext.setBackgroundResource(R.drawable.btn_normal);
                    btnNext.setTextColor(Color.parseColor("#FFFFFF"));
                    btnNext.setClickable(false);
                } else {
                    btnNext.setBackgroundResource(R.drawable.button_onclick);
                    btnNext.setTextColor(R.drawable.button_font_style);
                    btnNext.setClickable(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        editVerify.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(editTellPhone.getText()) || TextUtils.isEmpty(editVerify.getText())) {
                    btnNext.setBackgroundResource(R.drawable.btn_normal);
                    btnNext.setTextColor(Color.parseColor("#FFFFFF"));
                    btnNext.setClickable(false);
                } else {
                    btnNext.setBackgroundResource(R.drawable.button_onclick);
                    btnNext.setTextColor(R.drawable.button_font_style);
                    btnNext.setClickable(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void setRegisterButtonChange() {
        checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (checkBox.isChecked()) {
                setEditText();
                boolean signUsername = editTellPhone.getText().length() > 0;
                boolean signPassword = editVerify.getText().length() > 0;
                if (signUsername && signPassword) {
                    btnNext.setBackgroundResource(R.drawable.button_onclick);
                    btnNext.setTextColor(R.drawable.button_font_style);
                    btnNext.setClickable(true);
                    btnNext.setOnClickListener(v -> ToastUtils.shortToast(RegisterActivity.this, getResources().getString(R.string.register_success)));
                }
            } else {
                btnNext.setBackgroundResource(R.drawable.btn_normal);
                btnNext.setTextColor(Color.WHITE);
                btnNext.setClickable(false);
            }
        });
    }

    private void setBottomLogin() {
        TextView tvGoLogin = findViewById(R.id.text_go_login);
        style.append(getResources().getString(R.string.deal_after));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };
        style.setSpan(clickableSpan, 8, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvGoLogin.setMovementMethod(LinkMovementMethod.getInstance());
        tvGoLogin.setText(style);
    }

    private void setDealFront() {
        TextView tvDealFront = findViewById(R.id.deal_front);
        SpannableString spannableString = new SpannableString(getResources().getString(R.string.deal_front));
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA1A6B3")), 0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#477BEF")), 9, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA1A6B3")), 34, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#477BEF")), 36, 54, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDealFront.setText(spannableString);
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
        ToastUtils.shortToast(RegisterActivity.this, msg);
    }

    @Override
    public void networkError(Throwable t) {
        ToastUtils.shortToast(RegisterActivity.this, getResources().getString(R.string.network_error));
    }

    @Override
    public void getData(String result) {
        editVerify.setText(result);
    }

    @Override
    public void getMessage(String message) {
        ToastUtils.shortToast(RegisterActivity.this, message);
    }

    /**
     * 获取验证码倒计时
     */
    public class Time extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public Time(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onTick(long millisUntilFinished) {
            tvGetVerity.setText(TimeUtils.currentlyTime(millisUntilFinished) + getResources().getString(R.string.again_send));
            tvGetVerity.setTextColor(Color.parseColor("#FFA1A6B3"));
        }

        @Override
        public void onFinish() {
            tvGetVerity.setText(getResources().getString(R.string.get_verify));
            tvGetVerity.setClickable(true);
            tvGetVerity.setTextColor(Color.parseColor("#FF477BEF"));
        }
    }
}
