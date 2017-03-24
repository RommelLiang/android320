package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/3/23.
 */

public class WeChatPay {

    /**
     * Code : 1
     * Msg : OK
     * Data : {"Order":{"OrderRefundExpress":null,"OrderExpress":null,"MainImg":null,"ProductName":null,"ProductId":null,"Price":0,"Number":0,"OrderRefundImg":null,"OrderRefundRecord":[],"OrderRefund":null,"OrderPayInfo":null,"Items":[{"Size":null,"Mark":null,"Info":null,"Id":8149,"OrderId":10773,"ProductId":68275,"ProductNo":"201703230001000032","ProductName":"男装2017年酵素洗高级梭织棉单层短款外套","ProductLevel":1,"Price":480,"Unit":"","Number":1,"MainImage":"http://f1.myappcc.com/zfs/7E1/1082/KOG/082102225693CABHUQNUTZ.jpg","ProCost":"","Fabric":"","FabricColor":"","FabricColorValue":"","MaxCoin":96,"PkValue":8149}],"OrderAddress":{"OrderId":10773,"Privonce":"北京市","City":"北京市","District":"东城区","Address":"带回家大家","Contact":"莱茵哈特","PostCode":"000000","Mobile":"13569039897","PkValue":10773},"Records":[{"Id":17364,"OrderId":10773,"UserId":97238,"SourceStatus":11,"ActionName":"下单","TargetStatus":100,"ActionTime":"2017-03-23 10:47:09","Username":"17688159789","NickName":"穿友97238","Ip":"113.91.215.87","Record":"用户[穿友97238]下单","PkValue":17364}],"Garment":null,"PayTypeName":"微信","StatusClientName":"待付款","StatusAdminName":"待付款","Id":10773,"OrderNo":"201703230001000084","OrderedUserId":97238,"OrderedUsername":"17688159789","OrderTime":"2017-03-23 10:47:09","Status":11,"OrderSubject":"男装2017年酵素洗高级梭织棉单层短款外套","OrderType":1,"OrderSummary":"[17688159789]订购了:[男装2017年酵素洗高级梭织棉单层短款外套]:x1","TotalNum":1,"TotalFee":480,"Coin":0,"ExpressFee":0,"DiscountedPrice":0,"ActualFee":480,"PayType":1,"PayTime":"0001-01-01 00:00:00","IsDelete":0,"IsRemove":0,"StatusTime":"0001-01-01 00:00:00","SendTime":"0001-01-01 00:00:00","LastCancleDate":"2017-03-30 10:47:09","LastRefundDate":"0001-01-01 00:00:00","LastSureDate":"0001-01-01 00:00:00","IsAllowRefund":0,"IsAllowReturn":0,"CustomerRmk":"good","CreatorUsername":"17688159789","CreatorDate":"2017-03-23 10:47:09","UpdateUsername":"","UpdateDate":"0001-01-01 00:00:00","Rmk":"","FollowNikename":"","FollowDate":"0001-01-01 00:00:00","FollowUserId":0,"RegApp":"00","PkValue":10773},"PayInfos":{"Id":0,"OrderId":10773,"OrderType":1,"PayType":1,"PayStatus":1,"RefundStatus":0,"TotalFee":480,"RefundFee":0,"OrderTime":"2017-03-23 10:47:09","RefundTime":"0001-01-01 00:00:00","PayTime":"0001-01-01 00:00:00","OrderNo":"201703230001000084","SellerId":null,"TradeNo":null,"BuyerId":null,"SellerEmail":null,"BuyerEmail":null,"PkValue":0},"SignStr":"{\"appid\":\"wxbe4f4acfeb2d68e6\",\"noncestr\":\"2439967EDDBE4E82878A373F883FB1FA\",\"package\":\"Sign=WXPay\",\"partnerid\":\"1408004302\",\"prepayid\":\"wx201703231047106938140b020082512138\",\"sign\":\"52866C112F94F010D41BFB0398F41E73\",\"timestamp\":\"1490266030\"}"}
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
         * Order : {"OrderRefundExpress":null,"OrderExpress":null,"MainImg":null,"ProductName":null,"ProductId":null,"Price":0,"Number":0,"OrderRefundImg":null,"OrderRefundRecord":[],"OrderRefund":null,"OrderPayInfo":null,"Items":[{"Size":null,"Mark":null,"Info":null,"Id":8149,"OrderId":10773,"ProductId":68275,"ProductNo":"201703230001000032","ProductName":"男装2017年酵素洗高级梭织棉单层短款外套","ProductLevel":1,"Price":480,"Unit":"","Number":1,"MainImage":"http://f1.myappcc.com/zfs/7E1/1082/KOG/082102225693CABHUQNUTZ.jpg","ProCost":"","Fabric":"","FabricColor":"","FabricColorValue":"","MaxCoin":96,"PkValue":8149}],"OrderAddress":{"OrderId":10773,"Privonce":"北京市","City":"北京市","District":"东城区","Address":"带回家大家","Contact":"莱茵哈特","PostCode":"000000","Mobile":"13569039897","PkValue":10773},"Records":[{"Id":17364,"OrderId":10773,"UserId":97238,"SourceStatus":11,"ActionName":"下单","TargetStatus":100,"ActionTime":"2017-03-23 10:47:09","Username":"17688159789","NickName":"穿友97238","Ip":"113.91.215.87","Record":"用户[穿友97238]下单","PkValue":17364}],"Garment":null,"PayTypeName":"微信","StatusClientName":"待付款","StatusAdminName":"待付款","Id":10773,"OrderNo":"201703230001000084","OrderedUserId":97238,"OrderedUsername":"17688159789","OrderTime":"2017-03-23 10:47:09","Status":11,"OrderSubject":"男装2017年酵素洗高级梭织棉单层短款外套","OrderType":1,"OrderSummary":"[17688159789]订购了:[男装2017年酵素洗高级梭织棉单层短款外套]:x1","TotalNum":1,"TotalFee":480,"Coin":0,"ExpressFee":0,"DiscountedPrice":0,"ActualFee":480,"PayType":1,"PayTime":"0001-01-01 00:00:00","IsDelete":0,"IsRemove":0,"StatusTime":"0001-01-01 00:00:00","SendTime":"0001-01-01 00:00:00","LastCancleDate":"2017-03-30 10:47:09","LastRefundDate":"0001-01-01 00:00:00","LastSureDate":"0001-01-01 00:00:00","IsAllowRefund":0,"IsAllowReturn":0,"CustomerRmk":"good","CreatorUsername":"17688159789","CreatorDate":"2017-03-23 10:47:09","UpdateUsername":"","UpdateDate":"0001-01-01 00:00:00","Rmk":"","FollowNikename":"","FollowDate":"0001-01-01 00:00:00","FollowUserId":0,"RegApp":"00","PkValue":10773}
         * PayInfos : {"Id":0,"OrderId":10773,"OrderType":1,"PayType":1,"PayStatus":1,"RefundStatus":0,"TotalFee":480,"RefundFee":0,"OrderTime":"2017-03-23 10:47:09","RefundTime":"0001-01-01 00:00:00","PayTime":"0001-01-01 00:00:00","OrderNo":"201703230001000084","SellerId":null,"TradeNo":null,"BuyerId":null,"SellerEmail":null,"BuyerEmail":null,"PkValue":0}
         * SignStr : {"appid":"wxbe4f4acfeb2d68e6","noncestr":"2439967EDDBE4E82878A373F883FB1FA","package":"Sign=WXPay","partnerid":"1408004302","prepayid":"wx201703231047106938140b020082512138","sign":"52866C112F94F010D41BFB0398F41E73","timestamp":"1490266030"}
         */

