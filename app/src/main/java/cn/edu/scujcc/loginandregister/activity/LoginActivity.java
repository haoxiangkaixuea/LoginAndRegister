package cn.edu.scujcc.loginandregister.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;

import cn.edu.scujcc.loginandregister.R;
import cn.edu.scujcc.loginandregister.model.LoginUser;
import cn.edu.scujcc.loginandregister.presenter.LoginPresenter;
import cn.edu.scujcc.loginandregister.util.EditTextUtils;
import cn.edu.scujcc.loginandregister.view.LoginView;

/**
 * @author Administrator
 */
public class LoginActivity extends AppCompatActivity implements LoginView {
    private static final String TAG = "LoginActivity";
    public static List<Activity> activityList = new LinkedList<>();
    private LoginPresenter presenter;
    private EditText editUsername;
    private EditText editPassword;
    private Button btnSubmit;
    private CheckBox checkBox;
    private ImageView imageUsername;
    private ImageView imagePassword;
    private ImageView imageClose;
    private TextView tvLogin;
    private TextView tvDeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);
        editUsername = findViewById(R.id.edit_username);
        editPassword = findViewById(R.id.edit_password);
        imageUsername = findViewById(R.id.image_username);
        imagePassword = findViewById(R.id.image_password);
        imageClose = findViewById(R.id.image_close);
        tvLogin = findViewById(R.id.text_login);
        tvDeal = findViewById(R.id.deal_after);

        SpannableString spannableString = new SpannableString(getResources().getString(R.string.deal_front));
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA1A6B3")), 0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#477BEF")), 9, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA1A6B3")), 34, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#477BEF")), 36, 54, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDeal.setText(spannableString);


        tvLogin.setOnClickListener(v -> {
            Intent intentLogin = new Intent(this, RegisterActivity.class);
            startActivity(intentLogin);
        });

        EditTextUtils.clearButtonListener(editUsername, imageUsername);
        EditTextUtils.clearButtonListener(editPassword, imagePassword);
        btnSubmit = findViewById(R.id.btn_register);
        checkBox = findViewById(R.id.cb_register);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked()) {
                    boolean signUsername = editUsername.getText().length() > 0;
                    boolean signPassword = editPassword.getText().length() > 0;
                    if (signUsername & signPassword) {
                        btnSubmit.setBackgroundResource(R.drawable.btn_focus_on);
                        btnSubmit.setTextColor(R.drawable.button_font_style);
                        btnSubmit.setEnabled(true);
                    }
                } else {
                    btnSubmit.setBackgroundResource(R.drawable.btn_normal);
                    btnSubmit.setTextColor(Color.parseColor("#FFFFFF"));
                    btnSubmit.setEnabled(false);
                }
            }
        });
        imageClose.setOnClickListener(view -> {
            finish();
        });
        btnSubmit.setOnClickListener(v -> {
            String name = editUsername.getText().toString().trim();
            String pwd = editPassword.getText().toString().trim();
            LoginUser loginUser = new LoginUser(name, pwd);
            presenter.login(loginUser);
        });
    }

    @Override
    public void loginSuccess(String result) {
        Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_success), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFailure(String msg) {
        Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_failure), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void networkError(Throwable t) {
        Toast.makeText(LoginActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
    }
}
