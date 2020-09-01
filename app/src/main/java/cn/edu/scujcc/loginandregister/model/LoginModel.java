package cn.edu.scujcc.loginandregister.model;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.edu.scujcc.loginandregister.api.LoginCallBack;
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
public class LoginModel {
    private static final String TAG = "LoginModel";

    /**
     * 单例模式
     */
    private static volatile LoginModel INSTANCE;
    private static MediaType JSON = MediaType.get("application/json;charset=utf-8");

    public LoginModel() {

    }

    public static LoginModel getInstance() {
        if (INSTANCE == null) {
            synchronized (LoginModel.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LoginModel();
                }
            }
        }
        return INSTANCE;
    }

    public void getData(LoginCallBack loginCallBack) {
        Retrofit retrofit = RetrofitClient.getInstance();
        UserServiceApi api = retrofit.create(UserServiceApi.class);
        String content = "";
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userAccount", "15928132503");
            jsonObject.put("clientVersion", "1.1.1.6");
            jsonObject.put("password", "h5FHIuvx/7Mym38LnFjPX5dpQvypNfjLM+z+K7QRj3ZY47EoR7BbdhH39RAfBQWKxfU0TcGfvTuj8213MEuU1it63viplz9S0gNzOvtgti3kTXpWtoH03xwaMRT7e6Mj/jtmr66/6d67hmf7Ltx86g==|MTIzNDU2Nzg5MDk4NzY1NA==|GTRU949nFZn62MZaCSlKQg==");
            jsonObject.put("code", "3E8744hk6618");
            jsonObject.put("deviceId", "1116B2B9DBD20633807E7DDB450373A0");
            jsonObject.put("imei", "347558749E29B240957C58DAA6277D48");
            //json串转string类型
            content = String.valueOf(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String header = "";
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", "347558749E29B240957C58DAA6277D48");
            header = String.valueOf(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(JSON, content);
        Call<ResponseBody> call = api.login(requestBody, header);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull retrofit2.Response<ResponseBody> response) {
                String result;
                String code = "";
                String tokenId = "";
                String message = "";
                if (response.body() != null) {
                    try {
                        result = response.body().string();
                        LogUtils.d(TAG, "result" + result);
                        Gson gson = new Gson();
                        ResponseEntity responseEntity = gson.fromJson(result, ResponseEntity.class);
                        code = responseEntity.getCode();
                        tokenId = responseEntity.getContext().getTokenId();
                        message = responseEntity.getMessage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (Constants.GET_CODE.equals(code)) {
                    LogUtils.d(TAG, "tokenId" + code);
                    loginCallBack.onLoginSuccess(tokenId);
                    loginCallBack.getMessage(message);
                } else {
                    loginCallBack.onLoginFailure(message);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                loginCallBack.networkError(t);
            }
        });
    }
}





