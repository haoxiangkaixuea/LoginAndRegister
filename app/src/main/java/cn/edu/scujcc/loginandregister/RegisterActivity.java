package cn.edu.scujcc.loginandregister;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

import cn.edu.scujcc.loginandregister.listener.EditTextUtils;

public class RegisterActivity extends AppCompatActivity {
    public static List<Activity> activityList = new LinkedList();
    private EditText editUsername;
    private EditText editPassword;
    private Button btnSubmit;
    private CheckBox checkBox;
    private ImageView imageUsername;
    private ImageView imagePassword;
    private ImageView imageClose;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editUsername = findViewById(R.id.edit_username);
        editPassword = findViewById(R.id.edit_password);
        imageUsername = findViewById(R.id.image_username);
        imagePassword = findViewById(R.id.image_password);
        imageClose = findViewById(R.id.image_close);
        tvLogin = findViewById(R.id.text_login);
        tvLogin.setOnClickListener(v -> {
            Intent intentLogin = new Intent(this, LoginActivity.class);
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
            Toast.makeText(RegisterActivity.this, "被点击了", Toast.LENGTH_SHORT).show();
        });
    }

    public void exit() {
        for (Activity act : activityList) {
            act.finish();
        }
        System.exit(0);
    }
}
