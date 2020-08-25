package cn.edu.scujcc.loginandregister.api;

/**
 * @author Administrator
 */
public interface RetrofitCallBack {
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
     * 登陆成功
     *
     * @param result 返回注册成功的结果
     */
    void onRegisterSuccess(String result);

    /**
     * 登录失败
     *
     * @param msg 返回注册失败的结果
     */
    void onRegisterFailure(String msg);
}
