package cn.edu.scujcc.loginandregister.interfaces;

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
