package cn.edu.scujcc.loginandregister.presenter;

import cn.edu.scujcc.loginandregister.api.RegisterCallBack;
import cn.edu.scujcc.loginandregister.model.RegisterModel;
import cn.edu.scujcc.loginandregister.view.RegisterView;
import cn.edu.scujcc.loginandregister.view.UserViewImpl;

/**
 * <pre>
 *     author : Administrator
 *     e-mail : xxx@xx
 *     time   : 2020/08/26
 *     desc   :
 *     version: 1.0
 * </pre>
 *
 * @author Administrator
 */
public class RegisterPresenter {

    private static final String TAG = "RegisterPresenter";
    UserViewImpl userView = new UserViewImpl();
    private String getData;
    /**
     * View接口
     */
    private RegisterView registerView;

    public RegisterPresenter(RegisterView view) {
        this.registerView = view;
    }

    public void register() {
        RegisterModel.registerGetData(new RegisterCallBack() {
            @Override
            public void onRegisterSuccess(String result) {
                registerView.registerSuccess(result);
                registerView.getData(result);
            }

            @Override
            public void onRegisterFailure(String msg) {
                registerView.registerFailure(msg);
            }

            @Override
            public void networkError(Throwable t) {
                registerView.networkError(t);
            }
        });
    }
}
