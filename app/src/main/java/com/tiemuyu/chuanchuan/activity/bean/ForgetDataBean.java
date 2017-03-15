package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by CC2.0 on 2017/2/7.
 */

public class ForgetDataBean implements Serializable {

    @Expose
    @SerializedName("Accid")
    private String Accid;

    @Expose
    @SerializedName("AccToken")
    private String AccToken;

    @Expose
    @SerializedName("UserImg")
    private String UserImg;

    @Expose
    @SerializedName("UserName")
    private String UserName;

    @Expose
    @SerializedName("NickName")
    private String NickName;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserImg() {
        return UserImg;
    }

    public void setUserImg(String userImg) {
        UserImg = userImg;
    }

    public String getAccToken() {
        return AccToken;
    }

    public void setAccToken(String accToken) {
        AccToken = accToken;
    }

    public String getAccid() {
        return Accid;
    }

    public void setAccid(String accid) {
        Accid = accid;
    }

    @Expose
    @SerializedName("UserId")
    private int UserId;








}