        private OrderBean Order;
        private PayInfosBean PayInfos;
        private String SignStr;

        public OrderBean getOrder() {
            return Order;
        }

        public void setOrder(OrderBean Order) {
            this.Order = Order;
        }

        public PayInfosBean getPayInfos() {
            return PayInfos;
        }

        public void setPayInfos(PayInfosBean PayInfos) {
            this.PayInfos = PayInfos;
        }

        public String getSignStr() {
            return SignStr;
        }

        public void setSignStr(String SignStr) {
            this.SignStr = SignStr;
        }

        public static class OrderBean {
            /**
             * OrderRefundExpress : null
             * OrderExpress : null
             * MainImg : null
             * ProductName : null
             * ProductId : null
             * Price : 0.0
             * Number : 0
             * OrderRefundImg : null
             * OrderRefundRecord : []
             * OrderRefund : null
             * OrderPayInfo : null
             * Items : [{"Size":null,"Mark":null,"Info":null,"Id":8149,"OrderId":10773,"ProductId":68275,"ProductNo":"201703230001000032","ProductName":"男装2017年酵素洗高级梭织棉单层短款外套","ProductLevel":1,"Price":480,"Unit":"","Number":1,"MainImage":"http://f1.myappcc.com/zfs/7E1/1082/KOG/082102225693CABHUQNUTZ.jpg","ProCost":"","Fabric":"","FabricColor":"","FabricColorValue":"","MaxCoin":96,"PkValue":8149}]
             * OrderAddress : {"OrderId":10773,"Privonce":"北京市","City":"北京市","District":"东城区","Address":"带回家大家","Contact":"莱茵哈特","PostCode":"000000","Mobile":"13569039897","PkValue":10773}
             * Records : [{"Id":17364,"OrderId":10773,"UserId":97238,"SourceStatus":11,"ActionName":"下单","TargetStatus":100,"ActionTime":"2017-03-23 10:47:09","Username":"17688159789","NickName":"穿友97238","Ip":"113.91.215.87","Record":"用户[穿友97238]下单","PkValue":17364}]
             * Garment : null
             * PayTypeName : 微信
             * StatusClientName : 待付款
             * StatusAdminName : 待付款
             * Id : 10773
             * OrderNo : 201703230001000084
             * OrderedUserId : 97238
             * OrderedUsername : 17688159789
             * OrderTime : 2017-03-23 10:47:09
             * Status : 11
             * OrderSubject : 男装2017年酵素洗高级梭织棉单层短款外套
             * OrderType : 1
             * OrderSummary : [17688159789]订购了:[男装2017年酵素洗高级梭织棉单层短款外套]:x1
             * TotalNum : 1
             * TotalFee : 480.0
             * Coin : 0
             * ExpressFee : 0.0
             * DiscountedPrice : 0.0
             * ActualFee : 480.0
             * PayType : 1
             * PayTime : 0001-01-01 00:00:00
             * IsDelete : 0
             * IsRemove : 0
             * StatusTime : 0001-01-01 00:00:00
             * SendTime : 0001-01-01 00:00:00
             * LastCancleDate : 2017-03-30 10:47:09
             * LastRefundDate : 0001-01-01 00:00:00
             * LastSureDate : 0001-01-01 00:00:00
             * IsAllowRefund : 0
             * IsAllowReturn : 0
             * CustomerRmk : good
             * CreatorUsername : 17688159789
             * CreatorDate : 2017-03-23 10:47:09
             * UpdateUsername :
             * UpdateDate : 0001-01-01 00:00:00
             * Rmk :
             * FollowNikename :
             * FollowDate : 0001-01-01 00:00:00
             * FollowUserId : 0
             * RegApp : 00
             * PkValue : 10773
             */

