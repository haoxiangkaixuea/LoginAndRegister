package cn.edu.scujcc.loginandregister.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.edu.scujcc.loginandregister.api.LoginCallBack;
import cn.edu.scujcc.loginandregister.api.UserApi;
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
public class LoginModel {
    private static final String TAG = "LoginModel";

    //单例模式
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

    public static void loginGetData(LoginUser loginUser, LoginCallBack loginCallBack) {
        Retrofit retrofit = RetrofitClient.getInstance();
        UserApi api = retrofit.create(UserApi.class);
        String content = UserData.getLoginDate();
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
                        Log.d(TAG, "result" + result);
                        Log.d(TAG, "code" + code);
                        tokenId = jason.getString("tokenId");
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                if ("00".equals(code)) {
                    loginCallBack.onLoginSuccess(tokenId);
                } else {
                    loginCallBack.onLoginFailure(code);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loginCallBack.networkError(t);
            }
        });
    }
}





