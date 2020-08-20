package cn.edu.scujcc.loginandregister.listener;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.edu.scujcc.loginandregister.api.UserApi;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * 获取网路请求
 */

public class UserLab {
    public final static int MSG_NETWORK_ERROR = -2;
    public final static int MSG_LOGIN_SUCCESS = 1;
    public final static int MSG_PASSWORD_ERROR = -1;
    private static final String TAG = "UserLab";
    private static UserLab INSTANCE;
    String content = "";
    private MediaType JSON = MediaType.get("application/json;charset=utf-8");

    private UserLab() {
    }

    public static UserLab getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserLab();
        }
        return INSTANCE;
    }

    public void login(String userAccount, String password, Handler handler) {
        Retrofit retrofit = RetrofitClient.getInstance();
        UserApi api = retrofit.create(UserApi.class);

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
                boolean loginSuccess = false;
                String result = "";
                if (response.body() != null) {
                    try {
                        loginSuccess = true;
                        result = response.body().string();
                        JSONObject json = new JSONObject(content);
                        //JSONObject codes = json.getJSONObject("code");
                        String code = json.getString("code");
                        Log.d(TAG, "code" + code);

                        Log.d(TAG, "返回的数据为" + content);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (loginSuccess) {
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
                Log.d(TAG, "登录失败" + t);
                Message msg = new Message();
                msg.what = MSG_NETWORK_ERROR;
                handler.sendMessage(msg);
            }
        });
    }

    private void parseJson(String jsonData) {
        try {
            JSONArray root = new JSONArray(jsonData);
            for (int i = 0; i < root.length(); i++) {
                JSONObject jsonObject = root.getJSONObject(i);
                String userAccount = jsonObject.getString("userAccount");
                String password = jsonObject.getString("password");
                Log.d(TAG, "userAccount" + userAccount);
                Log.d(TAG, "password" + password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
