package top.luhailiang.mylibrary.utils.okhttp3.api;


import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import top.luhailiang.mylibrary.utils.okhttp3.OkHttpCallBack;
import top.luhailiang.mylibrary.utils.okhttp3.OkHttpUtil;

/**
 * 封装服务端回调
 */
public abstract class ApiUtil {

    private ApiListener mApiListener = null;
    private Boolean mStatus;


    private OkHttpCallBack mOkHttpCallBack = new OkHttpCallBack() {
        @Override
        public void onSuccess(Call call, JSONObject response) {
            mStatus = response.optBoolean("ret");
            if (isSuccess()) {
                try {
                    parseData(response);
                    mApiListener.success(ApiUtil.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    parseData(response);
                    mApiListener.success(ApiUtil.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        @Override
        public void onFailure(Call call, IOException e) {
            mApiListener.failure(ApiUtil.this);
        }
    };


    public boolean isSuccess() {
        return true == mStatus;
    }

    protected abstract void parseData(JSONObject jsonObject) throws Exception;

    //protected abstract String getUrl();


    /**
     * 发送GET请求
     *
     * @param url
     * @param listener
     */
    public void get(String url, ApiListener listener) {
        mApiListener = listener;
        OkHttpUtil.get(url, mOkHttpCallBack);
    }


    private HashMap<String, String> mBodyMap = new HashMap<>();

    public void addParams(String key, String value) {
        mBodyMap.put(key, value);
    }


    /**
     * 发送POST请求
     *
     * @param url
     * @param listener
     */
    public void post(String url, ApiListener listener) {
        mApiListener = listener;
        OkHttpUtil.post(url, mOkHttpCallBack, mBodyMap);
    }


}
