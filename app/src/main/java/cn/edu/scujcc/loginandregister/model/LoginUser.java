package cn.edu.scujcc.loginandregister.model;

import androidx.annotation.NonNull;

/**
 * @author Administrator
 */
public class LoginUser {
    String userAccount;
    String password;

    public LoginUser(String name, String pwd) {
        this.userAccount = name;
        this.password = pwd;
    }

    @NonNull
    @Override
    public String toString() {
        return "LoginUser{" +
                "userAccount='" + userAccount + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
