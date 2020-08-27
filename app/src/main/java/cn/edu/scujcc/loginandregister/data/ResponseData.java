package cn.edu.scujcc.loginandregister.data;

/**
 * @author Administrator
 */
public class ResponseData {
    private String code;
    private Context context;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public class Context {
        private String verificationCode;
        private String tokenId;

        @Override
        public String toString() {
            return "ContextData{" +
                    "verificationCode='" + verificationCode + '\'' +
                    ", tokenId='" + tokenId + '\'' +
                    '}';
        }

        public String getVerificationCode() {
            return verificationCode;
        }

        public void setVerificationCode(String verificationCode) {
            this.verificationCode = verificationCode;
        }

        public String getTokenId() {
            return tokenId;
        }

        public void setTokenId(String tokenId) {
            this.tokenId = tokenId;
        }
    }
}
