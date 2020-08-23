package cn.edu.scujcc.loginandregister.presenter;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Administrator
 */
public class RetrofitClient {
    private static Retrofit INSTANCE = null;

    public static Retrofit getLoginInstance() {
        return getInstance(getLoginClient());
    }

    public static Retrofit getRegisterInstance() {
        return getInstance(getRegisterClient());
    }

    private static Retrofit getInstance(OkHttpClient client) {
        if (INSTANCE == null && null == client) {
            INSTANCE = new Retrofit.Builder()
                    .baseUrl("http://172.32.12.243:20518/")
                    .callFactory(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return INSTANCE;
    }

    private static OkHttpClient getRegisterClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new RegisterInterceptor())
                .build();
        return client;
    }

    private static OkHttpClient getLoginClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoginInterceptor())
                .build();
        return client;
    }
}
