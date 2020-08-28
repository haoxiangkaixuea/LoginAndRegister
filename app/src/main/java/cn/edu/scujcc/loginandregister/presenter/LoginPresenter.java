package cn.edu.scujcc.loginandregister.presenter;

import android.util.Log;

import cn.edu.scujcc.loginandregister.api.LoginCallBack;
import cn.edu.scujcc.loginandregister.model.LoginModel;
import cn.edu.scujcc.loginandregister.model.LoginUser;
import cn.edu.scujcc.loginandregister.view.LoginView;

/**
 * @author Administrator
 */
public class LoginPresenter {
    private static final String TAG = "LoginPresenter";
    LoginModel loginModel = LoginModel.getInstance();
    /**
     * View接口
     */
    private LoginView loginView;

    public LoginPresenter(LoginView view) {
        this.loginView = view;
    }

    public void login(LoginUser loginUser) {

        loginModel.getData(loginUser, new LoginCallBack() {
            @Override
            public void onLoginSuccess(String result) {
                Log.d(TAG, "result" + result);
                loginView.loginSuccess(result);
            }

            @Override
            public void onLoginFailure(String msg) {
                loginView.loginFailure(msg);
                Log.d(TAG, "msg" + msg);
            }

            @Override
            public void networkError(Throwable t) {
                loginView.networkError(t);
            }

            @Override
            public void getMessage(String message) {
                loginView.getMessage(message);
                Log.d(TAG, "message" + message);
            }
        });
    }
}

