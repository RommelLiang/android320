package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/3/10.
 */

public class TiuKuanMessage {

    /**
     * Code : 1
     * Msg : OK
     * Data : {"OrderRefundExpress":null,"OrderExpress":null,"MainImg":null,"ProductName":null,"ProductId":null,"Price":0,"Number":0,"OrderRefundImg":null,"OrderRefundRecord":[{"Id":2560,"OrderId":10449,"UserId":97238,"SourceStatus":1,"ActionName":"","TargetStatus":9,"ActionTime":"2017-03-10 10:52:25","Username":"17688159789","NickName":"穿友97238","Ip":"106.185.52.213","Record":"退款申请已通过(无退货)","PkValue":2560},{"Id":2559,"OrderId":10449,"UserId":97238,"SourceStatus":1,"ActionName":"新增","TargetStatus":1,"ActionTime":"2017-03-09 19:56:52","Username":"17688159789","NickName":"17688159789","Ip":"113.91.213.14","Record":"用户新增申请退款,未发货","PkValue":2559}],"OrderRefund":{"OrderId":10449,"TotalFee":291,"CcCoin":0,"OnlineFee":291,"OrdPreStatus":12,"ApplyTimes":1,"Status":9,"ActionType":15,"ActionUserId":97238,"ApplyTime":"2017-03-09 19:56:52","AuditTime":"0001-01-01 00:00:00","RefundType":5,"RefundTime":"0001-01-01 00:00:00","IsNeedReturn":0,"Mobile":"13569039897","Contact":"莱茵哈特","ActionUsername":"17688159789","CreatorDate":"2017-03-09 19:56:52","CreatorUsername":"17688159789","UpdateDate":"2017-03-10 10:52:25","UpdateUsername":"17688159789","RefundReason":"用户申请退款，未发货！","RefuseReason":"","Rmk":"","GoodsUsername":"","GoodsTime":"0001-01-01 00:00:00","PkValue":10449,"OrderNo":null,"Info":null,"RefundImgs":null,"RefundRecords":null,"ActionTypeName":"退款","StatusName":"待退款","RefundTypeName":"零钱","OrderStatus":0,"OrderStatusName":"0"},"OrderPayInfo":null,"Items":[{"Size":null,"Mark":null,"Info":null,"Id":7851,"OrderId":10449,"ProductId":65217,"ProductNo":"201703080001000111","ProductName":"女装2017年高级梭织棉单层长袖短款衬衣","ProductLevel":1,"Price":291,"Unit":"","Number":1,"MainImage":"http://f1.myappcc.com/zfs/7E1/1067/RIC/067171438221CABHAGDJXY.png","ProCost":"","Fabric":"","FabricColor":"","FabricColorValue":"","MaxCoin":58,"PkValue":7851}],"OrderAddress":{"OrderId":10449,"Privonce":"北京市","City":"北京市","District":"东城区","Address":"带回家大家","Contact":"莱茵哈特","PostCode":"000000","Mobile":"13569039897","PkValue":10449},"Records":[{"Id":16759,"OrderId":10449,"UserId":97238,"SourceStatus":11,"ActionName":"下单","TargetStatus":100,"ActionTime":"2017-03-08 17:19:24","Username":"17688159789","NickName":"穿友97238","Ip":"113.91.213.249","Record":"用户[穿友97238]下单","PkValue":16759}],"Garment":null,"PayTypeName":"零钱","StatusClientName":"退款待审批","StatusAdminName":"退款处理中","Id":10449,"OrderNo":"201703080001000441","OrderedUserId":97238,"OrderedUsername":"17688159789","OrderTime":"2017-03-08 17:19:24","Status":31,"OrderSubject":"女装2017年高级梭织棉单层长袖短款衬衣","OrderType":1,"OrderSummary":"[17688159789]订购了:[女装2017年高级梭织棉单层长袖短款衬衣]:x1","TotalNum":1,"TotalFee":291,"Coin":0,"ExpressFee":0,"DiscountedPrice":0,"ActualFee":291,"PayType":5,"PayTime":"2017-03-08 17:19:25","IsDelete":0,"IsRemove":0,"StatusTime":"0001-01-01 00:00:00","SendTime":"0001-01-01 00:00:00","LastCancleDate":"2017-03-15 17:19:24","LastRefundDate":"0001-01-01 00:00:00","LastSureDate":"0001-01-01 00:00:00","IsAllowRefund":1,"IsAllowReturn":0,"CustomerRmk":"good","CreatorUsername":"17688159789","CreatorDate":"2017-03-08 17:19:24","UpdateUsername":"","UpdateDate":"0001-01-01 00:00:00","Rmk":"","FollowNikename":"","FollowDate":"0001-01-01 00:00:00","FollowUserId":0,"RegApp":"00","PkValue":10449}
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
         * OrderRefundExpress : null
         * OrderExpress : null
         * MainImg : null
         * ProductName : null
         * ProductId : null
         * Price : 0.0
         * Number : 0
         * OrderRefundImg : null
         * OrderRefundRecord : [{"Id":2560,"OrderId":10449,"UserId":97238,"SourceStatus":1,"ActionName":"","TargetStatus":9,"ActionTime":"2017-03-10 10:52:25","Username":"17688159789","NickName":"穿友97238","Ip":"106.185.52.213","Record":"退款申请已通过(无退货)","PkValue":2560},{"Id":2559,"OrderId":10449,"UserId":97238,"SourceStatus":1,"ActionName":"新增","TargetStatus":1,"ActionTime":"2017-03-09 19:56:52","Username":"17688159789","NickName":"17688159789","Ip":"113.91.213.14","Record":"用户新增申请退款,未发货","PkValue":2559}]
         * OrderRefund : {"OrderId":10449,"TotalFee":291,"CcCoin":0,"OnlineFee":291,"OrdPreStatus":12,"ApplyTimes":1,"Status":9,"ActionType":15,"ActionUserId":97238,"ApplyTime":"2017-03-09 19:56:52","AuditTime":"0001-01-01 00:00:00","RefundType":5,"RefundTime":"0001-01-01 00:00:00","IsNeedReturn":0,"Mobile":"13569039897","Contact":"莱茵哈特","ActionUsername":"17688159789","CreatorDate":"2017-03-09 19:56:52","CreatorUsername":"17688159789","UpdateDate":"2017-03-10 10:52:25","UpdateUsername":"17688159789","RefundReason":"用户申请退款，未发货！","RefuseReason":"","Rmk":"","GoodsUsername":"","GoodsTime":"0001-01-01 00:00:00","PkValue":10449,"OrderNo":null,"Info":null,"RefundImgs":null,"RefundRecords":null,"ActionTypeName":"退款","StatusName":"待退款","RefundTypeName":"零钱","OrderStatus":0,"OrderStatusName":"0"}
         * OrderPayInfo : null
         * Items : [{"Size":null,"Mark":null,"Info":null,"Id":7851,"OrderId":10449,"ProductId":65217,"ProductNo":"201703080001000111","ProductName":"女装2017年高级梭织棉单层长袖短款衬衣","ProductLevel":1,"Price":291,"Unit":"","Number":1,"MainImage":"http://f1.myappcc.com/zfs/7E1/1067/RIC/067171438221CABHAGDJXY.png","ProCost":"","Fabric":"","FabricColor":"","FabricColorValue":"","MaxCoin":58,"PkValue":7851}]
         * OrderAddress : {"OrderId":10449,"Privonce":"北京市","City":"北京市","District":"东城区","Address":"带回家大家","Contact":"莱茵哈特","PostCode":"000000","Mobile":"13569039897","PkValue":10449}
         * Records : [{"Id":16759,"OrderId":10449,"UserId":97238,"SourceStatus":11,"ActionName":"下单","TargetStatus":100,"ActionTime":"2017-03-08 17:19:24","Username":"17688159789","NickName":"穿友97238","Ip":"113.91.213.249","Record":"用户[穿友97238]下单","PkValue":16759}]
         * Garment : null
         * PayTypeName : 零钱
         * StatusClientName : 退款待审批
         * StatusAdminName : 退款处理中
         * Id : 10449
         * OrderNo : 201703080001000441
         * OrderedUserId : 97238
         * OrderedUsername : 17688159789
         * OrderTime : 2017-03-08 17:19:24
         * Status : 31
         * OrderSubject : 女装2017年高级梭织棉单层长袖短款衬衣
         * OrderType : 1
         * OrderSummary : [17688159789]订购了:[女装2017年高级梭织棉单层长袖短款衬衣]:x1
         * TotalNum : 1
         * TotalFee : 291.0
         * Coin : 0
         * ExpressFee : 0.0
         * DiscountedPrice : 0.0
         * ActualFee : 291.0
         * PayType : 5
         * PayTime : 2017-03-08 17:19:25
         * IsDelete : 0
         * IsRemove : 0
         * StatusTime : 0001-01-01 00:00:00
         * SendTime : 0001-01-01 00:00:00
         * LastCancleDate : 2017-03-15 17:19:24
         * LastRefundDate : 0001-01-01 00:00:00
         * LastSureDate : 0001-01-01 00:00:00
         * IsAllowRefund : 1
         * IsAllowReturn : 0
         * CustomerRmk : good
         * CreatorUsername : 17688159789
         * CreatorDate : 2017-03-08 17:19:24
         * UpdateUsername :
         * UpdateDate : 0001-01-01 00:00:00
         * Rmk :
         * FollowNikename :
         * FollowDate : 0001-01-01 00:00:00
         * FollowUserId : 0
         * RegApp : 00
         * PkValue : 10449
         */