            private Object OrderRefundExpress;
            private Object OrderExpress;
            private Object MainImg;
            private Object ProductName;
            private Object ProductId;
            private double Price;
            private int Number;
            private Object OrderRefundImg;
            private Object OrderRefund;
            private Object OrderPayInfo;
            private OrderAddressBean OrderAddress;
            private Object Garment;
            private String PayTypeName;
            private String StatusClientName;
            private String StatusAdminName;
            private int Id;
            private String OrderNo;
            private int OrderedUserId;
            private String OrderedUsername;
            private String OrderTime;
            private int Status;
            private String OrderSubject;
            private int OrderType;
            private String OrderSummary;
            private int TotalNum;
            private double TotalFee;
            private int Coin;
            private double ExpressFee;
            private double DiscountedPrice;
            private double ActualFee;
            private int PayType;
            private String PayTime;
            private int IsDelete;
            private int IsRemove;
            private String StatusTime;
            private String SendTime;
            private String LastCancleDate;
            private String LastRefundDate;
            private String LastSureDate;
            private int IsAllowRefund;
            private int IsAllowReturn;
            private String CustomerRmk;
            private String CreatorUsername;
            private String CreatorDate;
            private String UpdateUsername;
            private String UpdateDate;
            private String Rmk;
            private String FollowNikename;
            private String FollowDate;
            private int FollowUserId;
            private String RegApp;
            private int PkValue;
            private List<?> OrderRefundRecord;
            private List<ItemsBean> Items;
            private List<RecordsBean> Records;

