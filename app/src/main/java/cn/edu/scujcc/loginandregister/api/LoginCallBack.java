package cn.edu.scujcc.loginandregister.api;

/**
 * @author Administrator
 */
public interface LoginCallBack {
    /**
     * 登陆成功
     *
     * @param result 返回登陆成功的结果
     */
    void onLoginSuccess(String result);

    /**
     * 登录失败
     *
     * @param msg 返回登陆失败的结果
     */
    void onLoginFailure(String msg);

    /**
     * 网络错误
     *
     * @param t 返回失败错误
     */
    void networkError(Throwable t);

    /**
     * 返回数据
     *
     * @param message 返回message数据
     */
    void getMessage(String message);

}
