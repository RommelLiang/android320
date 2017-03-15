package com.tiemuyu.chuanchuan.activity.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 梁文硕 on 2017/2/27.
 */

public class OrderBean implements Serializable{

    /**
     * Code : 1
     * Msg : OK
     * Data : {"CurrentPage":1,"PageSize":2,"Total":16,"Rows":[{"OrderRefundExpress":null,"OrderExpress":null,"MainImg":null,"ProductName":null,"ProductId":null,"Price":0,"Number":0,"OrderRefundImg":null,"OrderRefundRecord":null,"OrderRefund":null,"OrderPayInfo":null,"Items":[{"Size":null,"Mark":null,"Info":null,"Id":7530,"OrderId":10069,"ProductId":61694,"ProductNo":"201702200001000088","ProductName":"女装2017年轻工钉珠品质蕾丝花面双层长袖长款连衣裙","ProductLevel":1,"Price":696,"Unit":"","Number":1,"MainImage":"http://f1.myappcc.com/zfs/7E1/1051/MUH/051124458723CABHAEJDLL.jpg","ProCost":"","Fabric":"","FabricColor":"","FabricColorValue":"","MaxCoin":139,"PkValue":7530}],"OrderAddress":{"OrderId":10069,"Privonce":"北京市","City":"市辖区","District":"东城区","Address":"长安街中南海","Contact":"海因里希","PostCode":null,"Mobile":"13569039897","PkValue":10069},"Records":null,"Garment":null,"PayTypeName":"线下支付","StatusClientName":"待付款","StatusAdminName":"待付款","Id":10069,"OrderNo":"201702220001000441","OrderedUserId":94589,"OrderedUsername":"17688159789","OrderTime":"2017-02-22 17:15:42","Status":11,"OrderSubject":"女装2017年轻工钉珠品质蕾丝花面双层长袖长款连衣裙","OrderType":1,"OrderSummary":"[17688159789]订购了:[女装2017年轻工钉珠品质蕾丝花面双层长袖长款连衣裙]:x1","TotalNum":1,"TotalFee":696,"Coin":28,"ExpressFee":0,"DiscountedPrice":0,"ActualFee":668,"PayType":0,"PayTime":"0001-01-01 00:00:00","IsDelete":0,"IsRemove":0,"StatusTime":"0001-01-01 00:00:00","SendTime":"0001-01-01 00:00:00","LastCancleDate":"2017-03-01 17:15:42","LastRefundDate":"0001-01-01 00:00:00","LastSureDate":"0001-01-01 00:00:00","IsAllowRefund":0,"IsAllowReturn":0,"CustomerRmk":"good","CreatorUsername":"17688159789","CreatorDate":"2017-02-22 17:15:42","UpdateUsername":"","UpdateDate":"0001-01-01 00:00:00","Rmk":"","FollowNikename":"","FollowDate":"0001-01-01 00:00:00","FollowUserId":0,"RegApp":"00","PkValue":10069},{"OrderRefundExpress":null,"OrderExpress":null,"MainImg":null,"ProductName":null,"ProductId":null,"Price":0,"Number":0,"OrderRefundImg":null,"OrderRefundRecord":null,"OrderRefund":null,"OrderPayInfo":null,"Items":[{"Size":null,"Mark":null,"Info":null,"Id":7529,"OrderId":10068,"ProductId":61694,"ProductNo":"201702200001000088","ProductName":"女装2017年轻工钉珠品质蕾丝花面双层长袖长款连衣裙","ProductLevel":1,"Price":696,"Unit":"","Number":1,"MainImage":"http://f1.myappcc.com/zfs/7E1/1051/MUH/051124458723CABHAEJDLL.jpg","ProCost":"","Fabric":"","FabricColor":"","FabricColorValue":"","MaxCoin":139,"PkValue":7529}],"OrderAddress":{"OrderId":10068,"Privonce":"北京市","City":"市辖区","District":"东城区","Address":"长安街中南海","Contact":"海因里希","PostCode":null,"Mobile":"13569039897","PkValue":10068},"Records":null,"Garment":null,"PayTypeName":"线下支付","StatusClientName":"待付款","StatusAdminName":"待付款","Id":10068,"OrderNo":"201702220001000420","OrderedUserId":94589,"OrderedUsername":"17688159789","OrderTime":"2017-02-22 17:10:18","Status":11,"OrderSubject":"女装2017年轻工钉珠品质蕾丝花面双层长袖长款连衣裙","OrderType":1,"OrderSummary":"[17688159789]订购了:[女装2017年轻工钉珠品质蕾丝花面双层长袖长款连衣裙]:x1","TotalNum":1,"TotalFee":696,"Coin":28,"ExpressFee":0,"DiscountedPrice":0,"ActualFee":668,"PayType":0,"PayTime":"0001-01-01 00:00:00","IsDelete":0,"IsRemove":0,"StatusTime":"0001-01-01 00:00:00","SendTime":"0001-01-01 00:00:00","LastCancleDate":"2017-03-01 17:10:18","LastRefundDate":"0001-01-01 00:00:00","LastSureDate":"0001-01-01 00:00:00","IsAllowRefund":0,"IsAllowReturn":0,"CustomerRmk":"good","CreatorUsername":"17688159789","CreatorDate":"2017-02-22 17:10:18","UpdateUsername":"","UpdateDate":"0001-01-01 00:00:00","Rmk":"","FollowNikename":"","FollowDate":"0001-01-01 00:00:00","FollowUserId":0,"RegApp":"00","PkValue":10068}],"PageCount":8,"PageRecordStartNum":1,"PageRocordEndNum":2}
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

    public static class DataBean implements Serializable {
        /**
         * CurrentPage : 1
         * PageSize : 2
         * Total : 16
         * Rows : [{"OrderRefundExpress":null,"OrderExpress":null,"MainImg":null,"ProductName":null,"ProductId":null,"Price":0,"Number":0,"OrderRefundImg":null,"OrderRefundRecord":null,"OrderRefund":null,"OrderPayInfo":null,"Items":[{"Size":null,"Mark":null,"Info":null,"Id":7530,"OrderId":10069,"ProductId":61694,"ProductNo":"201702200001000088","ProductName":"女装2017年轻工钉珠品质蕾丝花面双层长袖长款连衣裙","ProductLevel":1,"Price":696,"Unit":"","Number":1,"MainImage":"http://f1.myappcc.com/zfs/7E1/1051/MUH/051124458723CABHAEJDLL.jpg","ProCost":"","Fabric":"","FabricColor":"","FabricColorValue":"","MaxCoin":139,"PkValue":7530}],"OrderAddress":{"OrderId":10069,"Privonce":"北京市","City":"市辖区","District":"东城区","Address":"长安街中南海","Contact":"海因里希","PostCode":null,"Mobile":"13569039897","PkValue":10069},"Records":null,"Garment":null,"PayTypeName":"线下支付","StatusClientName":"待付款","StatusAdminName":"待付款","Id":10069,"OrderNo":"201702220001000441","OrderedUserId":94589,"OrderedUsername":"17688159789","OrderTime":"2017-02-22 17:15:42","Status":11,"OrderSubject":"女装2017年轻工钉珠品质蕾丝花面双层长袖长款连衣裙","OrderType":1,"OrderSummary":"[17688159789]订购了:[女装2017年轻工钉珠品质蕾丝花面双层长袖长款连衣裙]:x1","TotalNum":1,"TotalFee":696,"Coin":28,"ExpressFee":0,"DiscountedPrice":0,"ActualFee":668,"PayType":0,"PayTime":"0001-01-01 00:00:00","IsDelete":0,"IsRemove":0,"StatusTime":"0001-01-01 00:00:00","SendTime":"0001-01-01 00:00:00","LastCancleDate":"2017-03-01 17:15:42","LastRefundDate":"0001-01-01 00:00:00","LastSureDate":"0001-01-01 00:00:00","IsAllowRefund":0,"IsAllowReturn":0,"CustomerRmk":"good","CreatorUsername":"17688159789","CreatorDate":"2017-02-22 17:15:42","UpdateUsername":"","UpdateDate":"0001-01-01 00:00:00","Rmk":"","FollowNikename":"","FollowDate":"0001-01-01 00:00:00","FollowUserId":0,"RegApp":"00","PkValue":10069},{"OrderRefundExpress":null,"OrderExpress":null,"MainImg":null,"ProductName":null,"ProductId":null,"Price":0,"Number":0,"OrderRefundImg":null,"OrderRefundRecord":null,"OrderRefund":null,"OrderPayInfo":null,"Items":[{"Size":null,"Mark":null,"Info":null,"Id":7529,"OrderId":10068,"ProductId":61694,"ProductNo":"201702200001000088","ProductName":"女装2017年轻工钉珠品质蕾丝花面双层长袖长款连衣裙","ProductLevel":1,"Price":696,"Unit":"","Number":1,"MainImage":"http://f1.myappcc.com/zfs/7E1/1051/MUH/051124458723CABHAEJDLL.jpg","ProCost":"","Fabric":"","FabricColor":"","FabricColorValue":"","MaxCoin":139,"PkValue":7529}],"OrderAddress":{"OrderId":10068,"Privonce":"北京市","City":"市辖区","District":"东城区","Address":"长安街中南海","Contact":"海因里希","PostCode":null,"Mobile":"13569039897","PkValue":10068},"Records":null,"Garment":null,"PayTypeName":"线下支付","StatusClientName":"待付款","StatusAdminName":"待付款","Id":10068,"OrderNo":"201702220001000420","OrderedUserId":94589,"OrderedUsername":"17688159789","OrderTime":"2017-02-22 17:10:18","Status":11,"OrderSubject":"女装2017年轻工钉珠品质蕾丝花面双层长袖长款连衣裙","OrderType":1,"OrderSummary":"[17688159789]订购了:[女装2017年轻工钉珠品质蕾丝花面双层长袖长款连衣裙]:x1","TotalNum":1,"TotalFee":696,"Coin":28,"ExpressFee":0,"DiscountedPrice":0,"ActualFee":668,"PayType":0,"PayTime":"0001-01-01 00:00:00","IsDelete":0,"IsRemove":0,"StatusTime":"0001-01-01 00:00:00","SendTime":"0001-01-01 00:00:00","LastCancleDate":"2017-03-01 17:10:18","LastRefundDate":"0001-01-01 00:00:00","LastSureDate":"0001-01-01 00:00:00","IsAllowRefund":0,"IsAllowReturn":0,"CustomerRmk":"good","CreatorUsername":"17688159789","CreatorDate":"2017-02-22 17:10:18","UpdateUsername":"","UpdateDate":"0001-01-01 00:00:00","Rmk":"","FollowNikename":"","FollowDate":"0001-01-01 00:00:00","FollowUserId":0,"RegApp":"00","PkValue":10068}]
         * PageCount : 8
         * PageRecordStartNum : 1
         * PageRocordEndNum : 2
         */

