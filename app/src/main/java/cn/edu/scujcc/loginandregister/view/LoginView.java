package cn.edu.scujcc.loginandregister.view;

/**
 * @author Administrator
 */
public interface LoginView {
    /**
     * 登陆成功
     */
    void loginSuccess(String result);

    /**
     * 登陆失败
     */
    void loginFailure(String msg);

    /**
     * 登录失败
     */
    void networkError(Throwable t);
}
