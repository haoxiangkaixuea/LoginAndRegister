package cn.edu.scujcc.loginandregister.view;

/**
 * @author Administrator
 */
public interface RegisterView {

    /**
     * 注册成功
     *
     * @param result 返回成功数据
     */
    void registerSuccess(String result);

    /**
     * 注册失败
     *
     * @param msg 返回失败数据
     */
    void registerFailure(String msg);

    /**
     * 网络错误
     *
     * @param t 返回错误数据
     */
    void networkError(Throwable t);

    /**
     * 获取验证码数据
     *
     * @param result 获取验证码数据
     */
    void getData(String result);

    /**
     * 返回数据
     *
     * @param message 返回message数据
     */
    void getMessage(String message);
}

