package com.tiemuyu.chuanchuan.activity.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @项目名： 215androidpay
 * @包名： com.tiemuyu.chuanchuan.activity.bean
 * @类描述：
 * @创建人： hr
 * @创建时间： 2017/2/21
 * @version：
 */

public class NewShaituBean implements Serializable{
    /**
     * Code : 1
     * Msg : OK
     * Data : {"Pagedata":{"CurrentPage":1,"PageSize":8,"Total":2,"Rows":[{"MomentTypeDesc":"定制发图","UserName":null,"SharedDay":null,"SharedMonth":0,"ReviewSum":0,"FriendReview":null,"FriendReviewTop3":null,"UserImg":null,"UserNickName":null,"ProductPrice":0,"ProductName":null,"ContentBrief":"麻烦客服帮忙报个价谢谢","IsPraise":true,"SharedTimeStr":"2017-01-10 21:25:38","Images":["http://f1.myappcc.com/zfs/7E1/1010/VLW/010212405744CABHXXHKPP.jpg"],"Id":68697,"UserId":74886,"OrderId":0,"ProductId":54590,"SharedTime":"2017-01-10 21:25:38","Content":"麻烦客服帮忙报个价谢谢","ReviewTimes":0,"ViewTimes":464,"PraiseTimes":1,"MomentType":1,"IsPrivate":0,"IsOffer":1,"OfferTime":"2017-01-10 21:29:14","Tag":"上装","ImageNum":1,"Image1":"http://f1.myappcc.com/zfs/7E1/1010/VLW/010212405744CABHXXHKPP.jpg","Image2":"","Image3":"","Image4":"","Image5":"","Image6":"","Image7":"","Image8":"","Image9":"","RegApp":"","PkValue":68697},{"MomentTypeDesc":"定制发图","UserName":null,"SharedDay":null,"SharedMonth":0,"ReviewSum":0,"FriendReview":null,"FriendReviewTop3":null,"UserImg":null,"UserNickName":null,"ProductPrice":0,"ProductName":null,"ContentBrief":"","IsPraise":false,"SharedTimeStr":"2016-12-18 21:11:41","Images":["http://f1.myappcc.com/zfs/7E0/1353/VLW/353211356539CABGYRCSJA.jpg"],"Id":60558,"UserId":74886,"OrderId":0,"ProductId":48103,"SharedTime":"2016-12-18 21:11:41","Content":"","ReviewTimes":0,"ViewTimes":72,"PraiseTimes":0,"MomentType":1,"IsPrivate":0,"IsOffer":1,"OfferTime":"2016-12-18 21:18:42","Tag":"上装","ImageNum":1,"Image1":"http://f1.myappcc.com/zfs/7E0/1353/VLW/353211356539CABGYRCSJA.jpg","Image2":"","Image3":"","Image4":"","Image5":"","Image6":"","Image7":"","Image8":"","Image9":"","RegApp":"","PkValue":60558}],"PageCount":1,"PageRecordStartNum":1,"PageRocordEndNum":2},"Users":[{"Roles":null,"OrderCount":0,"DisplayName":"我是狗狗(13145975793)","HideName":"131******93","Id":74886,"Username":"13145975793","Password":"78D404C207BEF4ABAB92538E2496EF0B2CEC7769FD37979EE380929100A9FE6B","NickName":"我是狗狗","UserImg":"http://f1.myappcc.com/zfs/7E1/1023/VLW/023214557911CABHXCBKLB.jpg","UserIdentity":1,"PassErrCount":0,"IsLockPassword":0,"LogonCount":613,"OnlineTimes":0,"Mobile":"13145975793","Email":"","IsEnabled":1,"IsDelete":0,"LastLogonIp":"113.91.213.230","LastLogonTime":"2017-02-21 16:02:15","ActSource":"","RegSource":"null","RegVer":"3.0.58","LastVer":"","InvitedCode":"4AY","InviteCode":"7XUK","InviteTimes":0,"CreatorDate":"2016-11-19 16:16:07","CreatorUsername":"APP","Rmk":"","UpdateDate":"2016-11-19 16:16:07","UpdateUsername":"APP","RegIp":"113.91.215.145","RegApp":"Mozilla","PId":29122,"PpId":0,"RegDeviceInfo":"HUAWEI;HUAWEI P8max;6.0;","LastLoginDeviceInfo":"HUAWEI;HUAWEI P8max;6.0;","Latitude":0,"Longitude":0,"Gender":2,"PkValue":74886}]}
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

    public static class DataBean  implements Serializable{
        /**
         * Pagedata : {"CurrentPage":1,"PageSize":8,"Total":2,"Rows":[{"MomentTypeDesc":"定制发图","UserName":null,"SharedDay":null,"SharedMonth":0,"ReviewSum":0,"FriendReview":null,"FriendReviewTop3":null,"UserImg":null,"UserNickName":null,"ProductPrice":0,"ProductName":null,"ContentBrief":"麻烦客服帮忙报个价谢谢","IsPraise":true,"SharedTimeStr":"2017-01-10 21:25:38","Images":["http://f1.myappcc.com/zfs/7E1/1010/VLW/010212405744CABHXXHKPP.jpg"],"Id":68697,"UserId":74886,"OrderId":0,"ProductId":54590,"SharedTime":"2017-01-10 21:25:38","Content":"麻烦客服帮忙报个价谢谢","ReviewTimes":0,"ViewTimes":464,"PraiseTimes":1,"MomentType":1,"IsPrivate":0,"IsOffer":1,"OfferTime":"2017-01-10 21:29:14","Tag":"上装","ImageNum":1,"Image1":"http://f1.myappcc.com/zfs/7E1/1010/VLW/010212405744CABHXXHKPP.jpg","Image2":"","Image3":"","Image4":"","Image5":"","Image6":"","Image7":"","Image8":"","Image9":"","RegApp":"","PkValue":68697},{"MomentTypeDesc":"定制发图","UserName":null,"SharedDay":null,"SharedMonth":0,"ReviewSum":0,"FriendReview":null,"FriendReviewTop3":null,"UserImg":null,"UserNickName":null,"ProductPrice":0,"ProductName":null,"ContentBrief":"","IsPraise":false,"SharedTimeStr":"2016-12-18 21:11:41","Images":["http://f1.myappcc.com/zfs/7E0/1353/VLW/353211356539CABGYRCSJA.jpg"],"Id":60558,"UserId":74886,"OrderId":0,"ProductId":48103,"SharedTime":"2016-12-18 21:11:41","Content":"","ReviewTimes":0,"ViewTimes":72,"PraiseTimes":0,"MomentType":1,"IsPrivate":0,"IsOffer":1,"OfferTime":"2016-12-18 21:18:42","Tag":"上装","ImageNum":1,"Image1":"http://f1.myappcc.com/zfs/7E0/1353/VLW/353211356539CABGYRCSJA.jpg","Image2":"","Image3":"","Image4":"","Image5":"","Image6":"","Image7":"","Image8":"","Image9":"","RegApp":"","PkValue":60558}],"PageCount":1,"PageRecordStartNum":1,"PageRocordEndNum":2}
         * Users : [{"Roles":null,"OrderCount":0,"DisplayName":"我是狗狗(13145975793)","HideName":"131******93","Id":74886,"Username":"13145975793","Password":"78D404C207BEF4ABAB92538E2496EF0B2CEC7769FD37979EE380929100A9FE6B","NickName":"我是狗狗","UserImg":"http://f1.myappcc.com/zfs/7E1/1023/VLW/023214557911CABHXCBKLB.jpg","UserIdentity":1,"PassErrCount":0,"IsLockPassword":0,"LogonCount":613,"OnlineTimes":0,"Mobile":"13145975793","Email":"","IsEnabled":1,"IsDelete":0,"LastLogonIp":"113.91.213.230","LastLogonTime":"2017-02-21 16:02:15","ActSource":"","RegSource":"null","RegVer":"3.0.58","LastVer":"","InvitedCode":"4AY","InviteCode":"7XUK","InviteTimes":0,"CreatorDate":"2016-11-19 16:16:07","CreatorUsername":"APP","Rmk":"","UpdateDate":"2016-11-19 16:16:07","UpdateUsername":"APP","RegIp":"113.91.215.145","RegApp":"Mozilla","PId":29122,"PpId":0,"RegDeviceInfo":"HUAWEI;HUAWEI P8max;6.0;","LastLoginDeviceInfo":"HUAWEI;HUAWEI P8max;6.0;","Latitude":0,"Longitude":0,"Gender":2,"PkValue":74886}]
         */

