package cn.edu.scujcc.loginandregister.model;

/**
 * </pre>
 * @author Administrator
 */
public class RegisterUser {
    private String tellPhone;
    private String verity;

    @Override
    public String toString() {
        return "PostUser{" +
                "tellPhone='" + tellPhone + '\'' +
                ", verity='" + verity + '\'' +
                '}';
    }

    public String getTellPhone() {
        return tellPhone;
    }

    public void setTellPhone(String tellPhone) {
        this.tellPhone = tellPhone;
    }

    public String getVerity() {
        return verity;
    }

    public void setVerity(String verity) {
        this.verity = verity;
    }
}
