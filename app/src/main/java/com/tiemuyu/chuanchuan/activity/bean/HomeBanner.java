package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/2/23.
 */

public class HomeBanner {

    /**
     * Code : 1
     * Msg : OK
     * Data : [{"AdTypeName":"Banner","Id":82,"CategoryId":0,"Title":"《歌手》造型抢眼 谁是最佳\"POSE\"王","LinkUrl":"http://a1.myappcc.com/cc/Find/RankingList?id=170214183858988","ImgUrl":"http://f1.myappcc.com/zfs/7E1/1046/SCJ/046182423320CABHFMQTQK.jpg","SortId":160,"Rmk":"","CreatorDate":"2017-02-15 18:25:40","CreatorUsername":"18873004874","Status":1,"PkValue":82},{"AdTypeName":"Banner","Id":81,"CategoryId":0,"Title":"求职着装：让衣服为自己加分","LinkUrl":"http://a1.myappcc.com/cc/Find/RankingList?id=170207170840902","ImgUrl":"http://f1.myappcc.com/zfs/7E1/1039/OPQ/039145332473CABHHCKVSW.jpg","SortId":159,"Rmk":"","CreatorDate":"2017-02-08 14:52:54","CreatorUsername":"18776037331","Status":1,"PkValue":81},{"AdTypeName":"Banner","Id":79,"CategoryId":0,"Title":"穿穿就差你这块料！","LinkUrl":"http://a1.myappcc.com/cc/Find/RankingList?id=170116160137798","ImgUrl":"http://f1.myappcc.com/zfs/7E1/1027/KOG/027104022600CABHVKBGUZ.jpg","SortId":155,"Rmk":"","CreatorDate":"2017-01-16 19:08:44","CreatorUsername":"18776037331","Status":1,"PkValue":79},{"AdTypeName":"Banner","Id":74,"CategoryId":0,"Title":"小香风","LinkUrl":"/{appname}/Home/GetWenZhangListIndex?id=14","ImgUrl":"http://f1.myappcc.com/zfs/7E1/1027/KOG/027104033428CABHTYJGUX.jpg","SortId":154,"Rmk":"","CreatorDate":"2016-12-22 21:27:40","CreatorUsername":"13026656328","Status":1,"PkValue":74},{"AdTypeName":"Banner","Id":75,"CategoryId":0,"Title":"2017早春逆袭进行时\u2014春装新look 炫彩迷情","LinkUrl":"/{appname}/Home/GetWenZhangListIndex?id=15","ImgUrl":"http://f1.myappcc.com/zfs/7E1/1027/KOG/027104045131CABHZWFNQW.jpg","SortId":153,"Rmk":"","CreatorDate":"2016-12-23 20:31:22","CreatorUsername":"18776037331","Status":1,"PkValue":75},{"AdTypeName":"Banner","Id":56,"CategoryId":0,"Title":"定制必看！1分钟了解定制流程","LinkUrl":"http://a1.myappcc.com/cc/Find/RankingList?id=160804145137164","ImgUrl":"http://f1.myappcc.com/zfs/7E1/1027/KOG/027104105038CABHZVBWUO.jpg","SortId":137,"Rmk":"","CreatorDate":"2016-08-11 13:36:37","CreatorUsername":"13026656328","Status":1,"PkValue":56}]
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
         * AdTypeName : Banner
         * Id : 82
         * CategoryId : 0
         * Title : 《歌手》造型抢眼 谁是最佳"POSE"王
         * LinkUrl : http://a1.myappcc.com/cc/Find/RankingList?id=170214183858988
         * ImgUrl : http://f1.myappcc.com/zfs/7E1/1046/SCJ/046182423320CABHFMQTQK.jpg
         * SortId : 160
         * Rmk :
         * CreatorDate : 2017-02-15 18:25:40
         * CreatorUsername : 18873004874
         * Status : 1
         * PkValue : 82
         */

        private String AdTypeName;
        private int Id;
        private int CategoryId;
        private String Title;
        private String LinkUrl;
        private String ImgUrl;
        private int SortId;
        private String Rmk;
        private String CreatorDate;
        private String CreatorUsername;
        private int Status;
        private int PkValue;

        public String getAdTypeName() {
            return AdTypeName;
        }

        public void setAdTypeName(String AdTypeName) {
            this.AdTypeName = AdTypeName;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getCategoryId() {
            return CategoryId;
        }

        public void setCategoryId(int CategoryId) {
            this.CategoryId = CategoryId;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getLinkUrl() {
            return LinkUrl;
        }

        public void setLinkUrl(String LinkUrl) {
            this.LinkUrl = LinkUrl;
        }

        public String getImgUrl() {
            return ImgUrl;
        }

        public void setImgUrl(String ImgUrl) {
            this.ImgUrl = ImgUrl;
        }

        public int getSortId() {
            return SortId;
        }

        public void setSortId(int SortId) {
            this.SortId = SortId;
        }

        public String getRmk() {
            return Rmk;
        }

        public void setRmk(String Rmk) {
            this.Rmk = Rmk;
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

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public int getPkValue() {
            return PkValue;
        }

        public void setPkValue(int PkValue) {
            this.PkValue = PkValue;
        }
    }
}