        private PagedataBean Pagedata;
        private List<UsersBean> Users;

        public PagedataBean getPagedata() {
            return Pagedata;
        }

        public void setPagedata(PagedataBean Pagedata) {
            this.Pagedata = Pagedata;
        }

        public List<UsersBean> getUsers() {
            return Users;
        }

        public void setUsers(List<UsersBean> Users) {
            this.Users = Users;
        }

        public static class PagedataBean  implements Serializable{
            /**
             * CurrentPage : 1
             * PageSize : 8
             * Total : 2
             * Rows : [{"MomentTypeDesc":"定制发图","UserName":null,"SharedDay":null,"SharedMonth":0,"ReviewSum":0,"FriendReview":null,"FriendReviewTop3":null,"UserImg":null,"UserNickName":null,"ProductPrice":0,"ProductName":null,"ContentBrief":"麻烦客服帮忙报个价谢谢","IsPraise":true,"SharedTimeStr":"2017-01-10 21:25:38","Images":["http://f1.myappcc.com/zfs/7E1/1010/VLW/010212405744CABHXXHKPP.jpg"],"Id":68697,"UserId":74886,"OrderId":0,"ProductId":54590,"SharedTime":"2017-01-10 21:25:38","Content":"麻烦客服帮忙报个价谢谢","ReviewTimes":0,"ViewTimes":464,"PraiseTimes":1,"MomentType":1,"IsPrivate":0,"IsOffer":1,"OfferTime":"2017-01-10 21:29:14","Tag":"上装","ImageNum":1,"Image1":"http://f1.myappcc.com/zfs/7E1/1010/VLW/010212405744CABHXXHKPP.jpg","Image2":"","Image3":"","Image4":"","Image5":"","Image6":"","Image7":"","Image8":"","Image9":"","RegApp":"","PkValue":68697},{"MomentTypeDesc":"定制发图","UserName":null,"SharedDay":null,"SharedMonth":0,"ReviewSum":0,"FriendReview":null,"FriendReviewTop3":null,"UserImg":null,"UserNickName":null,"ProductPrice":0,"ProductName":null,"ContentBrief":"","IsPraise":false,"SharedTimeStr":"2016-12-18 21:11:41","Images":["http://f1.myappcc.com/zfs/7E0/1353/VLW/353211356539CABGYRCSJA.jpg"],"Id":60558,"UserId":74886,"OrderId":0,"ProductId":48103,"SharedTime":"2016-12-18 21:11:41","Content":"","ReviewTimes":0,"ViewTimes":72,"PraiseTimes":0,"MomentType":1,"IsPrivate":0,"IsOffer":1,"OfferTime":"2016-12-18 21:18:42","Tag":"上装","ImageNum":1,"Image1":"http://f1.myappcc.com/zfs/7E0/1353/VLW/353211356539CABGYRCSJA.jpg","Image2":"","Image3":"","Image4":"","Image5":"","Image6":"","Image7":"","Image8":"","Image9":"","RegApp":"","PkValue":60558}]
             * PageCount : 1
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

            public static class RowsBean  implements Serializable{
                @Override
                public String toString() {
                    return "RowsBean{" +
                            "MomentTypeDesc='" + MomentTypeDesc + '\'' +
                            ", UserName=" + UserName +
                            ", SharedDay=" + SharedDay +
                            ", SharedMonth=" + SharedMonth +
                            ", ReviewSum=" + ReviewSum +
                            ", FriendReview=" + FriendReview +
                            ", FriendReviewTop3=" + FriendReviewTop3 +
                            ", UserImg=" + UserImg +
                            ", UserNickName=" + UserNickName +
                            ", ProductPrice=" + ProductPrice +
                            ", ProductName=" + ProductName +
                            ", ContentBrief='" + ContentBrief + '\'' +
                            ", IsPraise=" + IsPraise +
                            ", SharedTimeStr='" + SharedTimeStr + '\'' +
                            ", Id=" + Id +
                            ", UserId=" + UserId +
                            ", OrderId=" + OrderId +
                            ", ProductId=" + ProductId +
                            ", SharedTime='" + SharedTime + '\'' +
                            ", Content='" + Content + '\'' +
                            ", ReviewTimes=" + ReviewTimes +
                            ", ViewTimes=" + ViewTimes +
                            ", PraiseTimes=" + PraiseTimes +
                            ", MomentType=" + MomentType +
                            ", IsPrivate=" + IsPrivate +
                            ", IsOffer=" + IsOffer +
                            ", OfferTime='" + OfferTime + '\'' +
                            ", Tag='" + Tag + '\'' +
                            ", ImageNum=" + ImageNum +
                            ", Image1='" + Image1 + '\'' +
                            ", Image2='" + Image2 + '\'' +
                            ", Image3='" + Image3 + '\'' +
                            ", Image4='" + Image4 + '\'' +
                            ", Image5='" + Image5 + '\'' +
                            ", Image6='" + Image6 + '\'' +
                            ", Image7='" + Image7 + '\'' +
                            ", Image8='" + Image8 + '\'' +
                            ", Image9='" + Image9 + '\'' +
                            ", RegApp='" + RegApp + '\'' +
                            ", PkValue=" + PkValue +
                            ", Images=" + Images +
                            '}';
                }

                /**
                 * MomentTypeDesc : 定制发图
                 * UserName : null
                 * SharedDay : null
                 * SharedMonth : 0
                 * ReviewSum : 0
                 * FriendReview : null
                 * FriendReviewTop3 : null
                 * UserImg : null
                 * UserNickName : null
                 * ProductPrice : 0.0
                 * ProductName : null
                 * ContentBrief : 麻烦客服帮忙报个价谢谢
                 * IsPraise : true
                 * SharedTimeStr : 2017-01-10 21:25:38
                 * Images : ["http://f1.myappcc.com/zfs/7E1/1010/VLW/010212405744CABHXXHKPP.jpg"]
                 * Id : 68697
                 * UserId : 74886
                 * OrderId : 0
                 * ProductId : 54590
                 * SharedTime : 2017-01-10 21:25:38
                 * Content : 麻烦客服帮忙报个价谢谢
                 * ReviewTimes : 0
                 * ViewTimes : 464
                 * PraiseTimes : 1
                 * MomentType : 1
                 * IsPrivate : 0
                 * IsOffer : 1
                 * OfferTime : 2017-01-10 21:29:14
                 * Tag : 上装
                 * ImageNum : 1
                 * Image1 : http://f1.myappcc.com/zfs/7E1/1010/VLW/010212405744CABHXXHKPP.jpg
                 * Image2 :
                 * Image3 :
                 * Image4 :
                 * Image5 :
                 * Image6 :
                 * Image7 :
                 * Image8 :
                 * Image9 :
                 * RegApp :
                 * PkValue : 68697
                 */

