package cn.edu.scujcc.loginandregister.model;

/**
 * 请求返回
 */
public class Response {

    public final static String STATUS_SUCCESS = "SUCCESS";

    private String Message;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
