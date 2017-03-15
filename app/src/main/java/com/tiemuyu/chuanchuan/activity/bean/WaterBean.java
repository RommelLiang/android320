package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/3/14.
 */

public class WaterBean {

    /**
     * Code : 1
     * Msg : OK
     * Data : {"Data":[{"id":1140,"topicsid":28,"productid":66263,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1072/QFI/072163525045CABHCSKMQV.png","fenlei":"陈晓","productname":"女装2017年陈晓同...","price":1068,"createtime":"2017-03-14 10:18:13","status":0},{"id":1139,"topicsid":28,"productid":66262,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1072/QFI/072163435451CABHSUDSMP.jpg","fenlei":"陈晓","productname":"男装2017年陈晓同...","price":540,"createtime":"2017-03-14 10:17:51","status":0},{"id":1138,"topicsid":28,"productid":66261,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1072/QFI/072163356123CABHHYVCQX.jpg","fenlei":"陈晓","productname":"男装2017年陈晓同...","price":540,"createtime":"2017-03-14 10:15:42","status":0},{"id":1137,"topicsid":28,"productid":66260,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1072/QFI/072163252467CABHSSKLRQ.jpg","fenlei":"陈晓","productname":"男装2017年陈晓同...","price":852,"createtime":"2017-03-14 10:15:21","status":0},{"id":1136,"topicsid":28,"productid":66258,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1072/QFI/072163144576CABHSQMXIL.jpg","fenlei":"外套","productname":"女装2017年袁姗姗...","price":600,"createtime":"2017-03-14 10:14:58","status":0},{"id":1135,"topicsid":28,"productid":66257,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1072/QFI/072163050248CABHGIQJQI.jpg","fenlei":"大衣","productname":"女装2017年袁姗姗...","price":852,"createtime":"2017-03-14 10:14:20","status":0},{"id":1134,"topicsid":28,"productid":66256,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1072/QFI/072163007357CABHIYTBHU.jpg","fenlei":"外套","productname":"女装2017年袁姗姗...","price":540,"createtime":"2017-03-14 10:13:35","status":0},{"id":1133,"topicsid":28,"productid":66255,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1072/QFI/072162931607CABHJVPXDG.jpg","fenlei":"大衣","productname":"女装2017年袁姗姗...","price":852,"createtime":"2017-03-14 10:13:17","status":0},{"id":1132,"topicsid":28,"productid":24827,"productmainpic":"http://f1.myappcc.com/zfs/7E0/1253/XXY/253235508876CABGDVOBDU.jpg","fenlei":"衬衣","productname":"女装2016年明星同...","price":390,"createtime":"2017-03-14 10:13:00","status":0},{"id":1131,"topicsid":28,"productid":21463,"productmainpic":"http://f1.myappcc.com/zfs/7E0/1244/NHP/244135221445CABGFOUOMB.jpg","fenlei":"套装","productname":"女装2016年明星同...","price":1430,"createtime":"2017-03-14 10:12:42","status":0}],"Topicss":{"id":28,"name":"《云巅之上》","url":"##","img":"http://f1.myappcc.com/zfs/7E1/1073/LGU/073114031432CABHOBUBPS.jpg","createtime":"2017-03-13 19:31:34","miaoshu":"看袁珊珊挑战百变造型，演绎浪漫奇幻的\u201c戏中戏\u201d。","guanjianzi":"外套,衬衣,裙子","morenwenben":"#","status":1,"statusname":"瀑布流形式","GuanJianList":["外套","衬衣","裙子"],"bigimg":"http://f1.myappcc.com/zfs/7E1/1073/LGU/073114025729CABHFENWVP.jpg","SumLook":0}}
     */

    private int Code;
    private String Msg;
    private DataBeanX Data;

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

    public DataBeanX getData() {
        return Data;
    }

    public void setData(DataBeanX Data) {
        this.Data = Data;
    }