        private int CurrentPage;
        private int PageSize;
        private int Total;
        private int PageCount;
        private int PageRecordStartNum;
        private int PageRocordEndNum;
        private List<RowsBean> Rows;

        public int getCurrentPage() {
            return CurrentPage;
        }

        public void setCurrentPage(int CurrentPage) {
            this.CurrentPage = CurrentPage;
        }

        public int getPageSize() {
            return PageSize;
        }

        public void setPageSize(int PageSize) {
            this.PageSize = PageSize;
        }

        public int getTotal() {
            return Total;
        }

        public void setTotal(int Total) {
            this.Total = Total;
        }

        public int getPageCount() {
            return PageCount;
        }

        public void setPageCount(int PageCount) {
            this.PageCount = PageCount;
        }

        public int getPageRecordStartNum() {
            return PageRecordStartNum;
        }

        public void setPageRecordStartNum(int PageRecordStartNum) {
            this.PageRecordStartNum = PageRecordStartNum;
        }

        public int getPageRocordEndNum() {
            return PageRocordEndNum;
        }

        public void setPageRocordEndNum(int PageRocordEndNum) {
            this.PageRocordEndNum = PageRocordEndNum;
        }

        public List<RowsBean> getRows() {
            return Rows;
        }

        public void setRows(List<RowsBean> Rows) {
            this.Rows = Rows;
        }

