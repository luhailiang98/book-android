package top.luhailiang.mylibrary.utils.okhttp3;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtil {

    private static OkHttpClient mOkHttpClient = null;

    public static void init() {
        if (mOkHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    .writeTimeout(5000, TimeUnit.MILLISECONDS);
            mOkHttpClient = builder.build();
        }
    }

    public static void get(String url, OkHttpCallBack okHttpCallBack) {
        Call call = null;
        try {
            Request request = new Request.Builder().url(url).build();
            call = mOkHttpClient.newCall(request);
            call.enqueue(okHttpCallBack);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void post(String url, OkHttpCallBack okHttpCallBack, HashMap<String, String> bodyMap) {
        Call call = null;
        try {
            RequestBody body = createEncodingBuilderBody(bodyMap);
            Request request = new Request.Builder().post(body).url(url).build();
            call = mOkHttpClient.newCall(request);
            call.enqueue(okHttpCallBack);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static RequestBody createEncodingBuilderBody(HashMap<String, String> bodyMap) {
        FormBody.Builder builder = new FormBody.Builder();

        for (HashMap.Entry<String, String> entry : bodyMap.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }
}