                private String MomentTypeDesc;
                private Object UserName;
                private Object SharedDay;
                private int SharedMonth;
                private int ReviewSum;
                private Object FriendReview;
                private Object FriendReviewTop3;
                private Object UserImg;
                private Object UserNickName;
                private double ProductPrice;
                private Object ProductName;
                private String ContentBrief;
                private boolean IsPraise;
                private String SharedTimeStr;
                private int Id;
                private int UserId;
                private int OrderId;
                private int ProductId;
                private String SharedTime;
                private String Content;
                private int ReviewTimes;
                private int ViewTimes;
                private int PraiseTimes;
                private int MomentType;
                private int IsPrivate;
                private int IsOffer;
                private String OfferTime;
                private String Tag;
                private int ImageNum;
                private String Image1;
                private String Image2;
                private String Image3;
                private String Image4;
                private String Image5;
                private String Image6;
                private String Image7;
                private String Image8;
                private String Image9;
                private String RegApp;
                private int PkValue;
                private List<String> Images;

                public String getMomentTypeDesc() {
                    return MomentTypeDesc;
                }

                public void setMomentTypeDesc(String MomentTypeDesc) {
                    this.MomentTypeDesc = MomentTypeDesc;
                }

                public Object getUserName() {
                    return UserName;
                }