        public static class RowsBean implements Serializable {
            @Override
            public String toString() {
                return "RowsBean{" +
                        "OrderRefundExpress='" + OrderRefundExpress + '\'' +
                        ", OrderExpress='" + OrderExpress + '\'' +
                        ", MainImg='" + MainImg + '\'' +
                        ", ProductName='" + ProductName + '\'' +
                        ", ProductId='" + ProductId + '\'' +
                        ", Price='" + Price + '\'' +
                        ", Number=" + Number +
                        ", OrderRefundImg='" + OrderRefundImg + '\'' +
                        ", OrderRefundRecord='" + OrderRefundRecord + '\'' +
                        ", OrderRefund='" + OrderRefund + '\'' +
                        ", OrderPayInfo='" + OrderPayInfo + '\'' +
                        ", OrderAddress=" + OrderAddress +
                        ", Records='" + Records + '\'' +
                        ", Garment='" + Garment + '\'' +
                        ", PayTypeName='" + PayTypeName + '\'' +
                        ", StatusClientName='" + StatusClientName + '\'' +
                        ", StatusAdminName='" + StatusAdminName + '\'' +
                        ", Id=" + Id +
                        ", OrderNo='" + OrderNo + '\'' +
                        ", OrderedUserId=" + OrderedUserId +
                        ", OrderedUsername='" + OrderedUsername + '\'' +
                        ", OrderTime='" + OrderTime + '\'' +
                        ", Status=" + Status +
                        ", OrderSubject='" + OrderSubject + '\'' +
                        ", OrderType=" + OrderType +
                        ", OrderSummary='" + OrderSummary + '\'' +
                        ", TotalNum=" + TotalNum +
                        ", TotalFee='" + TotalFee + '\'' +
                        ", Coin=" + Coin +
                        ", ExpressFee='" + ExpressFee + '\'' +
                        ", DiscountedPrice='" + DiscountedPrice + '\'' +
                        ", ActualFee='" + ActualFee + '\'' +
                        ", PayType=" + PayType +
                        ", PayTime='" + PayTime + '\'' +
                        ", IsDelete=" + IsDelete +
                        ", IsRemove=" + IsRemove +
                        ", StatusTime='" + StatusTime + '\'' +
                        ", SendTime='" + SendTime + '\'' +
                        ", LastCancleDate='" + LastCancleDate + '\'' +
                        ", LastRefundDate='" + LastRefundDate + '\'' +
                        ", LastSureDate='" + LastSureDate + '\'' +
                        ", IsAllowRefund=" + IsAllowRefund +
                        ", IsAllowReturn=" + IsAllowReturn +
                        ", CustomerRmk='" + CustomerRmk + '\'' +
                        ", CreatorUsername='" + CreatorUsername + '\'' +
                        ", CreatorDate='" + CreatorDate + '\'' +
                        ", UpdateUsername='" + UpdateUsername + '\'' +
                        ", UpdateDate='" + UpdateDate + '\'' +
                        ", Rmk='" + Rmk + '\'' +
                        ", FollowNikename='" + FollowNikename + '\'' +
                        ", FollowDate='" + FollowDate + '\'' +
                        ", FollowUserId=" + FollowUserId +
                        ", RegApp='" + RegApp + '\'' +
                        ", PkValue=" + PkValue +
                        ", Items=" + Items +
                        '}';
            }

