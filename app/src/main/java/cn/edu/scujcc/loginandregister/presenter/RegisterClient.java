package cn.edu.scujcc.loginandregister.presenter;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterClient {
    private static Retrofit INSTANCE = null;

    public static Retrofit getInstance() {
        if (INSTANCE == null) {
            //准备拦截器
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new RegisterInterceptor())
                    .build();

            INSTANCE = new Retrofit.Builder()
                    .baseUrl("http://172.32.12.243:20518/")
                    .callFactory(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return INSTANCE;
    }
}
