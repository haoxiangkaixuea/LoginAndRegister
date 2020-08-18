package cn.edu.scujcc.loginandregister;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * <pre>
 *     author : Administrator
 *     e-mail : xxx@xx
 *     time   : 2020/08/18
 *     desc   :
 *     version: 1.0
 * </pre>
 *
 * @author Administrator
 */
public class FocusOn implements TextWatcher {
    private EditText editUsername;
    private EditText editPassword;
    private Button btnSubmit;

    public void FocusOn(final Button btnSubmit, final EditText editUsername, final EditText editPassword) {

        this.editUsername = editUsername;
        this.editPassword = editPassword;
        this.btnSubmit = btnSubmit;
        if (editUsername != null) {
            editUsername.addTextChangedListener(FocusOn.this);
        } else if (editPassword != null) {
            editPassword.addTextChangedListener(FocusOn.this);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }
    @Override
    public void afterTextChanged(Editable editable) {
        if (TextUtils.isEmpty(editUsername.getText()) || TextUtils.isEmpty(editPassword.getText())) {
            btnSubmit.setEnabled(Boolean.FALSE);
        } else {
            btnSubmit.setEnabled(Boolean.TRUE);
        }
    }
}
