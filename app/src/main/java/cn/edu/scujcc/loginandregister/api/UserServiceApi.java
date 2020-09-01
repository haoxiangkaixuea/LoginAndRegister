package cn.edu.scujcc.loginandregister.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * @author Administrator
 */
public interface UserServiceApi {

    /**
     * 登录的网络请求
     *
     * @param requestBody 登录网络请求体
     *                    动态态添加请求头
     * @param imei        请求imei
     * @return ResponseBody   ResponseBody
     */
    @POST("mcloudbank/user/login/pwdLogin")
    Call<ResponseBody> login(@Body RequestBody requestBody, @Header("imei") String imei);

    /**
     * 注册的网络请求
     *
     * @param requestBody 注册网络请求体
     *                    静动态添加请求头
     * @param imei        请求头imei
     * @param appversion  请求头appversion
     * @return ResponseBody   ResponseBody
     */
    @POST("mcloudbank/user/verificationCode/sendVerificationCode")
    Call<ResponseBody> register(@Body RequestBody requestBody,
                                @Header("imei") String imei,
                                @Header("appversion") String appversion);
}

