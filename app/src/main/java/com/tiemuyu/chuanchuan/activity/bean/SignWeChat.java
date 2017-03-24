package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 梁文硕 on 2017/3/23.
 */

public class SignWeChat {

    /**
     * appid : wxbe4f4acfeb2d68e6
     * noncestr : 2439967EDDBE4E82878A373F883FB1FA
     * package : Sign=WXPay
     * partnerid : 1408004302
     * prepayid : wx201703231047106938140b020082512138
     * sign : 52866C112F94F010D41BFB0398F41E73
     * timestamp : 1490266030
     */

    private String appid;
    private String noncestr;
    @SerializedName("package")
    private String packageX;
    private String partnerid;
    private String prepayid;
    private String sign;
    private String timestamp;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
