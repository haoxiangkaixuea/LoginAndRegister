package cn.edu.scujcc.loginandregister.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.core.internal.deps.guava.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.edu.scujcc.loginandregister.R;
import cn.edu.scujcc.loginandregister.Utils.EditTextUtils;
import cn.edu.scujcc.loginandregister.listener.UserLab;
import cn.edu.scujcc.loginandregister.model.PostUser;

/**
 * @author Administrator
 */
public class RegisterActivity extends AppCompatActivity {
    private static final int VERIFY_FAILURE = -5;
    private static final int VERIFY_SUCCESS = 5;
    private static final String TAG = "RegisterActivity";
    public Intent verificationCode;
    private EditText editTellPhone;
    private EditText editVerify;
    private Button btnNext;
    private CheckBox checkBox;
    private ImageView imageTellPhone;
    private ImageView imageVerify;
    private ImageView imageBack;
    private TextView tvGetVerity;
    private int startTime = 60;
    private Boolean isRunning = true;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case UserLab.MSG_LOGIN_SUCCESS:
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_success), Toast.LENGTH_LONG).show();
                    break;
                case UserLab.MSG_PASSWORD_ERROR:
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_failure), Toast.LENGTH_LONG).show();
                    break;
                case UserLab.MSG_NETWORK_ERROR:
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_LONG).show();
                    break;
                case VERIFY_FAILURE:
                    handler.removeMessages(0);//停止
                    tvGetVerity.setText(getResources().getString(R.string.get_verify));
                    tvGetVerity.setEnabled(true);
                    tvGetVerity.setTextColor(getResources().getColor(R.color.colorBlue));
                    break;
                case VERIFY_SUCCESS:
                    handler.sendMessageDelayed(Message.obtain(handler, 0), 1000);
                    String m = String.valueOf(msg.obj);
                    editVerify.setText(m);
                    break;
                default:
            }
        }
    };
    private UserLab userLab = UserLab.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTellPhone = findViewById(R.id.edit_tellPhone);
        tvGetVerity = findViewById(R.id.get_verify);
        editVerify = findViewById(R.id.edit_verify);
        btnNext = findViewById(R.id.btn_login);
        checkBox = findViewById(R.id.cb_login);
        imageTellPhone = findViewById(R.id.image_tellPhone);
        imageVerify = findViewById(R.id.image_verify);
        imageBack = findViewById(R.id.image_back);
        imageBack.setOnClickListener(v -> {
            Intent intentBack = new Intent(this, LoginActivity.class);
            startActivity(intentBack);
        });

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
            String tellPhone = editTellPhone.getText().toString();
            String verify = editVerify.getText().toString();
            PostUser postUser = null;
            userLab.register(postUser, handler);
        });
        tvGetVerity.setOnClickListener(view -> {
            handler.sendMessageDelayed(Message.obtain(handler, 0), 1000);
            tvGetVerity.setText(startTime + getResources().getString(R.string.again_send));
            tvGetVerity.setTextColor(Color.GRAY);
            tvGetVerity.setEnabled(false);
            doWork();
        });
    }

    public void doWork() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(this::timerTaskThread);
        singleThreadPool.shutdown();
    }

    private void timerTaskThread() {
        Message msg = handler.obtainMessage();

        startTime = 60;
        startTime--;
        handler.sendMessageDelayed(Message.obtain(handler, 0), 1000);
        msg.arg1 = startTime;
        Log.d(TAG,"startTime"+startTime);
        if (startTime == 0) {
            isRunning = false;
            msg.what = VERIFY_FAILURE;
        } else {
            msg.what = VERIFY_SUCCESS;
            Log.d(TAG, "msg.arg1" + msg.arg1);
        }
        handler.sendMessage(msg);
    }
}