            public Object getOrderRefundExpress() {
                return OrderRefundExpress;
            }

            public void setOrderRefundExpress(Object OrderRefundExpress) {
                this.OrderRefundExpress = OrderRefundExpress;
            }

            public Object getOrderExpress() {
                return OrderExpress;
            }

            public void setOrderExpress(Object OrderExpress) {
                this.OrderExpress = OrderExpress;
            }

            public Object getMainImg() {
                return MainImg;
            }

            public void setMainImg(Object MainImg) {
                this.MainImg = MainImg;
            }

            public Object getProductName() {
                return ProductName;
            }

            public void setProductName(Object ProductName) {
                this.ProductName = ProductName;
            }

            public Object getProductId() {
                return ProductId;
            }

            public void setProductId(Object ProductId) {
                this.ProductId = ProductId;
            }

            public double getPrice() {
                return Price;
            }

            public void setPrice(double Price) {
                this.Price = Price;
            }

            public int getNumber() {
                return Number;
            }

            public void setNumber(int Number) {
                this.Number = Number;
            }

            public Object getOrderRefundImg() {
                return OrderRefundImg;
            }

            public void setOrderRefundImg(Object OrderRefundImg) {
                this.OrderRefundImg = OrderRefundImg;
            }

            public Object getOrderRefund() {
                return OrderRefund;
            }

            public void setOrderRefund(Object OrderRefund) {
                this.OrderRefund = OrderRefund;
            }

            public Object getOrderPayInfo() {
                return OrderPayInfo;
            }

            public void setOrderPayInfo(Object OrderPayInfo) {
                this.OrderPayInfo = OrderPayInfo;
            }

            public OrderAddressBean getOrderAddress() {
                return OrderAddress;
            }

            public void setOrderAddress(OrderAddressBean OrderAddress) {
                this.OrderAddress = OrderAddress;
            }

            public Object getGarment() {
                return Garment;
            }

            public void setGarment(Object Garment) {
                this.Garment = Garment;
            }

            public String getPayTypeName() {
                return PayTypeName;
            }

            public void setPayTypeName(String PayTypeName) {
                this.PayTypeName = PayTypeName;
            }

            public String getStatusClientName() {
                return StatusClientName;
            }

            public void setStatusClientName(String StatusClientName) {
                this.StatusClientName = StatusClientName;
            }

            public String getStatusAdminName() {
                return StatusAdminName;
            }

            public void setStatusAdminName(String StatusAdminName) {
                this.StatusAdminName = StatusAdminName;
            }

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getOrderNo() {
                return OrderNo;
            }

            public void setOrderNo(String OrderNo) {
                this.OrderNo = OrderNo;
            }

            public int getOrderedUserId() {
                return OrderedUserId;
            }

            public void setOrderedUserId(int OrderedUserId) {
                this.OrderedUserId = OrderedUserId;
            }

            public String getOrderedUsername() {
                return OrderedUsername;
            }

            public void setOrderedUsername(String OrderedUsername) {
                this.OrderedUsername = OrderedUsername;
            }

            public String getOrderTime() {
                return OrderTime;
            }

            public void setOrderTime(String OrderTime) {
                this.OrderTime = OrderTime;
            }

            public int getStatus() {
                return Status;
            }

            public void setStatus(int Status) {
                this.Status = Status;
            }

            public String getOrderSubject() {
                return OrderSubject;
            }

            public void setOrderSubject(String OrderSubject) {
                this.OrderSubject = OrderSubject;
            }

            public int getOrderType() {
                return OrderType;
            }

            public void setOrderType(int OrderType) {
                this.OrderType = OrderType;
            }

            public String getOrderSummary() {
                return OrderSummary;
            }

            public void setOrderSummary(String OrderSummary) {
                this.OrderSummary = OrderSummary;
            }

            public int getTotalNum() {
                return TotalNum;
            }

