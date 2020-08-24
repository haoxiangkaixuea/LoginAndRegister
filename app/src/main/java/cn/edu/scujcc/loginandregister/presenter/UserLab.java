package cn.edu.scujcc.loginandregister.presenter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.edu.scujcc.loginandregister.api.UserApi;
import cn.edu.scujcc.loginandregister.model.LoginUser;
import cn.edu.scujcc.loginandregister.model.RegisterUser;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * 获取网路请求
 *
 * @author Administrator
 */
public class UserLab {
    public final static int MSG_NETWORK_ERROR = -2;
    public final static int MSG_LOGIN_SUCCESS = 1;
    public final static int MSG_PASSWORD_ERROR = -1;
    public static final int MSG_REGISTER_SUCCESS = 2;
    private static final String TAG = "UserLab";
    private static UserLab INSTANCE;
    private MediaType JSON = MediaType.get("application/json;charset=utf-8");

    private UserLab() {
    }

    public static UserLab getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserLab();
        }
        return INSTANCE;
    }

    public void login(LoginUser loginUser, Handler handler) {
        Retrofit retrofit = LoginClient.getInstance();
        UserApi api = retrofit.create(UserApi.class);
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
        RequestBody requestBody = RequestBody.create(JSON, content);
        Call<ResponseBody> call = api.login(requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                String result = "";
                String code = "";
                String tokenId = "";
                if (response.body() != null) {
                    try {
                        result = response.body().string();
                        JSONObject json = new JSONObject(result);
                        code = json.getString("code");
                        String context = json.getString("context");
                        JSONObject jason = new JSONObject(context);
                        tokenId = jason.getString("tokenId");
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                if ("00".equals(code)) {
                    Message msg = new Message();
                    msg.what = MSG_LOGIN_SUCCESS;
                    handler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    msg.what = MSG_PASSWORD_ERROR;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "网络错误" + t);
                Message msg = new Message();
                msg.what = MSG_NETWORK_ERROR;
                handler.sendMessage(msg);
            }
        });
    }

    public void register(RegisterUser postUser, Handler handler) {
        Retrofit retrofit = RegisterClient.getInstance();
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
                int verificationCode = 0;
                if (response.body() != null) {
                    try {
                        result = response.body().string();
                        JSONObject json = new JSONObject(result);
                        code = json.getString("code");
                        String context = json.getString("context");
                        JSONObject jason = new JSONObject(context);
                        verificationCode = jason.getInt("verificationCode");
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                if ("00".equals(code)) {
                    Message msg = new Message();
                    msg.what = MSG_REGISTER_SUCCESS;
                    msg.arg1 = verificationCode;
                    handler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    msg.what = MSG_PASSWORD_ERROR;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "网络错误" + t);
                Message msg = new Message();
                msg.what = MSG_NETWORK_ERROR;
                handler.sendMessage(msg);
            }
        });
    }
}
