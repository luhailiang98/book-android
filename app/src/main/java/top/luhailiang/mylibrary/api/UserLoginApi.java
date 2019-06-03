package top.luhailiang.mylibrary.api;

import org.json.JSONObject;

import top.luhailiang.mylibrary.utils.CONTENST;
import top.luhailiang.mylibrary.utils.okhttp3.api.ApiUtil;

public class UserLoginApi extends ApiUtil {

    public String mResponse;

    public UserLoginApi(String userName, String userPassword) {
        addParams("userName", userName);
        addParams("userPassword", userPassword);
    }

    @Override
    protected void parseData(JSONObject jsonObject) throws Exception {
        mResponse = jsonObject.toString();
    }
//
//    @Override
//    protected String getUrl() {
//        return CONTENST.USER_LOGIN;
//    }
}
