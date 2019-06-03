package top.luhailiang.mylibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import top.luhailiang.mylibrary.R;
import top.luhailiang.mylibrary.api.UserLoginApi;
import top.luhailiang.mylibrary.model.response.UserResponse;
import top.luhailiang.mylibrary.utils.CONTENST;
import top.luhailiang.mylibrary.utils.MyApplication;
import top.luhailiang.mylibrary.utils.SharedPreferencesUtil;
import top.luhailiang.mylibrary.utils.ToastUtil;
import top.luhailiang.mylibrary.utils.okhttp3.api.ApiListener;
import top.luhailiang.mylibrary.utils.okhttp3.api.ApiUtil;

public class LoginActivity extends AppCompatActivity {


    private EditText mInputUserName;
    private EditText mInputUserPassword;
    private AppCompatButton mBtnLogin;


    private UserResponse mUserResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        boolean isFirst = (boolean) SharedPreferencesUtil.getData("isFirst", true);
        if (isFirst) {
            SharedPreferencesUtil.putData("isFirst", false);
            Intent intent = new Intent(MyApplication.mContext, IntroActivity.class);
            startActivity(intent);
        }


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("MYLIBRARY-登录");
        }
        mInputUserName = findViewById(R.id.input_userName);
        mInputUserPassword = findViewById(R.id.input_userPassword);
        mBtnLogin = findViewById(R.id.btn_login);


        MyLoginListener myLoginListener = new MyLoginListener();
        mBtnLogin.setOnClickListener(myLoginListener);

    }

    private class MyLoginListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {


            String userName = mInputUserName.getText().toString();
            String userPassword = mInputUserPassword.getText().toString();

            processUserLoginApi(userName, userPassword);


        }
    }

    private void processUserLoginApi(String userName, String userPassword) {
        new UserLoginApi(userName, userPassword).post(CONTENST.USER_LOGIN, new ApiListener() {
            @Override
            public void success(ApiUtil api) {
                UserLoginApi userLoginApi = (UserLoginApi) api;
                String response = userLoginApi.mResponse;
                parseLoginResponse(response);

                new Thread() {
                    public void run() {
                        //这儿是耗时操作，完成之后更新UI；
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mUserResponse.ret) {
                                    ToastUtil.showShortToast(MyApplication.mContext, mUserResponse.msg);
                                    //Toast.makeText(MyApplication.mContext, mUserResponse.msg, Toast.LENGTH_SHORT).show();
                                    //将部分用户信息存入SharedPreferences
                                    SharedPreferencesUtil.putData("userId", mUserResponse.data.userId);
                                    SharedPreferencesUtil.putData("userName", mUserResponse.data.userName);
                                    SharedPreferencesUtil.putData("userTrueName", mUserResponse.data.userTrueName);
                                    SharedPreferencesUtil.putData("userEmail", mUserResponse.data.userEmail);
                                    SharedPreferencesUtil.putData("userPhone", mUserResponse.data.userPhone);

                                    //成功跳转至主页面
                                    Intent intent = new Intent(MyApplication.mContext, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    //失败弹出错误信息
                                    ToastUtil.showShortToast(MyApplication.mContext, mUserResponse.msg);
                                }
                            }
                        });
                    }
                }.start();

            }

            @Override
            public void failure(ApiUtil api) {

            }
        });
    }

    private void parseLoginResponse(String response) {
        try {
            Gson gson = new Gson();
            UserResponse userResponse = gson.fromJson(response, UserResponse.class);
            mUserResponse = new UserResponse(userResponse.ret, userResponse.msg, userResponse.data);
            Log.i("TAG", "userResponse: " + mUserResponse.ret);
            Log.i("TAG", "userResponse: " + mUserResponse.msg);
            Log.i("TAG", "userResponse: " + mUserResponse.data);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
