package cn.edu.scujcc.loginandregister.view;

/**
 * @author Administrator
 */
public interface UserView {
    /**
     * 登陆成功
     */
    void loginSuccess(String result);

    /**
     * 登陆失败
     */
    void loginFailure(String msg);

    /**
     * 注册成功
     */
    void registerSuccess(String result);

    /**
     * 注册失败
     */
    void registerFailure(String msg);

    /**
     * 登录失败
     */
    void networkError(Throwable t);
}

