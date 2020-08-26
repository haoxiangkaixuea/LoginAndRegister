package cn.edu.scujcc.loginandregister.data;

/**
 * @author Administrator
 */
public class ResponseData {
    private String code;
    private ContextData contextData;

    @Override
    public String toString() {
        return "ResponseData{" +
                "code='" + code + '\'' +
                ", contextData=" + contextData +
                '}';
    }

    public ContextData getContextData() {
        return contextData;
    }

    public void setContextData(ContextData contextData) {
        this.contextData = contextData;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public class ContextData {
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
