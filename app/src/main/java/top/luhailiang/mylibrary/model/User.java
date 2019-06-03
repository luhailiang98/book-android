package top.luhailiang.mylibrary.model;


import java.io.Serializable;

public class User {

    public Long userId;
    public String userName;
    public String userTrueName;
    public String userPassword;
    public String userEmail;
    public String userPhone;
    public Integer userState;  //约定 1：正常状态  0：禁用状态   默认正常
    public String userCreateTime;
    public String userLastModifyTime;
    //不对应数据库字段
    public String roles;


}