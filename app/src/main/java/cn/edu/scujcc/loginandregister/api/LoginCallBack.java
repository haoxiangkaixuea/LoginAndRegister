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
     * 登录失败
     */
    void networkError(Throwable t);

}
