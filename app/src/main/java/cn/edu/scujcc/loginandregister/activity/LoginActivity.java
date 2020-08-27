package cn.edu.scujcc.loginandregister.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;

import cn.edu.scujcc.loginandregister.R;
import cn.edu.scujcc.loginandregister.model.LoginUser;
import cn.edu.scujcc.loginandregister.presenter.LoginPresenter;
import cn.edu.scujcc.loginandregister.util.EditTextUtils;
import cn.edu.scujcc.loginandregister.util.StatusBarUtil;
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
    private ImageView imageUsername;
    private ImageView imagePassword;
    private ImageView imageClose;
    private TextView tvLogin;
    private TextView tvDeal;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏为透明色
        //setConditionColor();
        setContentView(R.layout.activity_login);

        //设置状态栏字体
        //setConditionText();
        ImmersionBar.with(this).statusBarDarkFont(true).init();
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

    private void setConditionColor() {
        Window window = getWindow();
        //After LOLLIPOP not translucent status bar
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Then call setStatusBarColor.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#000000"));
        }
        //第二个参数是想要设置的颜色
        //StatusBarCompatUtils.compat(this, Color.WHITE);
        //BarUtils.setStatusBarColor(this,Color.parseColor("#4C64AD"));
//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null){
//            actionBar.hide();
//        }
    }

    private void setConditionText() {
        StatusBarUtil.setTranslucentStatus(LoginActivity.this);
        StatusBarUtil.setStatusBarColor(this, Color.parseColor("#FAFAFA"));
        StatusBarUtil.setImmersiveStatusBar(this, false);
        StatusBarUtil.setStatusBarFontIconDark(this, StatusBarUtil.TYPE_M);
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