        private Object OrderRefundExpress;
        private Object OrderExpress;
        private Object MainImg;
        private Object ProductName;
        private Object ProductId;
        private double Price;
        private int Number;
        private Object OrderRefundImg;
        private OrderRefundBean OrderRefund;
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
        private List<OrderRefundRecordBean> OrderRefundRecord;
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

        public OrderRefundBean getOrderRefund() {
            return OrderRefund;
        }

        public void setOrderRefund(OrderRefundBean OrderRefund) {
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

        public List<OrderRefundRecordBean> getOrderRefundRecord() {
            return OrderRefundRecord;
        }

        public void setOrderRefundRecord(List<OrderRefundRecordBean> OrderRefundRecord) {
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

        public static class OrderRefundBean {
            /**
             * OrderId : 10449
             * TotalFee : 291.0
             * CcCoin : 0.0
             * OnlineFee : 291.0
             * OrdPreStatus : 12
             * ApplyTimes : 1
             * Status : 9
             * ActionType : 15
             * ActionUserId : 97238
             * ApplyTime : 2017-03-09 19:56:52
             * AuditTime : 0001-01-01 00:00:00
             * RefundType : 5
             * RefundTime : 0001-01-01 00:00:00
             * IsNeedReturn : 0
             * Mobile : 13569039897
             * Contact : 莱茵哈特
             * ActionUsername : 17688159789
             * CreatorDate : 2017-03-09 19:56:52
             * CreatorUsername : 17688159789
             * UpdateDate : 2017-03-10 10:52:25
             * UpdateUsername : 17688159789
             * RefundReason : 用户申请退款，未发货！
             * RefuseReason :
             * Rmk :
             * GoodsUsername :
             * GoodsTime : 0001-01-01 00:00:00
             * PkValue : 10449
             * OrderNo : null
             * Info : null
             * RefundImgs : null
             * RefundRecords : null
             * ActionTypeName : 退款
             * StatusName : 待退款
             * RefundTypeName : 零钱
             * OrderStatus : 0
             * OrderStatusName : 0
             */

            private int OrderId;
            private double TotalFee;
            private double CcCoin;
            private double OnlineFee;
            private int OrdPreStatus;
            private int ApplyTimes;
            private int Status;
            private int ActionType;
            private int ActionUserId;
            private String ApplyTime;
            private String AuditTime;
            private int RefundType;
            private String RefundTime;
            private int IsNeedReturn;
            private String Mobile;
            private String Contact;
            private String ActionUsername;
            private String CreatorDate;
            private String CreatorUsername;
            private String UpdateDate;
            private String UpdateUsername;
            private String RefundReason;
            private String RefuseReason;
            private String Rmk;
            private String GoodsUsername;
            private String GoodsTime;
            private int PkValue;
            private Object OrderNo;
            private Object Info;
            private Object RefundImgs;
            private Object RefundRecords;
            private String ActionTypeName;
            private String StatusName;
            private String RefundTypeName;
            private int OrderStatus;
            private String OrderStatusName;

            public int getOrderId() {
                return OrderId;
            }

            public void setOrderId(int OrderId) {
                this.OrderId = OrderId;
            }

            public double getTotalFee() {
                return TotalFee;
            }

            public void setTotalFee(double TotalFee) {
                this.TotalFee = TotalFee;
            }

            public double getCcCoin() {
                return CcCoin;
            }

            public void setCcCoin(double CcCoin) {
                this.CcCoin = CcCoin;
            }

            public double getOnlineFee() {
                return OnlineFee;
            }

            public void setOnlineFee(double OnlineFee) {
                this.OnlineFee = OnlineFee;
            }

            public int getOrdPreStatus() {
                return OrdPreStatus;
            }

            public void setOrdPreStatus(int OrdPreStatus) {
                this.OrdPreStatus = OrdPreStatus;
            }

            public int getApplyTimes() {
                return ApplyTimes;
            }

            public void setApplyTimes(int ApplyTimes) {
                this.ApplyTimes = ApplyTimes;
            }

            public int getStatus() {
                return Status;
            }

            public void setStatus(int Status) {
                this.Status = Status;
            }

            public int getActionType() {
                return ActionType;
            }

            public void setActionType(int ActionType) {
                this.ActionType = ActionType;
            }

            public int getActionUserId() {
                return ActionUserId;
            }

            public void setActionUserId(int ActionUserId) {
                this.ActionUserId = ActionUserId;
            }

            public String getApplyTime() {
                return ApplyTime;
            }

            public void setApplyTime(String ApplyTime) {
                this.ApplyTime = ApplyTime;
            }

            public String getAuditTime() {
                return AuditTime;
            }

            public void setAuditTime(String AuditTime) {
                this.AuditTime = AuditTime;
            }

            public int getRefundType() {
                return RefundType;
            }

            public void setRefundType(int RefundType) {
                this.RefundType = RefundType;
            }

            public String getRefundTime() {
                return RefundTime;
            }

            public void setRefundTime(String RefundTime) {
                this.RefundTime = RefundTime;
            }

            public int getIsNeedReturn() {
                return IsNeedReturn;
            }

            public void setIsNeedReturn(int IsNeedReturn) {
                this.IsNeedReturn = IsNeedReturn;
            }

            public String getMobile() {
                return Mobile;
            }

            public void setMobile(String Mobile) {
                this.Mobile = Mobile;
            }

            public String getContact() {
                return Contact;
            }

            public void setContact(String Contact) {
                this.Contact = Contact;
            }

            public String getActionUsername() {
                return ActionUsername;
            }

            public void setActionUsername(String ActionUsername) {
                this.ActionUsername = ActionUsername;
            }

            public String getCreatorDate() {
                return CreatorDate;
            }

            public void setCreatorDate(String CreatorDate) {
                this.CreatorDate = CreatorDate;
            }

            public String getCreatorUsername() {
                return CreatorUsername;
            }

            public void setCreatorUsername(String CreatorUsername) {
                this.CreatorUsername = CreatorUsername;
            }

            public String getUpdateDate() {
                return UpdateDate;
            }

            public void setUpdateDate(String UpdateDate) {
                this.UpdateDate = UpdateDate;
            }

            public String getUpdateUsername() {
                return UpdateUsername;
            }

            public void setUpdateUsername(String UpdateUsername) {
                this.UpdateUsername = UpdateUsername;
            }

            public String getRefundReason() {
                return RefundReason;
            }

            public void setRefundReason(String RefundReason) {
                this.RefundReason = RefundReason;
            }

            public String getRefuseReason() {
                return RefuseReason;
            }

            public void setRefuseReason(String RefuseReason) {
                this.RefuseReason = RefuseReason;
            }

            public String getRmk() {
                return Rmk;
            }

            public void setRmk(String Rmk) {
                this.Rmk = Rmk;
            }

            public String getGoodsUsername() {
                return GoodsUsername;
            }

            public void setGoodsUsername(String GoodsUsername) {
                this.GoodsUsername = GoodsUsername;
            }

            public String getGoodsTime() {
                return GoodsTime;
            }

            public void setGoodsTime(String GoodsTime) {
                this.GoodsTime = GoodsTime;
            }

            public int getPkValue() {
                return PkValue;
            }

            public void setPkValue(int PkValue) {
                this.PkValue = PkValue;
            }

            public Object getOrderNo() {
                return OrderNo;
            }

            public void setOrderNo(Object OrderNo) {
                this.OrderNo = OrderNo;
            }

            public Object getInfo() {
                return Info;
            }

            public void setInfo(Object Info) {
                this.Info = Info;
            }

            public Object getRefundImgs() {
                return RefundImgs;
            }

            public void setRefundImgs(Object RefundImgs) {
                this.RefundImgs = RefundImgs;
            }

            public Object getRefundRecords() {
                return RefundRecords;
            }

            public void setRefundRecords(Object RefundRecords) {
                this.RefundRecords = RefundRecords;
            }

            public String getActionTypeName() {
                return ActionTypeName;
            }

            public void setActionTypeName(String ActionTypeName) {
                this.ActionTypeName = ActionTypeName;
            }

            public String getStatusName() {
                return StatusName;
            }

            public void setStatusName(String StatusName) {
                this.StatusName = StatusName;
            }

            public String getRefundTypeName() {
                return RefundTypeName;
            }

            public void setRefundTypeName(String RefundTypeName) {
                this.RefundTypeName = RefundTypeName;
            }

            public int getOrderStatus() {
                return OrderStatus;
            }

            public void setOrderStatus(int OrderStatus) {
                this.OrderStatus = OrderStatus;
            }

            public String getOrderStatusName() {
                return OrderStatusName;
            }

            public void setOrderStatusName(String OrderStatusName) {
                this.OrderStatusName = OrderStatusName;
            }
        }

        public static class OrderAddressBean {
            /**
             * OrderId : 10449
             * Privonce : 北京市
             * City : 北京市
             * District : 东城区
             * Address : 带回家大家
             * Contact : 莱茵哈特
             * PostCode : 000000
             * Mobile : 13569039897
             * PkValue : 10449
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

        public static class OrderRefundRecordBean {
            /**
             * Id : 2560
             * OrderId : 10449
             * UserId : 97238
             * SourceStatus : 1
             * ActionName :
             * TargetStatus : 9
             * ActionTime : 2017-03-10 10:52:25
             * Username : 17688159789
             * NickName : 穿友97238
             * Ip : 106.185.52.213
             * Record : 退款申请已通过(无退货)
             * PkValue : 2560
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

        public static class ItemsBean {
            /**
             * Size : null
             * Mark : null
             * Info : null
             * Id : 7851
             * OrderId : 10449
             * ProductId : 65217
             * ProductNo : 201703080001000111
             * ProductName : 女装2017年高级梭织棉单层长袖短款衬衣
             * ProductLevel : 1
             * Price : 291.0
             * Unit :
             * Number : 1
             * MainImage : http://f1.myappcc.com/zfs/7E1/1067/RIC/067171438221CABHAGDJXY.png
             * ProCost :
             * Fabric :
             * FabricColor :
             * FabricColorValue :
             * MaxCoin : 58
             * PkValue : 7851
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
             * Id : 16759
             * OrderId : 10449
             * UserId : 97238
             * SourceStatus : 11
             * ActionName : 下单
             * TargetStatus : 100
             * ActionTime : 2017-03-08 17:19:24
             * Username : 17688159789
             * NickName : 穿友97238
             * Ip : 113.91.213.249
             * Record : 用户[穿友97238]下单
             * PkValue : 16759
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
}
