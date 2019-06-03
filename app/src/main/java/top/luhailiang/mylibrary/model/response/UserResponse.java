package top.luhailiang.mylibrary.model.response;


import top.luhailiang.mylibrary.model.User;

public class UserResponse {


    public boolean ret;
    public String msg;
    public User data;


    public UserResponse() {
    }

    public UserResponse(boolean ret, String msg, User data) {
        this.ret = ret;
        this.msg = msg;
        this.data = data;
    }
}