            public void setTotalNum(int TotalNum) {
                this.TotalNum = TotalNum;
            }

            public double getTotalFee() {
                return TotalFee;
            }

            public void setTotalFee(double TotalFee) {
                this.TotalFee = TotalFee;
            }

            public int getCoin() {
                return Coin;
            }

            public void setCoin(int Coin) {
                this.Coin = Coin;
            }

            public double getExpressFee() {
                return ExpressFee;
            }

            public void setExpressFee(double ExpressFee) {
                this.ExpressFee = ExpressFee;
            }

            public double getDiscountedPrice() {
                return DiscountedPrice;
            }

            public void setDiscountedPrice(double DiscountedPrice) {
                this.DiscountedPrice = DiscountedPrice;
            }

            public double getActualFee() {
                return ActualFee;
            }

            public void setActualFee(double ActualFee) {
                this.ActualFee = ActualFee;
            }

            public int getPayType() {
                return PayType;
            }

            public void setPayType(int PayType) {
                this.PayType = PayType;
            }

            public String getPayTime() {
                return PayTime;
            }

            public void setPayTime(String PayTime) {
                this.PayTime = PayTime;
            }

            public int getIsDelete() {
                return IsDelete;
            }

            public void setIsDelete(int IsDelete) {
                this.IsDelete = IsDelete;
            }

            public int getIsRemove() {
                return IsRemove;
            }

            public void setIsRemove(int IsRemove) {
                this.IsRemove = IsRemove;
            }

            public String getStatusTime() {
                return StatusTime;
            }

            public void setStatusTime(String StatusTime) {
                this.StatusTime = StatusTime;
            }

            public String getSendTime() {
                return SendTime;
            }

            public void setSendTime(String SendTime) {
                this.SendTime = SendTime;
            }

            public String getLastCancleDate() {
                return LastCancleDate;
            }

            public void setLastCancleDate(String LastCancleDate) {
                this.LastCancleDate = LastCancleDate;
            }

            public String getLastRefundDate() {
                return LastRefundDate;
            }

            public void setLastRefundDate(String LastRefundDate) {
                this.LastRefundDate = LastRefundDate;
            }

            public String getLastSureDate() {
                return LastSureDate;
            }

            public void setLastSureDate(String LastSureDate) {
                this.LastSureDate = LastSureDate;
            }

            public int getIsAllowRefund() {
                return IsAllowRefund;
            }

            public void setIsAllowRefund(int IsAllowRefund) {
                this.IsAllowRefund = IsAllowRefund;
            }

            public int getIsAllowReturn() {
                return IsAllowReturn;
            }

            public void setIsAllowReturn(int IsAllowReturn) {
                this.IsAllowReturn = IsAllowReturn;
            }

            public String getCustomerRmk() {
                return CustomerRmk;
            }

            public void setCustomerRmk(String CustomerRmk) {
                this.CustomerRmk = CustomerRmk;
            }

            public String getCreatorUsername() {
                return CreatorUsername;
            }

            public void setCreatorUsername(String CreatorUsername) {
                this.CreatorUsername = CreatorUsername;
            }

            public String getCreatorDate() {
                return CreatorDate;
            }

            public void setCreatorDate(String CreatorDate) {
                this.CreatorDate = CreatorDate;
            }

            public String getUpdateUsername() {
                return UpdateUsername;
            }

            public void setUpdateUsername(String UpdateUsername) {
                this.UpdateUsername = UpdateUsername;
            }

            public String getUpdateDate() {
                return UpdateDate;
            }

            public void setUpdateDate(String UpdateDate) {
                this.UpdateDate = UpdateDate;
            }

            public String getRmk() {
                return Rmk;
            }

            public void setRmk(String Rmk) {
                this.Rmk = Rmk;
            }

            public String getFollowNikename() {
                return FollowNikename;
            }

            public void setFollowNikename(String FollowNikename) {
                this.FollowNikename = FollowNikename;
            }

            public String getFollowDate() {
                return FollowDate;
            }

            public void setFollowDate(String FollowDate) {
                this.FollowDate = FollowDate;
            }

            public int getFollowUserId() {
                return FollowUserId;
            }

            public void setFollowUserId(int FollowUserId) {
                this.FollowUserId = FollowUserId;
            }

