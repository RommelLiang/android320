package com.tiemuyu.chuanchuan.activity.bean;

/**
 * Created by 梁文硕 on 2017/3/1.
 */

public class AccountMannger {

    /**
     * Code : 1
     * Msg : OK
     * Data : {"UserId":94589,"CcCoin":126,"Voucher":0,"FrzCcCoin":86,"FrzVoucher":0,"AccVoucher":0,"Amounts":82,"FrzAmounts":0,"PayPwd":"96357AFC3278879AF78E3C0845D4B5061749C3110B7BF313EE4064D4BD93F9D5","PassErrCount":0,"IsLockPassword":0,"LastErrTime":"0001-01-01 00:00:00","TrueName":"","CashAccount":"","IdCard":"","Xp":20,"PkValue":94589,"NickName":"了不起的硕硕","UserImg":"http://f1.myappcc.com/zfs/7E1/1052/RIC/052170735374CABHNPYEOM.jpg","EnabledAmounts":82,"EnabledCcCoin":40,"Mobile":"17688159789"}
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
         * UserId : 94589
         * CcCoin : 126
         * Voucher : 0.0
         * FrzCcCoin : 86
         * FrzVoucher : 0.0
         * AccVoucher : 0.0
         * Amounts : 82.0
         * FrzAmounts : 0.0
         * PayPwd : 96357AFC3278879AF78E3C0845D4B5061749C3110B7BF313EE4064D4BD93F9D5
         * PassErrCount : 0
         * IsLockPassword : 0
         * LastErrTime : 0001-01-01 00:00:00
         * TrueName :
         * CashAccount :
         * IdCard :
         * Xp : 20
         * PkValue : 94589
         * NickName : 了不起的硕硕
         * UserImg : http://f1.myappcc.com/zfs/7E1/1052/RIC/052170735374CABHNPYEOM.jpg
         * EnabledAmounts : 82.0
         * EnabledCcCoin : 40
         * Mobile : 17688159789
         */

        private int UserId;
        private int CcCoin;
        private double Voucher;
        private int FrzCcCoin;
        private double FrzVoucher;
        private double AccVoucher;
        private double Amounts;
        private double FrzAmounts;
        private String PayPwd;
        private int PassErrCount;
        private int IsLockPassword;
        private String LastErrTime;
        private String TrueName;
        private String CashAccount;
        private String IdCard;
        private int Xp;
        private int PkValue;
        private String NickName;
        private String UserImg;
        private double EnabledAmounts;
        private int EnabledCcCoin;
        private String Mobile;

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getCcCoin() {
            return CcCoin;
        }

        public void setCcCoin(int CcCoin) {
            this.CcCoin = CcCoin;
        }

        public double getVoucher() {
            return Voucher;
        }

        public void setVoucher(double Voucher) {
            this.Voucher = Voucher;
        }

        public int getFrzCcCoin() {
            return FrzCcCoin;
        }

        public void setFrzCcCoin(int FrzCcCoin) {
            this.FrzCcCoin = FrzCcCoin;
        }

        public double getFrzVoucher() {
            return FrzVoucher;
        }

        public void setFrzVoucher(double FrzVoucher) {
            this.FrzVoucher = FrzVoucher;
        }

        public double getAccVoucher() {
            return AccVoucher;
        }

        public void setAccVoucher(double AccVoucher) {
            this.AccVoucher = AccVoucher;
        }

        public double getAmounts() {
            return Amounts;
        }

        public void setAmounts(double Amounts) {
            this.Amounts = Amounts;
        }

        public double getFrzAmounts() {
            return FrzAmounts;
        }

        public void setFrzAmounts(double FrzAmounts) {
            this.FrzAmounts = FrzAmounts;
        }

        public String getPayPwd() {
            return PayPwd;
        }

        public void setPayPwd(String PayPwd) {
            this.PayPwd = PayPwd;
        }

        public int getPassErrCount() {
            return PassErrCount;
        }

        public void setPassErrCount(int PassErrCount) {
            this.PassErrCount = PassErrCount;
        }

        public int getIsLockPassword() {
            return IsLockPassword;
        }

        public void setIsLockPassword(int IsLockPassword) {
            this.IsLockPassword = IsLockPassword;
        }

        public String getLastErrTime() {
            return LastErrTime;
        }

        public void setLastErrTime(String LastErrTime) {
            this.LastErrTime = LastErrTime;
        }

        public String getTrueName() {
            return TrueName;
        }

        public void setTrueName(String TrueName) {
            this.TrueName = TrueName;
        }

        public String getCashAccount() {
            return CashAccount;
        }

        public void setCashAccount(String CashAccount) {
            this.CashAccount = CashAccount;
        }

        public String getIdCard() {
            return IdCard;
        }

        public void setIdCard(String IdCard) {
            this.IdCard = IdCard;
        }

        public int getXp() {
            return Xp;
        }

        public void setXp(int Xp) {
            this.Xp = Xp;
        }

        public int getPkValue() {
            return PkValue;
        }

        public void setPkValue(int PkValue) {
            this.PkValue = PkValue;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public String getUserImg() {
            return UserImg;
        }

        public void setUserImg(String UserImg) {
            this.UserImg = UserImg;
        }

        public double getEnabledAmounts() {
            return EnabledAmounts;
        }

        public void setEnabledAmounts(double EnabledAmounts) {
            this.EnabledAmounts = EnabledAmounts;
        }

        public int getEnabledCcCoin() {
            return EnabledCcCoin;
        }

        public void setEnabledCcCoin(int EnabledCcCoin) {
            this.EnabledCcCoin = EnabledCcCoin;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }
    }
}
