package cn.edu.scujcc.loginandregister.view;

/**
 * @author Administrator
 */
public interface RegisterView {

    /**
     * 注册成功
     */
    void registerSuccess(String result);

    /**
     * 注册失败
     */
    void registerFailure(String msg);

    /**
     * 网络错误
     */
    void networkError(Throwable t);

    /**
     * 获取数据
     */
    void getData(String result);
}

