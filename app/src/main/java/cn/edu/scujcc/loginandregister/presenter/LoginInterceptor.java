package cn.edu.scujcc.loginandregister.presenter;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * </pre> 添加header 请求头
 *
 * @author Administrator
 */
public class LoginInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Request request = builder
                .addHeader("imei", "347558749E29B240957C58DAA6277D48")
                .build();
        return chain.proceed(request);
    }
}
