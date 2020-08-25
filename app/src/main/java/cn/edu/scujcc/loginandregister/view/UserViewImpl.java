package cn.edu.scujcc.loginandregister.view;

/**
 * @author Administrator
 */
public class UserViewImpl implements UserView {
    private String result;

    @Override
    public void loginSuccess(String result) {

    }

    @Override
    public void loginFailure(String msg) {

    }

    @Override
    public void registerSuccess(String result) {
        this.result = result;
    }

    @Override
    public void registerFailure(String msg) {

    }

    @Override
    public void networkError(Throwable t) {

    }

    public String getResult() {
        return result;
    }
}
