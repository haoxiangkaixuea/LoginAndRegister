package cn.edu.scujcc.loginandregister.view;

/**
 * @author Administrator
 */
public interface LoginView {
    /**
     * 登录成功
     *
     * @param result 返回登录数据
     */
    void loginSuccess(String result);

    /**
     * 登录失败
     *
     * @param msg 返回失败数据
     */
    void loginFailure(String msg);

    /**
     * 网路错误
     *
     * @param t 返回错误疏浚
     */
    void networkError(Throwable t);

    /**
     * 返回数据
     *
     * @param message 返回message数据
     */
    void getMessage(String message);
}