            /**
             * OrderRefundExpress : null
             * OrderExpress : null
             * MainImg : null
             * ProductName : null
             * ProductId : null
             * Price : 0
             * Number : 0
             * OrderRefundImg : null
             * OrderRefundRecord : null
             *   : null
             * OrderPayInfo : null
             * Items : [{"Size":null,"Mark":null,"Info":null,"Id":7530,"OrderId":10069,"ProductId":61694,"ProductNo":"201702200001000088","ProductName":"女装2017年轻工钉珠品质蕾丝花面双层长袖长款连衣裙","ProductLevel":1,"Price":696,"Unit":"","Number":1,"MainImage":"http://f1.myappcc.com/zfs/7E1/1051/MUH/051124458723CABHAEJDLL.jpg","ProCost":"","Fabric":"","FabricColor":"","FabricColorValue":"","MaxCoin":139,"PkValue":7530}]
             * OrderAddress : {"OrderId":10069,"Privonce":"北京市","City":"市辖区","District":"东城区","Address":"长安街中南海","Contact":"海因里希","PostCode":null,"Mobile":"13569039897","PkValue":10069}
             * Records : null
             * Garment : null
             * PayTypeName : 线下支付
             * StatusClientName : 待付款
             * StatusAdminName : 待付款
             * Id : 10069
             * OrderNo : 201702220001000441
             * OrderedUserId : 94589
             * OrderedUsername : 17688159789
             * OrderTime : 2017-02-22 17:15:42
             * Status : 11
             * OrderSubject : 女装2017年轻工钉珠品质蕾丝花面双层长袖长款连衣裙
             * OrderType : 1
             * OrderSummary : [17688159789]订购了:[女装2017年轻工钉珠品质蕾丝花面双层长袖长款连衣裙]:x1
             * TotalNum : 1
             * TotalFee : 696
             * Coin : 28
             * ExpressFee : 0
             * DiscountedPrice : 0
             * ActualFee : 668
             * PayType : 0
             * PayTime : 0001-01-01 00:00:00
             * IsDelete : 0
             * IsRemove : 0
             *
             * StatusTime : 0001-01-01 00:00:00
             * SendTime : 0001-01-01 00:00:00
             * LastCancleDate : 2017-03-01 17:15:42
             * LastRefundDate : 0001-01-01 00:00:00
             * LastSureDate : 0001-01-01 00:00:00
             * IsAllowRefund : 0
             * IsAllowReturn : 0
             * CustomerRmk : good
             * CreatorUsername : 17688159789
             * CreatorDate : 2017-02-22 17:15:42
             * UpdateUsername :
             * UpdateDate : 0001-01-01 00:00:00
             * Rmk :
             * FollowNikename :
             * FollowDate : 0001-01-01 00:00:00
             * FollowUserId : 0
             * RegApp : 00
             * PkValue : 10069
             */

