package com.tiemuyu.chuanchuan.activity.bean;

import java.io.Serializable;

/**
 * Created by 梁文硕 on 2017/2/21.
 */

public class OrdInfo implements Serializable {

    /**
     * TotalNum : 1
     * TotalFee : 100
     * Coin : 0
     * DiscountedPrice : 0
     * ActualFee : 100
     * CustomerRmk : good
     * RegApp : 00
     */

    private String TotalNum;
    private String TotalFee;
    private String Coin;
    private String DiscountedPrice;
    private String ActualFee;
    private String CustomerRmk;
    private String RegApp;

    public String getTotalNum() {
        return TotalNum;
    }

    public void setTotalNum(String TotalNum) {
        this.TotalNum = TotalNum;
    }

    public String getTotalFee() {
        return TotalFee;
    }

    public void setTotalFee(String TotalFee) {
        this.TotalFee = TotalFee;
    }

    public String getCoin() {
        return Coin;
    }

    public void setCoin(String Coin) {
        this.Coin = Coin;
    }

    public String getDiscountedPrice() {
        return DiscountedPrice;
    }

    public void setDiscountedPrice(String DiscountedPrice) {
        this.DiscountedPrice = DiscountedPrice;
    }

    public String getActualFee() {
        return ActualFee;
    }

    public void setActualFee(String ActualFee) {
        this.ActualFee = ActualFee;
    }

    public String getCustomerRmk() {
        return CustomerRmk;
    }

    public void setCustomerRmk(String CustomerRmk) {
        this.CustomerRmk = CustomerRmk;
    }

    public String getRegApp() {
        return RegApp;
    }

    public void setRegApp(String RegApp) {
        this.RegApp = RegApp;
    }
}
