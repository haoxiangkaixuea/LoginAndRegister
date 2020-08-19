package cn.edu.scujcc.loginandregister;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.loginandregister.listener.EditTextUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText et1 = (EditText) findViewById(R.id.et1);
        ImageView bt = findViewById(R.id.imageView);

        EditTextUtils.clearButtonListener(et1, bt);

    }
}