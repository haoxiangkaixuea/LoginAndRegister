package cn.edu.scujcc.loginandregister.presenter;

import cn.edu.scujcc.loginandregister.api.RegisterCallBack;
import cn.edu.scujcc.loginandregister.model.RegisterModel;
import cn.edu.scujcc.loginandregister.util.LogUtils;
import cn.edu.scujcc.loginandregister.view.RegisterView;

/**
 * @author Administrator
 */
public class RegisterPresenter {

    private static final String TAG = "RegisterPresenter";
    RegisterModel registerModel = RegisterModel.getInstance();
    /**
     * View接口
     */
    private RegisterView registerView;

    public RegisterPresenter(RegisterView view) {
        this.registerView = view;
    }

    public void register() {
        registerModel.getData(new RegisterCallBack() {
            @Override
            public void onRegisterSuccess(String result) {
                registerView.registerSuccess(result);
                registerView.getData(result);
                LogUtils.d(TAG, "result " + result);
            }

            @Override
            public void onRegisterFailure(String msg) {
                registerView.registerFailure(msg);
                LogUtils.d(TAG, "msg " + msg);
            }

            @Override
            public void networkError(Throwable t) {
                registerView.networkError(t);
                LogUtils.d(TAG, "t " + t);
            }

            @Override
            public void getMessage(String message) {
                registerView.getMessage(message);
            }
        });
    }
}
