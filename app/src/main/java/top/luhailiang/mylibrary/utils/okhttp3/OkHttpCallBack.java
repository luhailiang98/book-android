package top.luhailiang.mylibrary.utils.okhttp3;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public abstract class OkHttpCallBack implements Callback {

    public abstract void onSuccess(final Call call, JSONObject response);

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response != null) {
            if (response.isSuccessful()) {
                try {
                    String str = response.body().string().trim();
                    JSONObject object = (JSONObject) new JSONTokener(str).nextValue();
                    if (object != null) {
                        onSuccess(call, object);
                    } else {
                        onFailure(call, null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                onFailure(call, null);
            }
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }


}
