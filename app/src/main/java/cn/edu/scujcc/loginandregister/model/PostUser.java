package cn.edu.scujcc.loginandregister.model;

/**
 * <pre>
 *     author : Administrator
 *     e-mail : xxx@xx
 *     time   : 2020/08/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class PostUser {
    private String userAccount;
    private String password;

    public PostUser(String userAccount, String password) {
        this.userAccount = userAccount;
        this.password = password;
    }
}
