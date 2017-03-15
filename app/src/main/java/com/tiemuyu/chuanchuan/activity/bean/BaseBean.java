package com.tiemuyu.chuanchuan.activity.bean;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CC2.0 on 2017/1/7.
 */

public class BaseBean extends DataPacket {


    //{"Code":1,"Msg":"OK","Data":"2c572fc3913a414b"}
    @SerializedName("Code")
    private int Code;

    @SerializedName("Msg")
    private String Msg;


    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }





}
