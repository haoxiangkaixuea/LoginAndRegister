package cn.edu.scujcc.loginandregister.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author Administrator
 */
public interface UserApi {
    /**
     * @param requestBody 登录请求体
     *                    静态添加请求头
     */
    @Headers({
            "imei: 347558749E29B240957C58DAA6277D48"
    })
    @POST("mcloudbank/user/login/pwdLogin")
    Call<ResponseBody> login(@Body RequestBody requestBody);

    /**
     * @param requestBody 注册请求体
     *                    静态添加请求头
     */
    @Headers({
            "imei: 347558749E29B240957C58DAA6277D48",
            "appversion: 1.1.1.6"
    })
    @POST("mcloudbank/user/verificationCode/sendVerificationCode")
    Call<ResponseBody> register(@Body RequestBody requestBody);
}