            public String getRegApp() {
                return RegApp;
            }

            public void setRegApp(String RegApp) {
                this.RegApp = RegApp;
            }

            public int getPkValue() {
                return PkValue;
            }

            public void setPkValue(int PkValue) {
                this.PkValue = PkValue;
            }

            public List<?> getOrderRefundRecord() {
                return OrderRefundRecord;
            }

            public void setOrderRefundRecord(List<?> OrderRefundRecord) {
                this.OrderRefundRecord = OrderRefundRecord;
            }

            public List<ItemsBean> getItems() {
                return Items;
            }

            public void setItems(List<ItemsBean> Items) {
                this.Items = Items;
            }

            public List<RecordsBean> getRecords() {
                return Records;
            }

            public void setRecords(List<RecordsBean> Records) {
                this.Records = Records;
            }

            public static class OrderAddressBean {
                /**
                 * OrderId : 10773
                 * Privonce : 北京市
                 * City : 北京市
                 * District : 东城区
                 * Address : 带回家大家
                 * Contact : 莱茵哈特
                 * PostCode : 000000
                 * Mobile : 13569039897
                 * PkValue : 10773
                 */

                private int OrderId;
                private String Privonce;
                private String City;
                private String District;
                private String Address;
                private String Contact;
                private String PostCode;
                private String Mobile;
                private int PkValue;

                public int getOrderId() {
                    return OrderId;
                }

                public void setOrderId(int OrderId) {
                    this.OrderId = OrderId;
                }

                public String getPrivonce() {
                    return Privonce;
                }

                public void setPrivonce(String Privonce) {
                    this.Privonce = Privonce;
                }

                public String getCity() {
                    return City;
                }

                public void setCity(String City) {
                    this.City = City;
                }

                public String getDistrict() {
                    return District;
                }

                public void setDistrict(String District) {
                    this.District = District;
                }

                public String getAddress() {
                    return Address;
                }

                public void setAddress(String Address) {
                    this.Address = Address;
                }

                public String getContact() {
                    return Contact;
                }

                public void setContact(String Contact) {
                    this.Contact = Contact;
                }

                public String getPostCode() {
                    return PostCode;
                }

                public void setPostCode(String PostCode) {
                    this.PostCode = PostCode;
                }

                public String getMobile() {
                    return Mobile;
                }

                public void setMobile(String Mobile) {
                    this.Mobile = Mobile;
                }

                public int getPkValue() {
                    return PkValue;
                }

                public void setPkValue(int PkValue) {
                    this.PkValue = PkValue;
                }
            }

            public static class ItemsBean {
                /**
                 * Size : null
                 * Mark : null
                 * Info : null
                 * Id : 8149
                 * OrderId : 10773
                 * ProductId : 68275
                 * ProductNo : 201703230001000032
                 * ProductName : 男装2017年酵素洗高级梭织棉单层短款外套
                 * ProductLevel : 1
                 * Price : 480.0
                 * Unit :
                 * Number : 1
                 * MainImage : http://f1.myappcc.com/zfs/7E1/1082/KOG/082102225693CABHUQNUTZ.jpg
                 * ProCost :
                 * Fabric :
                 * FabricColor :
                 * FabricColorValue :
                 * MaxCoin : 96
                 * PkValue : 8149
                 */

                private Object Size;
                private Object Mark;
                private Object Info;
                private int Id;
                private int OrderId;
                private int ProductId;
                private String ProductNo;
                private String ProductName;
                private int ProductLevel;
                private double Price;
                private String Unit;
                private int Number;
                private String MainImage;
                private String ProCost;
                private String Fabric;
                private String FabricColor;
                private String FabricColorValue;
                private int MaxCoin;
                private int PkValue;

                public Object getSize() {
                    return Size;
                }

                public void setSize(Object Size) {
                    this.Size = Size;
                }

                public Object getMark() {
                    return Mark;
                }

                public void setMark(Object Mark) {
                    this.Mark = Mark;
                }

                public Object getInfo() {
                    return Info;
                }

                public void setInfo(Object Info) {
                    this.Info = Info;
                }

                public int getId() {
                    return Id;
                }

                public void setId(int Id) {
                    this.Id = Id;
                }