            private String OrderRefundExpress;
            private String OrderExpress;
            private String MainImg;
            private String ProductName;
            private String ProductId;
            private String Price;
            private int Number;
            private String OrderRefundImg;
            private String OrderRefundRecord;
            private String OrderRefund;
            private String OrderPayInfo;
            private OrderAddressBean OrderAddress;
            private String Records;
            private String Garment;
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
            private String TotalFee;
            private double Coin;
            private String ExpressFee;
            private String DiscountedPrice;
            private String ActualFee;
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
            private List<ItemsBean> Items;

            public String getOrderRefundExpress() {
                return OrderRefundExpress;
            }

            public void setOrderRefundExpress(String OrderRefundExpress) {
                this.OrderRefundExpress = OrderRefundExpress;
            }

            public String getOrderExpress() {
                return OrderExpress;
            }

            public void setOrderExpress(String OrderExpress) {
                this.OrderExpress = OrderExpress;
            }

            public String getMainImg() {
                return MainImg;
            }

            public void setMainImg(String MainImg) {
                this.MainImg = MainImg;
            }

            public String getProductName() {
                return ProductName;
            }

            public void setProductName(String ProductName) {
                this.ProductName = ProductName;
            }

            public String getProductId() {
                return ProductId;
            }

            public void setProductId(String ProductId) {
                this.ProductId = ProductId;
            }

