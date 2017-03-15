package com.tiemuyu.chuanchuan.activity.bean;

/**
 * Created by 梁文硕 on 2017/3/2.
 */

public class ExecuteBean {

    /**
     * Code : 1
     * Msg : OK
     * Data : {"SignStr":"_input_charset=\"utf-8\"&body=\"充值-201703020001000609\"&notify_url=\"http://a1.myappcc.com/paynotify/alipayNew/3/10288\"&out_trade_no=\"201703020001000588\"&partner=\"2088021209537423\"&payment_type=\"1\"&seller_id=\"tmy@tiemuyu.com\"&service=\"mobile.securitypay.pay\"&subject=\"充值-201703020001000609\"&total_fee=\"55.00\"&sign=\"sz9ZR%2fYO7wxRoS%2ftOr%2bcRVBVIHYE%2f6GmwRYELhdLTrUxSVO5E5Jb%2fDFM8yqdeVSNu1ubURpGznBnLSw2AsJKlDI9KuM088aBXndavOcdc6yF9wCNcn9Dj%2fS%2fjo4ChWscg9zacKNcc6m7PbhZ5Jo4gvxnt%2bL57v37eSHTtIP7pjI%3d\"&sign_type=\"RSA\"","ChargeId":10288}
     */

    private int Code;
    private String Msg;
    private DataBean Data;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * SignStr : _input_charset="utf-8"&body="充值-201703020001000609"&notify_url="http://a1.myappcc.com/paynotify/alipayNew/3/10288"&out_trade_no="201703020001000588"&partner="2088021209537423"&payment_type="1"&seller_id="tmy@tiemuyu.com"&service="mobile.securitypay.pay"&subject="充值-201703020001000609"&total_fee="55.00"&sign="sz9ZR%2fYO7wxRoS%2ftOr%2bcRVBVIHYE%2f6GmwRYELhdLTrUxSVO5E5Jb%2fDFM8yqdeVSNu1ubURpGznBnLSw2AsJKlDI9KuM088aBXndavOcdc6yF9wCNcn9Dj%2fS%2fjo4ChWscg9zacKNcc6m7PbhZ5Jo4gvxnt%2bL57v37eSHTtIP7pjI%3d"&sign_type="RSA"
         * ChargeId : 10288
         */

        private String SignStr;
        private int ChargeId;

        public String getSignStr() {
            return SignStr;
        }

        public void setSignStr(String SignStr) {
            this.SignStr = SignStr;
        }

        public int getChargeId() {
            return ChargeId;
        }

        public void setChargeId(int ChargeId) {
            this.ChargeId = ChargeId;
        }
    }
}
