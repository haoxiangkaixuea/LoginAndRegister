package cn.edu.scujcc.loginandregister.presenter;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.edu.scujcc.loginandregister.api.LoginCallBack;
import cn.edu.scujcc.loginandregister.api.RegisterCallBack;
import cn.edu.scujcc.loginandregister.api.UserApi;
import cn.edu.scujcc.loginandregister.model.UserModel;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * @author Administrator
 */
public class UserPresenter {
    private static final String TAG = "UserModel";
    private static int verificationCode = 0;
    public static final int MSG_VERIFY_SUCCESS = verificationCode;
    //单例模式
    private static volatile UserPresenter INSTANCE;
    private static MediaType JSON = MediaType.get("application/json;charset=utf-8");

    public UserPresenter() {

    }

    public static UserPresenter getInstance() {
        if (INSTANCE == null) {
            synchronized (UserPresenter.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserPresenter();
                }
            }
        }
        return INSTANCE;
    }

    public static void login(LoginCallBack loginCallBack) {
        Retrofit retrofit = RetrofitClient.getInstance();
        UserApi api = retrofit.create(UserApi.class);
        String content = UserModel.getLoginDate();
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

    public static void register(RegisterCallBack registerCallBack) {
        Retrofit retrofit = RetrofitClient.getInstance();
        UserApi api = retrofit.create(UserApi.class);
        String content = UserModel.getRegisterDate();

        RequestBody requestBody = RequestBody.create(JSON, content);
        Call<ResponseBody> call = api.register(requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                String result = "";
                String code = "";
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
                    String verify = String.valueOf(verificationCode);
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





