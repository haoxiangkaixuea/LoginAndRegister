package cn.edu.scujcc.loginandregister;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

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
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTellPhone = findViewById(R.id.edit_tellPhone);
        editVerify = findViewById(R.id.edit_verify);
        btnNext = findViewById(R.id.btn_login);
        checkBox = findViewById(R.id.cb_login);
        imageTellPhone = findViewById(R.id.image_tellPhone);
        imageVerify = findViewById(R.id.image_verify);

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
    }
}