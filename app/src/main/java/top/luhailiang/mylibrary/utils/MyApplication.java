package top.luhailiang.mylibrary.utils;

import android.app.Application;
import android.content.Context;

import top.luhailiang.mylibrary.utils.SharedPreferencesUtil;
import top.luhailiang.mylibrary.utils.okhttp3.OkHttpUtil;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        //初始化OKHTTP
        OkHttpUtil.init();

        //初始化SharedPreferencesUtil
        SharedPreferencesUtil.getInstance(this, "MYLIBRARY");
    }

    public static Context mContext;
}
