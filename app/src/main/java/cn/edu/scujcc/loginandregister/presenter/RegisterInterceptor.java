package cn.edu.scujcc.loginandregister.presenter;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Administrator
 */
public class RegisterInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request authorised = originalRequest.newBuilder()
                .addHeader("imei", "347558749E29B240957C58DAA6277D48")
                .addHeader("appversion", "1.1.1.6")
                .build();
        return chain.proceed(authorised);
    }
}
