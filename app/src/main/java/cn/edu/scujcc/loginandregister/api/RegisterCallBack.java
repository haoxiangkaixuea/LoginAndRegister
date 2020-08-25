package cn.edu.scujcc.loginandregister.api;

/**
 * @author Administrator
 */
public interface RegisterCallBack {

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

    /**
     * 登录失败
     */
    void networkError(Throwable t);
}
