package cn.edu.scujcc.loginandregister.presenter;

import cn.edu.scujcc.loginandregister.interfaces.LoginCallBack;
import cn.edu.scujcc.loginandregister.model.LoginModel;
import cn.edu.scujcc.loginandregister.util.LogUtils;
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

    public void login() {

        loginModel.getData(new LoginCallBack() {
            @Override
            public void onLoginSuccess(String result) {
                LogUtils.d(TAG, "result" + result);
                loginView.loginSuccess(result);
            }

            @Override
            public void onLoginFailure(String msg) {
                loginView.loginFailure(msg);
                LogUtils.d(TAG, "msg" + msg);
            }

            @Override
            public void networkError(Throwable t) {
                loginView.networkError(t);
            }

            @Override
            public void getMessage(String message) {
                loginView.getMessage(message);
                LogUtils.d(TAG, "message" + message);
            }
        });
    }
}

