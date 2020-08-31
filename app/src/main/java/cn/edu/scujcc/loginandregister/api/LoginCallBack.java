package cn.edu.scujcc.loginandregister.api;

/**
 * @author Administrator
 */
public interface LoginCallBack {
    /**
     * 登录成功
     *
     * @param result 返回登录成功的结果
     */
    void onLoginSuccess(String result);

    /**
     * 登录失败
     *
     * @param msg 返回登录失败的结果
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
