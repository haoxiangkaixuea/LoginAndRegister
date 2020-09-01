package cn.edu.scujcc.loginandregister.model;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.edu.scujcc.loginandregister.api.RegisterCallBack;
import cn.edu.scujcc.loginandregister.api.UserServiceApi;
import cn.edu.scujcc.loginandregister.constant.Constants;
import cn.edu.scujcc.loginandregister.entity.ResponseEntity;
import cn.edu.scujcc.loginandregister.http.RetrofitClient;
import cn.edu.scujcc.loginandregister.util.LogUtils;
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

    public void getData(RegisterCallBack registerCallBack) {
        Retrofit retrofit = RetrofitClient.getInstance();
        UserServiceApi api = retrofit.create(UserServiceApi.class);
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
                String result;
                String code = "";
                String verificationCode = "";
                String message = "";
                if (response.body() != null) {
                    try {
                        result = response.body().string();
                        LogUtils.d(TAG, "result" + result);
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
                    LogUtils.d(TAG, "verificationCode" + verificationCode);
                    registerCallBack.onRegisterSuccess(verificationCode);
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
