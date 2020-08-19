package cn.edu.scujcc.loginandregister;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText editUsername;
    private EditText editPassword;
    private Button btnSubmit;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editUsername = findViewById(R.id.edit_username);
        editPassword = findViewById(R.id.edit_password);
        btnSubmit = findViewById(R.id.btn_register);
        checkBox = findViewById(R.id.cbEat);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked()) {
                    boolean signUsername = editUsername.getText().length() > 0;
                    boolean signPassword = editPassword.getText().length() > 0;
                    if (signUsername & signPassword) {
                        btnSubmit.setBackgroundResource(R.drawable.btn_focus_on);
                    }
                } else {
                    btnSubmit.setBackgroundResource(R.drawable.btn_normal);
                }
            }
        });
        btnSubmit.setOnClickListener(v -> {
            Toast.makeText(RegisterActivity.this, "被点击了", Toast.LENGTH_SHORT).show();
        });
    }
}
