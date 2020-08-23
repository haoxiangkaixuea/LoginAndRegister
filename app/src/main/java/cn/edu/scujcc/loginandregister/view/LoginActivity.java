package cn.edu.scujcc.loginandregister.view;

import android.app.Activity;
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

import java.util.LinkedList;
import java.util.List;

import cn.edu.scujcc.loginandregister.R;
import cn.edu.scujcc.loginandregister.Utils.EditTextUtils;
import cn.edu.scujcc.loginandregister.presenter.UserLab;

/**
 * @author Administrator
 */
public class LoginActivity extends AppCompatActivity {
    public static List<Activity> activityList = new LinkedList();
    private EditText editUsername;
    private EditText editPassword;
    private Button btnSubmit;
    private CheckBox checkBox;
    private ImageView imageUsername;
    private ImageView imagePassword;
    private ImageView imageClose;
    private TextView tvLogin;
    private UserLab userLab = UserLab.getInstance();
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case UserLab.MSG_LOGIN_SUCCESS:
                    loginSuccess();
                    break;
                case UserLab.MSG_PASSWORD_ERROR:
                    loginPasswordError();
                    break;
                case UserLab.MSG_NETWORK_ERROR:
                    loginNetworkError();
                    break;
                default:
            }
        }
    };

    private void loginNetworkError() {
        Toast.makeText(this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
    }

    private void loginPasswordError() {
        Toast.makeText(this, getResources().getString(R.string.login_failure), Toast.LENGTH_SHORT).show();
    }

    private void loginSuccess() {
        Toast.makeText(this, getResources().getString(R.string.login_success), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = findViewById(R.id.edit_username);
        editPassword = findViewById(R.id.edit_password);
        imageUsername = findViewById(R.id.image_username);
        imagePassword = findViewById(R.id.image_password);
        imageClose = findViewById(R.id.image_close);
        tvLogin = findViewById(R.id.text_login);
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
                    }
                } else {
                    btnSubmit.setBackgroundResource(R.drawable.btn_normal);
                    btnSubmit.setTextColor(Color.WHITE);
                }
            }
        });
        imageClose.setOnClickListener(view -> {
            exit();
        });
        btnSubmit.setOnClickListener(v -> {
            String userAccount = editUsername.getText().toString();
            String password = editPassword.getText().toString();
            userLab.login(null, handler);
        });
    }

    public void exit() {
        for (Activity act : activityList) {
            act.finish();
        }
        System.exit(0);
    }
}