            public String getPrice() {
                return Price;
            }

            public void setPrice(String Price) {
                this.Price = Price;
            }

            public int getNumber() {
                return Number;
            }

            public void setNumber(int Number) {
                this.Number = Number;
            }

            public String getOrderRefundImg() {
                return OrderRefundImg;
            }

            public void setOrderRefundImg(String OrderRefundImg) {
                this.OrderRefundImg = OrderRefundImg;
            }

            public String getOrderRefundRecord() {
                return OrderRefundRecord;
            }

            public void setOrderRefundRecord(String OrderRefundRecord) {
                this.OrderRefundRecord = OrderRefundRecord;
            }

            public String getOrderRefund() {
                return OrderRefund;
            }

            public void setOrderRefund(String OrderRefund) {
                this.OrderRefund = OrderRefund;
            }

            public String getOrderPayInfo() {
                return OrderPayInfo;
            }

            public void setOrderPayInfo(String OrderPayInfo) {
                this.OrderPayInfo = OrderPayInfo;
            }

            public OrderAddressBean getOrderAddress() {
                return OrderAddress;
            }

            public void setOrderAddress(OrderAddressBean OrderAddress) {
                this.OrderAddress = OrderAddress;
            }

            public String getRecords() {
                return Records;
            }

            public void setRecords(String Records) {
                this.Records = Records;
            }

            public String getGarment() {
                return Garment;
            }

            public void setGarment(String Garment) {
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

            public String getTotalFee() {
                return TotalFee;
            }

            public void setTotalFee(String TotalFee) {
                this.TotalFee = TotalFee;
            }

            public double getCoin() {
                return Coin;
            }

            public void setCoin(double Coin) {
                this.Coin = Coin;
            }

            public String getExpressFee() {
                return ExpressFee;
            }

            public void setExpressFee(String ExpressFee) {
                this.ExpressFee = ExpressFee;
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

            public List<ItemsBean> getItems() {
                return Items;
            }

            public void setItems(List<ItemsBean> Items) {
                this.Items = Items;
            }

            public static class OrderAddressBean implements Serializable {
                /**
                 * OrderId : 10069
                 * Privonce : 北京市
                 * City : 市辖区
                 * District : 东城区
                 * Address : 长安街中南海
                 * Contact : 海因里希
                 * PostCode : null
                 * Mobile : 13569039897
                 * PkValue : 10069
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

            public static class ItemsBean implements Serializable {
                /**
                 * Size : null
                 * Mark : null
                 * Info : null
                 * Id : 7530
                 * OrderId : 10069
                 * ProductId : 61694
                 * ProductNo : 201702200001000088
                 * ProductName : 女装2017年轻工钉珠品质蕾丝花面双层长袖长款连衣裙
                 * ProductLevel : 1
                 * Price : 696
                 * Unit :
                 * Number : 1
                 * MainImage : http://f1.myappcc.com/zfs/7E1/1051/MUH/051124458723CABHAEJDLL.jpg
                 * ProCost :
                 * Fabric :
                 * FabricColor :
                 * FabricColorValue :
                 * MaxCoin : 139
                 * PkValue : 7530
                 */

                private String Size;
                private String Mark;
                private String Info;
                private int Id;
                private int OrderId;
                private int ProductId;
                private String ProductNo;
                private String ProductName;
                private int ProductLevel;
                private String Price;
                private String Unit;
                private int Number;
                private String MainImage;
                private String ProCost;
                private String Fabric;
                private String FabricColor;
                private String FabricColorValue;
                private int MaxCoin;
                private int PkValue;

                public String getSize() {
                    return Size;
                }

                public void setSize(String Size) {
                    this.Size = Size;
                }

                public String getMark() {
                    return Mark;
                }

                public void setMark(String Mark) {
                    this.Mark = Mark;
                }

                public String getInfo() {
                    return Info;
                }

                public void setInfo(String Info) {
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

                public String getPrice() {
                    return Price;
                }

                public void setPrice(String Price) {
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
        }
    }
}
