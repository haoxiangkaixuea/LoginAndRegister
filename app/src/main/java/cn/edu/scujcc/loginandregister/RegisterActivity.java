package cn.edu.scujcc.loginandregister;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.loginandregister.Utils.CheckEditUtils;
import cn.edu.scujcc.loginandregister.listener.EditTextChangeListener;

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

        //1.创建工具类对象 设置监听空间
        CheckEditUtils checkEditForButton = new CheckEditUtils(btnSubmit);
        //2.把所有被监听的EditText设置进去
        checkEditForButton.addEditText(editUsername, editPassword);
        //3.根据接口回调的方法,分别进行操作
        checkEditForButton.setListener(new EditTextChangeListener() {
            @Override
            public void allHasContent(boolean isHasContent) {
                if (isHasContent) {
                        btnSubmit.setBackgroundResource(R.drawable.btn_focus_on);
                    //btnSubmit.setTextColor(Color.parseColor("#FF148F"));
                } else {
                    btnSubmit.setBackgroundResource(R.drawable.btn_normal);
                    //btnSubmit.setTextColor(Color.parseColor("#000000"));
                }
            }
        });
        btnSubmit.setOnClickListener(v -> {
            Toast.makeText(RegisterActivity.this, "被点击了", Toast.LENGTH_SHORT).show();
        });
    }
}