                public void setUserName(Object UserName) {
                    this.UserName = UserName;
                }

                public Object getSharedDay() {
                    return SharedDay;
                }

                public void setSharedDay(Object SharedDay) {
                    this.SharedDay = SharedDay;
                }

                public int getSharedMonth() {
                    return SharedMonth;
                }

                public void setSharedMonth(int SharedMonth) {
                    this.SharedMonth = SharedMonth;
                }

                public int getReviewSum() {
                    return ReviewSum;
                }

                public void setReviewSum(int ReviewSum) {
                    this.ReviewSum = ReviewSum;
                }

                public Object getFriendReview() {
                    return FriendReview;
                }

                public void setFriendReview(Object FriendReview) {
                    this.FriendReview = FriendReview;
                }

                public Object getFriendReviewTop3() {
                    return FriendReviewTop3;
                }

                public void setFriendReviewTop3(Object FriendReviewTop3) {
                    this.FriendReviewTop3 = FriendReviewTop3;
                }

                public Object getUserImg() {
                    return UserImg;
                }

                public void setUserImg(Object UserImg) {
                    this.UserImg = UserImg;
                }

                public Object getUserNickName() {
                    return UserNickName;
                }

                public void setUserNickName(Object UserNickName) {
                    this.UserNickName = UserNickName;
                }

