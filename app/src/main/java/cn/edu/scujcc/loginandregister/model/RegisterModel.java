package cn.edu.scujcc.loginandregister.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.edu.scujcc.loginandregister.Constants;
import cn.edu.scujcc.loginandregister.api.RegisterCallBack;
import cn.edu.scujcc.loginandregister.api.UserApi;
import cn.edu.scujcc.loginandregister.entity.ResponseEntity;
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
    /**
     * 单例模式
     */
    private static volatile RegisterModel INSTANCE;

    public RegisterModel() {

    }

    public static RegisterModel getInstance() {
        if (INSTANCE == null) {
            synchronized (RegisterModel.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RegisterModel();
                }
            }
        }
        return INSTANCE;
    }

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
        String imei = "";
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", "347558749E29B240957C58DAA6277D48");
            //json串转string类型
            imei = String.valueOf(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String appversion = "";
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("appversion", "1.1.1.6");
            //json串转string类型
            appversion = String.valueOf(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(JSON, content);
        Call<ResponseBody> call = api.register(requestBody, imei, appversion);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull retrofit2.Response<ResponseBody> response) {
                String result = "";
                String code = "";
                String verificationCode = "";
                String message = "";
                if (response.body() != null) {
                    try {
                        result = response.body().string();
                        Log.d(TAG, "result  " + result);
                        Gson gson = new Gson();
                        ResponseEntity responseEntity = gson.fromJson(result, ResponseEntity.class);
                        code = responseEntity.getCode();
                        verificationCode = responseEntity.getContext().getVerificationCode();
                        message = responseEntity.getMessage();
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
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                registerCallBack.networkError(t);
            }
        });
    }
}
