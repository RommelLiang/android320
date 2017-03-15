package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

/**
 * @项目名： 215androidpay
 * @包名： com.tiemuyu.chuanchuan.activity.bean
 * @类描述：
 * @创建人： hr
 * @创建时间： 2017/2/21
 * @version：
 */

public class FavBean {
    /**
     * Code : 1
     * Msg : OK
     * Data : [{"Id":9350,"UserId":74886,"ProductId":61542,"ProductName":"女装2017年品质蕾丝花面短款吊带+半裙","MainImage":"http://f1.myappcc.com/zfs/7E1/1050/TJK/050190621444CABHVUCITZ.jpg","Price":672,"CollectTime":"2017-02-21 21:04:17","NewPrice":672,"PkValue":9350},{"Id":8136,"UserId":74886,"ProductId":27621,"ProductName":"测试定制请勿下单","MainImage":"http://f1.myappcc.com/zfs/7E0/1262/PQF/262152722404CABGEKFUYV.jpg","Price":1,"CollectTime":"2017-01-23 21:52:19","NewPrice":1,"PkValue":8136}]
     */

    private int Code;
    private String Msg;
    private List<DataBean> Data;

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

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Id : 9350
         * UserId : 74886
         * ProductId : 61542
         * ProductName : 女装2017年品质蕾丝花面短款吊带+半裙
         * MainImage : http://f1.myappcc.com/zfs/7E1/1050/TJK/050190621444CABHVUCITZ.jpg
         * Price : 672.0
         * CollectTime : 2017-02-21 21:04:17
         * NewPrice : 672.0
         * PkValue : 9350
         */

        private int Id;
        private int UserId;
        private int ProductId;
        private String ProductName;
        private String MainImage;
        private double Price;
        private String CollectTime;
        private double NewPrice;
        private int PkValue;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public String getMainImage() {
            return MainImage;
        }

        public void setMainImage(String MainImage) {
            this.MainImage = MainImage;
        }

        public double getPrice() {
            return Price;
        }

        public void setPrice(double Price) {
            this.Price = Price;
        }

        public String getCollectTime() {
            return CollectTime;
        }

        public void setCollectTime(String CollectTime) {
            this.CollectTime = CollectTime;
        }

        public double getNewPrice() {
            return NewPrice;
        }

        public void setNewPrice(double NewPrice) {
            this.NewPrice = NewPrice;
        }

        public int getPkValue() {
            return PkValue;
        }

        public void setPkValue(int PkValue) {
            this.PkValue = PkValue;
        }
    }
}