                public double getProductPrice() {
                    return ProductPrice;
                }

                public void setProductPrice(double ProductPrice) {
                    this.ProductPrice = ProductPrice;
                }

                public Object getProductName() {
                    return ProductName;
                }

                public void setProductName(Object ProductName) {
                    this.ProductName = ProductName;
                }

                public String getContentBrief() {
                    return ContentBrief;
                }

                public void setContentBrief(String ContentBrief) {
                    this.ContentBrief = ContentBrief;
                }

                public boolean isIsPraise() {
                    return IsPraise;
                }

                public void setIsPraise(boolean IsPraise) {
                    this.IsPraise = IsPraise;
                }

                public String getSharedTimeStr() {
                    return SharedTimeStr;
                }

                public void setSharedTimeStr(String SharedTimeStr) {
                    this.SharedTimeStr = SharedTimeStr;
                }

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

                public String getSharedTime() {
                    return SharedTime;
                }

                public void setSharedTime(String SharedTime) {
                    this.SharedTime = SharedTime;
                }

                public String getContent() {
                    return Content;
                }

                public void setContent(String Content) {
                    this.Content = Content;
                }

                public int getReviewTimes() {
                    return ReviewTimes;
                }

                public void setReviewTimes(int ReviewTimes) {
                    this.ReviewTimes = ReviewTimes;
                }

                public int getViewTimes() {
                    return ViewTimes;
                }

                public void setViewTimes(int ViewTimes) {
                    this.ViewTimes = ViewTimes;
                }

                public int getPraiseTimes() {
                    return PraiseTimes;
                }

                public void setPraiseTimes(int PraiseTimes) {
                    this.PraiseTimes = PraiseTimes;
                }

                public int getMomentType() {
                    return MomentType;
                }

                public void setMomentType(int MomentType) {
                    this.MomentType = MomentType;
                }

                public int getIsPrivate() {
                    return IsPrivate;
                }

                public void setIsPrivate(int IsPrivate) {
                    this.IsPrivate = IsPrivate;
                }

                public int getIsOffer() {
                    return IsOffer;
                }

                public void setIsOffer(int IsOffer) {
                    this.IsOffer = IsOffer;
                }

                public String getOfferTime() {
                    return OfferTime;
                }

                public void setOfferTime(String OfferTime) {
                    this.OfferTime = OfferTime;
                }

                public String getTag() {
                    return Tag;
                }

                public void setTag(String Tag) {
                    this.Tag = Tag;
                }

                public int getImageNum() {
                    return ImageNum;
                }

                public void setImageNum(int ImageNum) {
                    this.ImageNum = ImageNum;
                }

                public String getImage1() {
                    return Image1;
                }

                public void setImage1(String Image1) {
                    this.Image1 = Image1;
                }

                public String getImage2() {
                    return Image2;
                }

                public void setImage2(String Image2) {
                    this.Image2 = Image2;
                }

                public String getImage3() {
                    return Image3;
                }

                public void setImage3(String Image3) {
                    this.Image3 = Image3;
                }

                public String getImage4() {
                    return Image4;
                }

