package cn.edu.scujcc.loginandregister.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * </pre>
 */
public interface UserApi {
    @POST("/mcloudbank/user/login/pwdLogin")
    Call<ResponseBody> login(@Body RequestBody requestBody);

    @POST("mcloudbank/user/verificationCode/sendVerificationCode")
    Call<ResponseBody> register(@Body RequestBody requestBody);
}
