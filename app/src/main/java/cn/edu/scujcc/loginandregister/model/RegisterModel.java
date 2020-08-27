package cn.edu.scujcc.loginandregister.model;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import cn.edu.scujcc.loginandregister.api.RegisterCallBack;
import cn.edu.scujcc.loginandregister.api.UserApi;
import cn.edu.scujcc.loginandregister.data.ResponseData;
import cn.edu.scujcc.loginandregister.data.UserData;
import cn.edu.scujcc.loginandregister.presenter.RetrofitClient;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * @author Administrator
 */
public class RegisterModel {
    private static final String TAG = "RegisterModel";
    private static MediaType JSON = MediaType.get("application/json;charset=utf-8");
    private static int verificationCode = 0;

    public static void registerGetData(RegisterUser registerUser, RegisterCallBack registerCallBack) {
        Retrofit retrofit = RetrofitClient.getInstance();
        UserApi api = retrofit.create(UserApi.class);
        String content = UserData.getRegisterDate();

        RequestBody requestBody = RequestBody.create(JSON, content);
        Call<ResponseBody> call = api.register(requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                String result = "";
                String code = "";
                String verificationCode = "";
                if (response.body() != null) {
                    try {
                        result = response.body().string();
                        Gson gson = new Gson();
                        ResponseData responseData = gson.fromJson(result, ResponseData.class);
                        code = responseData.getCode();
                        verificationCode = responseData.getContext().getVerificationCode();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if ("00".equals(code)) {
                    String verify = verificationCode;
                    Log.d(TAG, "verify  " + verify);
                    registerCallBack.onRegisterSuccess(verify);
                } else {
                    registerCallBack.onRegisterFailure(result);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                registerCallBack.networkError(t);
            }
        });
    }
}