                public void setImage4(String Image4) {
                    this.Image4 = Image4;
                }

                public String getImage5() {
                    return Image5;
                }

                public void setImage5(String Image5) {
                    this.Image5 = Image5;
                }

                public String getImage6() {
                    return Image6;
                }

                public void setImage6(String Image6) {
                    this.Image6 = Image6;
                }

                public String getImage7() {
                    return Image7;
                }

                public void setImage7(String Image7) {
                    this.Image7 = Image7;
                }

                public String getImage8() {
                    return Image8;
                }

                public void setImage8(String Image8) {
                    this.Image8 = Image8;
                }

                public String getImage9() {
                    return Image9;
                }

                public void setImage9(String Image9) {
                    this.Image9 = Image9;
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

                public List<String> getImages() {
                    return Images;
                }

                public void setImages(List<String> Images) {
                    this.Images = Images;
                }
            }
        }

        public static class UsersBean {
            /**
             * Roles : null
             * OrderCount : 0
             * DisplayName : 我是狗狗(13145975793)
             * HideName : 131******93
             * Id : 74886
             * Username : 13145975793
             * Password : 78D404C207BEF4ABAB92538E2496EF0B2CEC7769FD37979EE380929100A9FE6B
             * NickName : 我是狗狗
             * UserImg : http://f1.myappcc.com/zfs/7E1/1023/VLW/023214557911CABHXCBKLB.jpg
             * UserIdentity : 1
             * PassErrCount : 0
             * IsLockPassword : 0
             * LogonCount : 613
             * OnlineTimes : 0
             * Mobile : 13145975793
             * Email :
             * IsEnabled : 1
             * IsDelete : 0
             * LastLogonIp : 113.91.213.230
             * LastLogonTime : 2017-02-21 16:02:15
             * ActSource :
             * RegSource : null
             * RegVer : 3.0.58
             * LastVer :
             * InvitedCode : 4AY
             * InviteCode : 7XUK
             * InviteTimes : 0
             * CreatorDate : 2016-11-19 16:16:07
             * CreatorUsername : APP
             * Rmk :
             * UpdateDate : 2016-11-19 16:16:07
             * UpdateUsername : APP
             * RegIp : 113.91.215.145
             * RegApp : Mozilla
             * PId : 29122
             * PpId : 0
             * RegDeviceInfo : HUAWEI;HUAWEI P8max;6.0;
             * LastLoginDeviceInfo : HUAWEI;HUAWEI P8max;6.0;
             * Latitude : 0.0
             * Longitude : 0.0
             * Gender : 2
             * PkValue : 74886
             */

            private Object Roles;
            private int OrderCount;
            private String DisplayName;
            private String HideName;
            private int Id;
            private String Username;
            private String Password;
            private String NickName;
            private String UserImg;
            private int UserIdentity;
            private int PassErrCount;
            private int IsLockPassword;
            private int LogonCount;
            private int OnlineTimes;
            private String Mobile;
            private String Email;
            private int IsEnabled;
            private int IsDelete;
            private String LastLogonIp;
            private String LastLogonTime;
            private String ActSource;
            private String RegSource;
            private String RegVer;
            private String LastVer;
            private String InvitedCode;
            private String InviteCode;
            private int InviteTimes;
            private String CreatorDate;
            private String CreatorUsername;
            private String Rmk;
            private String UpdateDate;
            private String UpdateUsername;
            private String RegIp;
            private String RegApp;
            private int PId;
            private int PpId;
            private String RegDeviceInfo;
            private String LastLoginDeviceInfo;
            private double Latitude;
            private double Longitude;
            private int Gender;
            private int PkValue;

            public Object getRoles() {
                return Roles;
            }

            public void setRoles(Object Roles) {
                this.Roles = Roles;
            }

            public int getOrderCount() {
                return OrderCount;
            }

            public void setOrderCount(int OrderCount) {
                this.OrderCount = OrderCount;
            }

            public String getDisplayName() {
                return DisplayName;
            }

            public void setDisplayName(String DisplayName) {
                this.DisplayName = DisplayName;
            }

            public String getHideName() {
                return HideName;
            }

            public void setHideName(String HideName) {
                this.HideName = HideName;
            }

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getUsername() {
                return Username;
            }

