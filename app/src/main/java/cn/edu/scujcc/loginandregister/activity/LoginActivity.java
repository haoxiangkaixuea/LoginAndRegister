package cn.edu.scujcc.loginandregister.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;

import cn.edu.scujcc.loginandregister.R;
import cn.edu.scujcc.loginandregister.presenter.LoginPresenter;
import cn.edu.scujcc.loginandregister.util.EditTextHintUtils;
import cn.edu.scujcc.loginandregister.util.EditTextUtils;
import cn.edu.scujcc.loginandregister.util.LogUtils;
import cn.edu.scujcc.loginandregister.util.ToastUtils;
import cn.edu.scujcc.loginandregister.view.LoginView;

/**
 * @author Administrator
 */
public class LoginActivity extends AppCompatActivity implements LoginView {
    private static final String TAG = "LoginActivity";
    private LoginPresenter presenter;
    private EditText editUsername;
    private EditText editPassword;
    private Button btnSubmit;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_login);

        //导航栏与状态栏设置
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        presenter = new LoginPresenter(this);
        editUsername = findViewById(R.id.edit_username);
        editPassword = findViewById(R.id.edit_password);
        TextView tvUsernameHint = findViewById(R.id.username_hint);
        TextView tvPasswordHint = findViewById(R.id.password_hint);
        ImageView imageUsername = findViewById(R.id.image_username);
        ImageView imagePassword = findViewById(R.id.image_password);
        ImageView imageClose = findViewById(R.id.image_close);
        Button tvLogin = findViewById(R.id.btn_login);

        setDeal();

        tvLogin.setOnClickListener(v -> {
            Intent intentLogin = new Intent(this, RegisterActivity.class);
            startActivity(intentLogin);
        });

        //输入框的清除按钮
        EditTextUtils.clearButtonListener(editUsername, imageUsername);
        EditTextUtils.clearButtonListener(editPassword, imagePassword);

        //按钮背景转换事件
        setButtonChange();
        //设置输入框hint文字
        EditTextHintUtils.textHintListener(editUsername, tvUsernameHint);
        EditTextHintUtils.textHintListener(editPassword, tvPasswordHint);

        imageClose.setOnClickListener(view -> finish());
    }

    private void setEditText() {

        editUsername.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (TextUtils.isEmpty(editUsername.getText()) || TextUtils.isEmpty(editPassword.getText())) {
                    btnSubmit.setBackgroundResource(R.drawable.btn_normal);
                    btnSubmit.setTextColor(Color.parseColor("#FFFFFF"));
                    btnSubmit.setClickable(false);
                } else {
                    btnSubmit.setBackgroundResource(R.drawable.button_onclick);
                    btnSubmit.setTextColor(R.drawable.button_font_style);
                    btnSubmit.setClickable(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        editPassword.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(editUsername.getText()) || TextUtils.isEmpty(editPassword.getText())) {
                    btnSubmit.setBackgroundResource(R.drawable.btn_normal);
                    btnSubmit.setTextColor(Color.parseColor("#FFFFFF"));
                    btnSubmit.setClickable(false);
                } else {
                    btnSubmit.setBackgroundResource(R.drawable.button_onclick);
                    btnSubmit.setTextColor(R.drawable.button_font_style);
                    btnSubmit.setClickable(true);
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

    private void setButtonChange() {
        btnSubmit = findViewById(R.id.btn_register);
        checkBox = findViewById(R.id.cb_register);
        checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (checkBox.isChecked()) {
                setEditText();
                boolean signUsername = editUsername.getText().length() > 0;
                boolean signPassword = editPassword.getText().length() > 0;
                if (signUsername && signPassword) {
                    btnSubmit.setBackgroundResource(R.drawable.button_onclick);
                    btnSubmit.setTextColor(R.drawable.button_font_style);
                    btnSubmit.setClickable(true);
                    btnSubmit.setOnClickListener(v -> presenter.login());
                }
            } else {
                btnSubmit.setBackgroundResource(R.drawable.btn_normal);
                btnSubmit.setTextColor(Color.WHITE);
                btnSubmit.setClickable(false);
            }
        });
    }

    private void setDeal() {
        TextView tvDeal = findViewById(R.id.deal_after);
        SpannableString spannableString = new SpannableString(getResources().getString(R.string.deal_front));
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA1A6B3")), 0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#477BEF")), 9, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA1A6B3")), 34, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#477BEF")), 36, 54, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDeal.setText(spannableString);
    }

    @Override
    public void loginSuccess(String result) {
        ToastUtils.shortToast(LoginActivity.this, getResources().getString(R.string.login_success));
    }

    @Override
    public void loginFailure(String msg) {
        LogUtils.d(TAG, "msg" + msg);
        ToastUtils.shortToast(LoginActivity.this, getResources().getString(R.string.login_failure) + msg);
    }

    @Override
    public void networkError(Throwable t) {
        ToastUtils.shortToast(LoginActivity.this, getResources().getString(R.string.network_error));
    }

    @Override
    public void getMessage(String message) {
        ToastUtils.shortToast(LoginActivity.this, message);
    }
}

