package cn.edu.scujcc.loginandregister.Utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import cn.edu.scujcc.loginandregister.R;

/**
 * @author Administrator
 */
public class SomeMonitorEditText implements TextWatcher {

    private Button button;
    private EditText[] text;
    private CheckBox checkBox;

    public void SetMonitorEditText(final Button button, final CheckBox checkBox, final EditText... text) {

        this.button = button;
        this.text = text;
        this.checkBox=checkBox;

        for (int i = 0; i < text.length; i++) {
            if (text[i] != null) {
                text[i].addTextChangedListener(SomeMonitorEditText.this);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        for (int i = 0; i < text.length; i++) {
            if (text[i].length() == 0) {
                button.setBackgroundResource(R.drawable.btn_focus_on);
                button.setEnabled(false);
                return;
            } else {
                button.setBackgroundResource(R.drawable.btn_normal);
                button.setEnabled(true);
            }
        }
    }
}
