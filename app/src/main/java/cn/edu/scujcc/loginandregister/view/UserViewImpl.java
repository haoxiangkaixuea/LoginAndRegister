package cn.edu.scujcc.loginandregister.view;

import cn.edu.scujcc.loginandregister.api.RegisterCallBack;

/**
 * @author Administrator
 */
public class UserViewImpl implements RegisterCallBack {
    private String result;
    private String data;

    @Override
    public void onRegisterSuccess(String result) {

    }

    @Override
    public void onRegisterFailure(String msg) {

    }

    @Override
    public void networkError(Throwable t) {

    }

//    public String getResult() {
//        return result;
//    }
}
