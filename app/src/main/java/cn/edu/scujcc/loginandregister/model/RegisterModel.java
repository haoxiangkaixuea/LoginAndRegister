package cn.edu.scujcc.loginandregister.model;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.edu.scujcc.loginandregister.Constants;
import cn.edu.scujcc.loginandregister.api.RegisterCallBack;
import cn.edu.scujcc.loginandregister.api.UserApi;
import cn.edu.scujcc.loginandregister.data.ResponseEntente;
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

    public void getData(RegisterUser registerUser, RegisterCallBack registerCallBack) {
        Retrofit retrofit = RetrofitClient.getInstance();
        UserApi api = retrofit.create(UserApi.class);
        String content = "";
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userAccount", "15928132508");
            jsonObject.put("type", "register");
            jsonObject.put("clientVersion", "1.1.1.6");
            jsonObject.put("imei", "347558749E29B240957C58DAA6277D48");
            //json串转string类型
            content = String.valueOf(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(JSON, content);
        Call<ResponseBody> call = api.register(requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                String result = "";
                String code = "";
                String verificationCode = "";
                String message = "";
                if (response.body() != null) {
                    try {
                        result = response.body().string();
                        Log.d(TAG, "result  " + result);
                        Gson gson = new Gson();
                        ResponseEntente responseEntente = gson.fromJson(result, ResponseEntente.class);
                        code = responseEntente.getCode();
                        verificationCode = responseEntente.getContext().getVerificationCode();
                        message = responseEntente.getMessage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (Constants.GET_CODE.equals(code)) {
                    String verify = verificationCode;
                    Log.d(TAG, "verify  " + verify);
                    registerCallBack.onRegisterSuccess(verify);
                    registerCallBack.getMessage(message);
                } else {
                    registerCallBack.onRegisterFailure(message);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                registerCallBack.networkError(t);
            }
        });
    }
}
