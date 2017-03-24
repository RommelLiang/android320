package com.tiemuyu.chuanchuan.activity.bean;

/**
 * Created by 梁文硕 on 2017/3/23.
 */

public class WeChatExecute {


    /**
     * Code : 1
     * Msg : OK
     * Data : {"SignStr":"{\"appid\":\"wxbe4f4acfeb2d68e6\",\"noncestr\":\"0DA924FB72654943BEBD0138070AF014\",\"package\":\"Sign=WXPay\",\"partnerid\":\"1408004302\",\"prepayid\":\"wx201703231649081cddae42e20893177784\",\"sign\":\"CEC9EEA959A14E8D8C8FD5058C15D19A\",\"timestamp\":\"1490287748\"}","ChargeId":10800}
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
         * SignStr : {"appid":"wxbe4f4acfeb2d68e6","noncestr":"0DA924FB72654943BEBD0138070AF014","package":"Sign=WXPay","partnerid":"1408004302","prepayid":"wx201703231649081cddae42e20893177784","sign":"CEC9EEA959A14E8D8C8FD5058C15D19A","timestamp":"1490287748"}
         * ChargeId : 10800
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
