package cn.edu.scujcc.loginandregister;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText editUsername;
    private EditText editPassword;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editUsername = findViewById(R.id.edit_username);
        editPassword = findViewById(R.id.edit_password);
        btnSubmit = findViewById(R.id.btn_register);
        //new SomeMonitorEditText().SetMonitorEditText(btnSubmit, editUsername, editPassword);
        new FocusOn().FocusOn(btnSubmit, editUsername, editPassword);

        btnSubmit.setOnClickListener(v -> {
            Toast.makeText(RegisterActivity.this, "被点击了", Toast.LENGTH_SHORT).show();
        });
    }



}