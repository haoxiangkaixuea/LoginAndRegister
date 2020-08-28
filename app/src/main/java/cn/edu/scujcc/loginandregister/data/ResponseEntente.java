package cn.edu.scujcc.loginandregister.data;

import androidx.annotation.NonNull;

/**
 * GSON 获取数据
 *
 * @author Administrator
 */
public class ResponseEntente {
    private String code;
    private String message;
    private Context context;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

        @NonNull
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
