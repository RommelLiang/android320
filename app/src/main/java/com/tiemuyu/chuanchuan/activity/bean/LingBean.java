package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/2/28.
 */

public class LingBean {

    /**
     * Code : 1
     * Msg : OK
     * Data : {"CurrentPage":1,"PageSize":15,"Total":3,"Rows":[{"Id":7750,"UserId":94589,"TradeNo":"20170222162600813","TradeAmounts":-209,"BeforeTrade":291,"AfterTrade":82,"TradeType":1,"InExType":-1,"Summary":"支付订单:201702220001000378","PayType":5,"OrderNo":"201702220001000378","TradeTime":"2017-02-22 16:26:13","CreatorUsername":"17688159789","PkValue":7750,"UserName":null,"TradeTypeName":"付款","PayTypeName":"零钱","InExTypeName":"支出"},{"Id":7747,"UserId":94589,"TradeNo":"20170221171000824","TradeAmounts":-209,"BeforeTrade":500,"AfterTrade":291,"TradeType":1,"InExType":-1,"Summary":"支付订单:201702210001000567","PayType":5,"OrderNo":"201702210001000567","TradeTime":"2017-02-21 17:10:18","CreatorUsername":"17688159789","PkValue":7747,"UserName":null,"TradeTypeName":"付款","PayTypeName":"零钱","InExTypeName":"支出"},{"Id":7746,"UserId":94589,"TradeNo":"20170221170600813","TradeAmounts":500,"BeforeTrade":0,"AfterTrade":500,"TradeType":255,"InExType":1,"Summary":"安卓开发测试","PayType":5,"OrderNo":"","TradeTime":"2017-02-21 17:06:45","CreatorUsername":"13530166523","PkValue":7746,"UserName":null,"TradeTypeName":"系统发放","PayTypeName":"零钱","InExTypeName":"收入"}],"PageCount":1,"PageRecordStartNum":1,"PageRocordEndNum":3}
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
         * CurrentPage : 1
         * PageSize : 15
         * Total : 3
         * Rows : [{"Id":7750,"UserId":94589,"TradeNo":"20170222162600813","TradeAmounts":-209,"BeforeTrade":291,"AfterTrade":82,"TradeType":1,"InExType":-1,"Summary":"支付订单:201702220001000378","PayType":5,"OrderNo":"201702220001000378","TradeTime":"2017-02-22 16:26:13","CreatorUsername":"17688159789","PkValue":7750,"UserName":null,"TradeTypeName":"付款","PayTypeName":"零钱","InExTypeName":"支出"},{"Id":7747,"UserId":94589,"TradeNo":"20170221171000824","TradeAmounts":-209,"BeforeTrade":500,"AfterTrade":291,"TradeType":1,"InExType":-1,"Summary":"支付订单:201702210001000567","PayType":5,"OrderNo":"201702210001000567","TradeTime":"2017-02-21 17:10:18","CreatorUsername":"17688159789","PkValue":7747,"UserName":null,"TradeTypeName":"付款","PayTypeName":"零钱","InExTypeName":"支出"},{"Id":7746,"UserId":94589,"TradeNo":"20170221170600813","TradeAmounts":500,"BeforeTrade":0,"AfterTrade":500,"TradeType":255,"InExType":1,"Summary":"安卓开发测试","PayType":5,"OrderNo":"","TradeTime":"2017-02-21 17:06:45","CreatorUsername":"13530166523","PkValue":7746,"UserName":null,"TradeTypeName":"系统发放","PayTypeName":"零钱","InExTypeName":"收入"}]
         * PageCount : 1
         * PageRecordStartNum : 1
         * PageRocordEndNum : 3
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

        public static class RowsBean {
            /**
             * Id : 7750
             * UserId : 94589
             * TradeNo : 20170222162600813
             * TradeAmounts : -209.0
             * BeforeTrade : 291.0
             * AfterTrade : 82.0
             * TradeType : 1
             * InExType : -1
             * Summary : 支付订单:201702220001000378
             * PayType : 5
             * OrderNo : 201702220001000378
             * TradeTime : 2017-02-22 16:26:13
             * CreatorUsername : 17688159789
             * PkValue : 7750
             * UserName : null
             * TradeTypeName : 付款
             * PayTypeName : 零钱
             * InExTypeName : 支出
             */

            private int Id;
            private int UserId;
            private String TradeNo;
            private double TradeAmounts;
            private double BeforeTrade;
            private double AfterTrade;
            private int TradeType;
            private int InExType;
            private String Summary;
            private int PayType;
            private String OrderNo;
            private String TradeTime;
            private String CreatorUsername;
            private int PkValue;
            private Object UserName;
            private String TradeTypeName;
            private String PayTypeName;
            private String InExTypeName;

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

            public String getTradeNo() {
                return TradeNo;
            }

            public void setTradeNo(String TradeNo) {
                this.TradeNo = TradeNo;
            }

            public double getTradeAmounts() {
                return TradeAmounts;
            }

            public void setTradeAmounts(double TradeAmounts) {
                this.TradeAmounts = TradeAmounts;
            }

            public double getBeforeTrade() {
                return BeforeTrade;
            }

            public void setBeforeTrade(double BeforeTrade) {
                this.BeforeTrade = BeforeTrade;
            }

            public double getAfterTrade() {
                return AfterTrade;
            }

            public void setAfterTrade(double AfterTrade) {
                this.AfterTrade = AfterTrade;
            }

            public int getTradeType() {
                return TradeType;
            }

            public void setTradeType(int TradeType) {
                this.TradeType = TradeType;
            }

            public int getInExType() {
                return InExType;
            }

            public void setInExType(int InExType) {
                this.InExType = InExType;
            }

            public String getSummary() {
                return Summary;
            }

            public void setSummary(String Summary) {
                this.Summary = Summary;
            }

            public int getPayType() {
                return PayType;
            }

            public void setPayType(int PayType) {
                this.PayType = PayType;
            }

            public String getOrderNo() {
                return OrderNo;
            }

            public void setOrderNo(String OrderNo) {
                this.OrderNo = OrderNo;
            }

            public String getTradeTime() {
                return TradeTime;
            }

            public void setTradeTime(String TradeTime) {
                this.TradeTime = TradeTime;
            }

            public String getCreatorUsername() {
                return CreatorUsername;
            }

            public void setCreatorUsername(String CreatorUsername) {
                this.CreatorUsername = CreatorUsername;
            }

            public int getPkValue() {
                return PkValue;
            }

            public void setPkValue(int PkValue) {
                this.PkValue = PkValue;
            }

            public Object getUserName() {
                return UserName;
            }

            public void setUserName(Object UserName) {
                this.UserName = UserName;
            }

            public String getTradeTypeName() {
                return TradeTypeName;
            }

            public void setTradeTypeName(String TradeTypeName) {
                this.TradeTypeName = TradeTypeName;
            }

            public String getPayTypeName() {
                return PayTypeName;
            }

            public void setPayTypeName(String PayTypeName) {
                this.PayTypeName = PayTypeName;
            }

            public String getInExTypeName() {
                return InExTypeName;
            }

            public void setInExTypeName(String InExTypeName) {
                this.InExTypeName = InExTypeName;
            }
        }
    }
}
