package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CC2.0 on 2017/2/10.
 */

public class ZhuantiWaterBean  extends DataPacket {


    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductmainpic() {
        return productmainpic;
    }

    public void setProductmainpic(String productmainpic) {
        this.productmainpic = productmainpic;
    }

    @SerializedName("productid")
    private String productid;

    @SerializedName("productname")
    private String productname;

    @SerializedName("price")
    private String price;

    @SerializedName("productmainpic")
    private String productmainpic;

    @Override
    public String toString() {
        return "ZhuantiWaterBean{" +
                "productid='" + productid + '\'' +
                ", productname='" + productname + '\'' +
                ", price='" + price + '\'' +
                ", productmainpic='" + productmainpic + '\'' +
                '}';
    }
}