                public int getOrderId() {
                    return OrderId;
                }

                public void setOrderId(int OrderId) {
                    this.OrderId = OrderId;
                }

                public int getProductId() {
                    return ProductId;
                }

                public void setProductId(int ProductId) {
                    this.ProductId = ProductId;
                }

                public String getProductNo() {
                    return ProductNo;
                }

                public void setProductNo(String ProductNo) {
                    this.ProductNo = ProductNo;
                }

                public String getProductName() {
                    return ProductName;
                }

                public void setProductName(String ProductName) {
                    this.ProductName = ProductName;
                }

                public int getProductLevel() {
                    return ProductLevel;
                }

                public void setProductLevel(int ProductLevel) {
                    this.ProductLevel = ProductLevel;
                }

                public double getPrice() {
                    return Price;
                }

                public void setPrice(double Price) {
                    this.Price = Price;
                }

                public String getUnit() {
                    return Unit;
                }

                public void setUnit(String Unit) {
                    this.Unit = Unit;
                }

                public int getNumber() {
                    return Number;
                }

                public void setNumber(int Number) {
                    this.Number = Number;
                }

                public String getMainImage() {
                    return MainImage;
                }

                public void setMainImage(String MainImage) {
                    this.MainImage = MainImage;
                }

                public String getProCost() {
                    return ProCost;
                }

                public void setProCost(String ProCost) {
                    this.ProCost = ProCost;
                }

                public String getFabric() {
                    return Fabric;
                }

                public void setFabric(String Fabric) {
                    this.Fabric = Fabric;
                }

                public String getFabricColor() {
                    return FabricColor;
                }

                public void setFabricColor(String FabricColor) {
                    this.FabricColor = FabricColor;
                }

                public String getFabricColorValue() {
                    return FabricColorValue;
                }

                public void setFabricColorValue(String FabricColorValue) {
                    this.FabricColorValue = FabricColorValue;
                }

                public int getMaxCoin() {
                    return MaxCoin;
                }

                public void setMaxCoin(int MaxCoin) {
                    this.MaxCoin = MaxCoin;
                }

                public int getPkValue() {
                    return PkValue;
                }

                public void setPkValue(int PkValue) {
                    this.PkValue = PkValue;
                }
            }

            public static class RecordsBean {
                /**
                 * Id : 17364
                 * OrderId : 10773
                 * UserId : 97238
                 * SourceStatus : 11
                 * ActionName : 下单
                 * TargetStatus : 100
                 * ActionTime : 2017-03-23 10:47:09
                 * Username : 17688159789
                 * NickName : 穿友97238
                 * Ip : 113.91.215.87
                 * Record : 用户[穿友97238]下单
                 * PkValue : 17364
                 */

                private int Id;
                private int OrderId;
                private int UserId;
                private int SourceStatus;
                private String ActionName;
                private int TargetStatus;
                private String ActionTime;
                private String Username;
                private String NickName;
                private String Ip;
                private String Record;
                private int PkValue;

                public int getId() {
                    return Id;
                }

                public void setId(int Id) {
                    this.Id = Id;
                }

                public int getOrderId() {
                    return OrderId;
                }

                public void setOrderId(int OrderId) {
                    this.OrderId = OrderId;
                }

                public int getUserId() {
                    return UserId;
                }

                public void setUserId(int UserId) {
                    this.UserId = UserId;
                }

                public int getSourceStatus() {
                    return SourceStatus;
                }

                public void setSourceStatus(int SourceStatus) {
                    this.SourceStatus = SourceStatus;
                }

                public String getActionName() {
                    return ActionName;
                }

                public void setActionName(String ActionName) {
                    this.ActionName = ActionName;
                }

                public int getTargetStatus() {
                    return TargetStatus;
                }

                public void setTargetStatus(int TargetStatus) {
                    this.TargetStatus = TargetStatus;
                }

                public String getActionTime() {
                    return ActionTime;
                }

                public void setActionTime(String ActionTime) {
                    this.ActionTime = ActionTime;
                }

                public String getUsername() {
                    return Username;
                }

                public void setUsername(String Username) {
                    this.Username = Username;
                }

                public String getNickName() {
                    return NickName;
                }