            public void setUsername(String Username) {
                this.Username = Username;
            }

            public String getPassword() {
                return Password;
            }

            public void setPassword(String Password) {
                this.Password = Password;
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

            public int getUserIdentity() {
                return UserIdentity;
            }

            public void setUserIdentity(int UserIdentity) {
                this.UserIdentity = UserIdentity;
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

            public int getLogonCount() {
                return LogonCount;
            }

            public void setLogonCount(int LogonCount) {
                this.LogonCount = LogonCount;
            }

            public int getOnlineTimes() {
                return OnlineTimes;
            }

            public void setOnlineTimes(int OnlineTimes) {
                this.OnlineTimes = OnlineTimes;
            }

            public String getMobile() {
                return Mobile;
            }

            public void setMobile(String Mobile) {
                this.Mobile = Mobile;
            }

            public String getEmail() {
                return Email;
            }

            public void setEmail(String Email) {
                this.Email = Email;
            }

            public int getIsEnabled() {
                return IsEnabled;
            }

            public void setIsEnabled(int IsEnabled) {
                this.IsEnabled = IsEnabled;
            }

            public int getIsDelete() {
                return IsDelete;
            }

            public void setIsDelete(int IsDelete) {
                this.IsDelete = IsDelete;
            }

            public String getLastLogonIp() {
                return LastLogonIp;
            }

            public void setLastLogonIp(String LastLogonIp) {
                this.LastLogonIp = LastLogonIp;
            }

            public String getLastLogonTime() {
                return LastLogonTime;
            }

            public void setLastLogonTime(String LastLogonTime) {
                this.LastLogonTime = LastLogonTime;
            }

            public String getActSource() {
                return ActSource;
            }

            public void setActSource(String ActSource) {
                this.ActSource = ActSource;
            }

            public String getRegSource() {
                return RegSource;
            }

            public void setRegSource(String RegSource) {
                this.RegSource = RegSource;
            }

            public String getRegVer() {
                return RegVer;
            }

            public void setRegVer(String RegVer) {
                this.RegVer = RegVer;
            }

            public String getLastVer() {
                return LastVer;
            }

            public void setLastVer(String LastVer) {
                this.LastVer = LastVer;
            }

            public String getInvitedCode() {
                return InvitedCode;
            }

            public void setInvitedCode(String InvitedCode) {
                this.InvitedCode = InvitedCode;
            }

            public String getInviteCode() {
                return InviteCode;
            }

            public void setInviteCode(String InviteCode) {
                this.InviteCode = InviteCode;
            }

            public int getInviteTimes() {
                return InviteTimes;
            }

            public void setInviteTimes(int InviteTimes) {
                this.InviteTimes = InviteTimes;
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

            public String getRmk() {
                return Rmk;
            }

            public void setRmk(String Rmk) {
                this.Rmk = Rmk;
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

            public String getRegIp() {
                return RegIp;
            }

            public void setRegIp(String RegIp) {
                this.RegIp = RegIp;
            }

            public String getRegApp() {
                return RegApp;
            }

            public void setRegApp(String RegApp) {
                this.RegApp = RegApp;
            }

            public int getPId() {
                return PId;
            }

            public void setPId(int PId) {
                this.PId = PId;
            }

            public int getPpId() {
                return PpId;
            }

            public void setPpId(int PpId) {
                this.PpId = PpId;
            }

            public String getRegDeviceInfo() {
                return RegDeviceInfo;
            }

            public void setRegDeviceInfo(String RegDeviceInfo) {
                this.RegDeviceInfo = RegDeviceInfo;
            }

            public String getLastLoginDeviceInfo() {
                return LastLoginDeviceInfo;
            }

            public void setLastLoginDeviceInfo(String LastLoginDeviceInfo) {
                this.LastLoginDeviceInfo = LastLoginDeviceInfo;
            }

            public double getLatitude() {
                return Latitude;
            }

            public void setLatitude(double Latitude) {
                this.Latitude = Latitude;
            }

            public double getLongitude() {
                return Longitude;
            }

            public void setLongitude(double Longitude) {
                this.Longitude = Longitude;
            }

            public int getGender() {
                return Gender;
            }

            public void setGender(int Gender) {
                this.Gender = Gender;
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