    public static class DataBeanX {
        /**
         * Data : [{"id":1140,"topicsid":28,"productid":66263,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1072/QFI/072163525045CABHCSKMQV.png","fenlei":"陈晓","productname":"女装2017年陈晓同...","price":1068,"createtime":"2017-03-14 10:18:13","status":0},{"id":1139,"topicsid":28,"productid":66262,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1072/QFI/072163435451CABHSUDSMP.jpg","fenlei":"陈晓","productname":"男装2017年陈晓同...","price":540,"createtime":"2017-03-14 10:17:51","status":0},{"id":1138,"topicsid":28,"productid":66261,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1072/QFI/072163356123CABHHYVCQX.jpg","fenlei":"陈晓","productname":"男装2017年陈晓同...","price":540,"createtime":"2017-03-14 10:15:42","status":0},{"id":1137,"topicsid":28,"productid":66260,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1072/QFI/072163252467CABHSSKLRQ.jpg","fenlei":"陈晓","productname":"男装2017年陈晓同...","price":852,"createtime":"2017-03-14 10:15:21","status":0},{"id":1136,"topicsid":28,"productid":66258,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1072/QFI/072163144576CABHSQMXIL.jpg","fenlei":"外套","productname":"女装2017年袁姗姗...","price":600,"createtime":"2017-03-14 10:14:58","status":0},{"id":1135,"topicsid":28,"productid":66257,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1072/QFI/072163050248CABHGIQJQI.jpg","fenlei":"大衣","productname":"女装2017年袁姗姗...","price":852,"createtime":"2017-03-14 10:14:20","status":0},{"id":1134,"topicsid":28,"productid":66256,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1072/QFI/072163007357CABHIYTBHU.jpg","fenlei":"外套","productname":"女装2017年袁姗姗...","price":540,"createtime":"2017-03-14 10:13:35","status":0},{"id":1133,"topicsid":28,"productid":66255,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1072/QFI/072162931607CABHJVPXDG.jpg","fenlei":"大衣","productname":"女装2017年袁姗姗...","price":852,"createtime":"2017-03-14 10:13:17","status":0},{"id":1132,"topicsid":28,"productid":24827,"productmainpic":"http://f1.myappcc.com/zfs/7E0/1253/XXY/253235508876CABGDVOBDU.jpg","fenlei":"衬衣","productname":"女装2016年明星同...","price":390,"createtime":"2017-03-14 10:13:00","status":0},{"id":1131,"topicsid":28,"productid":21463,"productmainpic":"http://f1.myappcc.com/zfs/7E0/1244/NHP/244135221445CABGFOUOMB.jpg","fenlei":"套装","productname":"女装2016年明星同...","price":1430,"createtime":"2017-03-14 10:12:42","status":0}]
         * Topicss : {"id":28,"name":"《云巅之上》","url":"##","img":"http://f1.myappcc.com/zfs/7E1/1073/LGU/073114031432CABHOBUBPS.jpg","createtime":"2017-03-13 19:31:34","miaoshu":"看袁珊珊挑战百变造型，演绎浪漫奇幻的\u201c戏中戏\u201d。","guanjianzi":"外套,衬衣,裙子","morenwenben":"#","status":1,"statusname":"瀑布流形式","GuanJianList":["外套","衬衣","裙子"],"bigimg":"http://f1.myappcc.com/zfs/7E1/1073/LGU/073114025729CABHFENWVP.jpg","SumLook":0}
         */

        private TopicssBean Topicss;
        private List<DataBean> Data;

        public TopicssBean getTopicss() {
            return Topicss;
        }

        public void setTopicss(TopicssBean Topicss) {
            this.Topicss = Topicss;
        }

        public List<DataBean> getData() {
            return Data;
        }

        public void setData(List<DataBean> Data) {
            this.Data = Data;
        }

        public static class TopicssBean {
            /**
             * id : 28
             * name : 《云巅之上》
             * url : ##
             * img : http://f1.myappcc.com/zfs/7E1/1073/LGU/073114031432CABHOBUBPS.jpg
             * createtime : 2017-03-13 19:31:34
             * miaoshu : 看袁珊珊挑战百变造型，演绎浪漫奇幻的“戏中戏”。
             * guanjianzi : 外套,衬衣,裙子
             * morenwenben : #
             * status : 1
             * statusname : 瀑布流形式
             * GuanJianList : ["外套","衬衣","裙子"]
             * bigimg : http://f1.myappcc.com/zfs/7E1/1073/LGU/073114025729CABHFENWVP.jpg
             * SumLook : 0
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

        public static class DataBean {
            /**
             * id : 1140
             * topicsid : 28
             * productid : 66263
             * productmainpic : http://f1.myappcc.com/zfs/7E1/1072/QFI/072163525045CABHCSKMQV.png
             * fenlei : 陈晓
             * productname : 女装2017年陈晓同...
             * price : 1068
             * createtime : 2017-03-14 10:18:13
             * status : 0
             */

            private int id;
            private int topicsid;
            private int productid;
            private String productmainpic;
            private String fenlei;
            private String productname;
            private int price;
            private String createtime;
            private int status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTopicsid() {
                return topicsid;
            }

            public void setTopicsid(int topicsid) {
                this.topicsid = topicsid;
            }

            public int getProductid() {
                return productid;
            }

            public void setProductid(int productid) {
                this.productid = productid;
            }

            public String getProductmainpic() {
                return productmainpic;
            }

            public void setProductmainpic(String productmainpic) {
                this.productmainpic = productmainpic;
            }

            public String getFenlei() {
                return fenlei;
            }

            public void setFenlei(String fenlei) {
                this.fenlei = fenlei;
            }

            public String getProductname() {
                return productname;
            }

            public void setProductname(String productname) {
                this.productname = productname;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
