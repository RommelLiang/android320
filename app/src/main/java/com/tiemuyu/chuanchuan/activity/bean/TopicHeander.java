package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/3/14.
 */

public class TopicHeander {

    /**
     * Code : 1
     * Msg : OK
     * Data : {"Data":[],"Topicss":{"id":14,"name":"小香风","url":"#","img":"http://f1.myappcc.com/zfs/7E0/1364/SCJ/364182711370CABGQQRXJM.jpg","createtime":"2016-12-22 17:32:12","miaoshu":"源自苏格兰手工纺织，明星都爱穿的经典小香，不同的人能穿出不同的风格，无论你怎么搭配它，体现的都是你的时尚语言。","guanjianzi":"张柏芝原版小香编织,羊毛编织软呢,小香编织","morenwenben":"张柏芝原版小香编织","status":0,"statusname":"文章类型","GuanJianList":["张柏芝原版小香编织","羊毛编织软呢","小香编织"],"bigimg":"http://f1.myappcc.com/zfs/7E0/1364/SCJ/364182718917CABGLYCJAD.jpg","SumLook":73532}}
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
         * Data : []
         * Topicss : {"id":14,"name":"小香风","url":"#","img":"http://f1.myappcc.com/zfs/7E0/1364/SCJ/364182711370CABGQQRXJM.jpg","createtime":"2016-12-22 17:32:12","miaoshu":"源自苏格兰手工纺织，明星都爱穿的经典小香，不同的人能穿出不同的风格，无论你怎么搭配它，体现的都是你的时尚语言。","guanjianzi":"张柏芝原版小香编织,羊毛编织软呢,小香编织","morenwenben":"张柏芝原版小香编织","status":0,"statusname":"文章类型","GuanJianList":["张柏芝原版小香编织","羊毛编织软呢","小香编织"],"bigimg":"http://f1.myappcc.com/zfs/7E0/1364/SCJ/364182718917CABGLYCJAD.jpg","SumLook":73532}
         */

        private TopicssBean Topicss;
        private List<?> Data;

        public TopicssBean getTopicss() {
            return Topicss;
        }

        public void setTopicss(TopicssBean Topicss) {
            this.Topicss = Topicss;
        }

        public List<?> getData() {
            return Data;
        }

        public void setData(List<?> Data) {
            this.Data = Data;
        }

        public static class TopicssBean {
            /**
             * id : 14
             * name : 小香风
             * url : #
             * img : http://f1.myappcc.com/zfs/7E0/1364/SCJ/364182711370CABGQQRXJM.jpg
             * createtime : 2016-12-22 17:32:12
             * miaoshu : 源自苏格兰手工纺织，明星都爱穿的经典小香，不同的人能穿出不同的风格，无论你怎么搭配它，体现的都是你的时尚语言。
             * guanjianzi : 张柏芝原版小香编织,羊毛编织软呢,小香编织
             * morenwenben : 张柏芝原版小香编织
             * status : 0
             * statusname : 文章类型
             * GuanJianList : ["张柏芝原版小香编织","羊毛编织软呢","小香编织"]
             * bigimg : http://f1.myappcc.com/zfs/7E0/1364/SCJ/364182718917CABGLYCJAD.jpg
             * SumLook : 73532
             */

            private int id;
            private String name;
            private String url;
            private String img;
            private String createtime;
            private String miaoshu;
            private String guanjianzi;
            private String morenwenben;
            private int status;
            private String statusname;
            private String bigimg;
            private int SumLook;
            private List<String> GuanJianList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getMiaoshu() {
                return miaoshu;
            }

            public void setMiaoshu(String miaoshu) {
                this.miaoshu = miaoshu;
            }

            public String getGuanjianzi() {
                return guanjianzi;
            }

            public void setGuanjianzi(String guanjianzi) {
                this.guanjianzi = guanjianzi;
            }

            public String getMorenwenben() {
                return morenwenben;
            }

            public void setMorenwenben(String morenwenben) {
                this.morenwenben = morenwenben;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getStatusname() {
                return statusname;
            }

            public void setStatusname(String statusname) {
                this.statusname = statusname;
            }

            public String getBigimg() {
                return bigimg;
            }

            public void setBigimg(String bigimg) {
                this.bigimg = bigimg;
            }

            public int getSumLook() {
                return SumLook;
            }

            public void setSumLook(int SumLook) {
                this.SumLook = SumLook;
            }

            public List<String> getGuanJianList() {
                return GuanJianList;
            }

            public void setGuanJianList(List<String> GuanJianList) {
                this.GuanJianList = GuanJianList;
            }
        }
    }
}