                public void setNickName(String NickName) {
                    this.NickName = NickName;
                }

                public String getIp() {
                    return Ip;
                }

                public void setIp(String Ip) {
                    this.Ip = Ip;
                }

                public String getRecord() {
                    return Record;
                }

                public void setRecord(String Record) {
                    this.Record = Record;
                }

                public int getPkValue() {
                    return PkValue;
                }

                public void setPkValue(int PkValue) {
                    this.PkValue = PkValue;
                }
            }
        }

        public static class PayInfosBean {
            /**
             * Id : 0
             * OrderId : 10773
             * OrderType : 1
             * PayType : 1
             * PayStatus : 1
             * RefundStatus : 0
             * TotalFee : 480.0
             * RefundFee : 0.0
             * OrderTime : 2017-03-23 10:47:09
             * RefundTime : 0001-01-01 00:00:00
             * PayTime : 0001-01-01 00:00:00
             * OrderNo : 201703230001000084
             * SellerId : null
             * TradeNo : null
             * BuyerId : null
             * SellerEmail : null
             * BuyerEmail : null
             * PkValue : 0
             */

            private int Id;
            private int OrderId;
            private int OrderType;
            private int PayType;
            private int PayStatus;
            private int RefundStatus;
            private double TotalFee;
            private double RefundFee;
            private String OrderTime;
            private String RefundTime;
            private String PayTime;
            private String OrderNo;
            private Object SellerId;
            private Object TradeNo;
            private Object BuyerId;
            private Object SellerEmail;
            private Object BuyerEmail;
            private int PkValue;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public int getOrderId() {
                return OrderId;
            }

            public void setOrderId(int OrderId) {
                this.OrderId = OrderId;
            }

            public int getOrderType() {
                return OrderType;
            }

            public void setOrderType(int OrderType) {
                this.OrderType = OrderType;
            }

            public int getPayType() {
                return PayType;
            }

            public void setPayType(int PayType) {
                this.PayType = PayType;
            }

            public int getPayStatus() {
                return PayStatus;
            }

            public void setPayStatus(int PayStatus) {
                this.PayStatus = PayStatus;
            }

            public int getRefundStatus() {
                return RefundStatus;
            }

            public void setRefundStatus(int RefundStatus) {
                this.RefundStatus = RefundStatus;
            }

            public double getTotalFee() {
                return TotalFee;
            }

            public void setTotalFee(double TotalFee) {
                this.TotalFee = TotalFee;
            }

            public double getRefundFee() {
                return RefundFee;
            }

            public void setRefundFee(double RefundFee) {
                this.RefundFee = RefundFee;
            }

            public String getOrderTime() {
                return OrderTime;
            }

            public void setOrderTime(String OrderTime) {
                this.OrderTime = OrderTime;
            }

            public String getRefundTime() {
                return RefundTime;
            }

            public void setRefundTime(String RefundTime) {
                this.RefundTime = RefundTime;
            }

            public String getPayTime() {
                return PayTime;
            }

            public void setPayTime(String PayTime) {
                this.PayTime = PayTime;
            }

            public String getOrderNo() {
                return OrderNo;
            }

            public void setOrderNo(String OrderNo) {
                this.OrderNo = OrderNo;
            }

            public Object getSellerId() {
                return SellerId;
            }

            public void setSellerId(Object SellerId) {
                this.SellerId = SellerId;
            }

            public Object getTradeNo() {
                return TradeNo;
            }

            public void setTradeNo(Object TradeNo) {
                this.TradeNo = TradeNo;
            }

            public Object getBuyerId() {
                return BuyerId;
            }

            public void setBuyerId(Object BuyerId) {
                this.BuyerId = BuyerId;
            }

            public Object getSellerEmail() {
                return SellerEmail;
            }

            public void setSellerEmail(Object SellerEmail) {
                this.SellerEmail = SellerEmail;
            }

            public Object getBuyerEmail() {
                return BuyerEmail;
            }

            public void setBuyerEmail(Object BuyerEmail) {
                this.BuyerEmail = BuyerEmail;
            }

            public int getPkValue() {
                return PkValue;
            }

            public void setPkValue(int PkValue) {
                this.PkValue = PkValue;
            }
        }
    }
}
