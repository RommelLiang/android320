package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

/**
 * @项目名： 227androidpay-master
 * @包名： com.tiemuyu.chuanchuan.activity.bean
 * @类描述：
 * @创建人： hr
 * @创建时间： 2017/3/3
 * @version：
 */

public class FindSecondWaterBean {
    /**
     * Code : 1
     * Msg : OK
     * Data : {"Data":[{"id":1033,"topicsid":7,"productid":50780,"productmainpic":"http://f1.myappcc.com/zfs/7E0/1362/XXY/362234637893CABGPQQISG.jpg","fenlei":"","productname":"林俊杰同款高级羊毛混...","price":698,"createtime":"2017-02-21 15:55:24","status":0},{"id":1032,"topicsid":7,"productid":61198,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1048/XXY/048232014217CABHZSGDSY.jpg","fenlei":"","productname":"高级聚酯纤维层叠长袖...","price":732,"createtime":"2017-02-21 15:55:10","status":0},{"id":1031,"topicsid":7,"productid":61303,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1049/OPQ/049140832215CABHEVYVCF.jpg","fenlei":"","productname":"高级聚酯纤维双层长袖...","price":648,"createtime":"2017-02-21 15:54:57","status":0},{"id":1030,"topicsid":7,"productid":61532,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1050/SCJ/050183939945CABHGMKCUD.jpg","fenlei":"","productname":"华晨宇同款普洗/固色...","price":398,"createtime":"2017-02-21 15:54:28","status":0},{"id":1029,"topicsid":7,"productid":61335,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1049/QFI/049163938543CABHZZWPEY.jpg","fenlei":"","productname":"陈赫同款高级针织棉单...","price":261,"createtime":"2017-02-21 15:54:15","status":0},{"id":1028,"topicsid":7,"productid":61539,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1050/SCJ/050190026710CABHZJLSTW.jpg","fenlei":"","productname":"高级梭织棉双层无袖短...","price":440,"createtime":"2017-02-21 15:54:01","status":0},{"id":1027,"topicsid":7,"productid":61555,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1050/UKL/050200641585CABHTAYQXW.jpg","fenlei":"","productname":"薛之谦同款轻工钉珠高...","price":648,"createtime":"2017-02-21 15:53:36","status":0},{"id":1026,"topicsid":7,"productid":61650,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1051/AZA/051101446489CABHDMNCUK.jpg","fenlei":"","productname":"薛之谦同款高级棉麻混...","price":372,"createtime":"2017-02-21 15:53:22","status":0},{"id":1025,"topicsid":7,"productid":61542,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1050/TJK/050190621444CABHVUCITZ.jpg","fenlei":"","productname":"品质蕾丝花面短款吊带...","price":672,"createtime":"2017-02-21 15:53:03","status":0},{"id":1024,"topicsid":7,"productid":60595,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1045/VLW/045211845323CABHXVGNSQ.jpg","fenlei":"","productname":"高级混纺单层拼接长款...","price":384,"createtime":"2017-02-21 15:52:48","status":0}],"Topicss":{"id":7,"name":"今日开团","url":"#","img":"http://f1.myappcc.com/zfs/7E1/1047/KOG/047104558786CABHMYGNKY.jpg","createtime":"2016-11-15 19:29:36","miaoshu":"选衣服好似选饭馆，葛优大爷有名言曰：哪家人多我找哪家！赶快来看看穿友们正在定制的热款吧！最重要的是：以下款式均可联系客服享九折特惠！","guanjianzi":"大衣,裙,外套,衬衣,上衣,羽绒服,裤","morenwenben":"选衣服好似选饭馆，葛优大爷有名言曰：哪家人多我找哪家！赶快来看看穿友们正在定制的热款吧！最重要的是：以下款式均可联系客服享九折特惠！","status":1,"statusname":"瀑布流形式","GuanJianList":["大衣","裙","外套","衬衣","上衣","羽绒服","裤"],"bigimg":"http://f1.myappcc.com/zfs/7E0/1322/SCJ/322185833350CABGRTUHAI.jpg","SumLook":0}}
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
         * Data : [{"id":1033,"topicsid":7,"productid":50780,"productmainpic":"http://f1.myappcc.com/zfs/7E0/1362/XXY/362234637893CABGPQQISG.jpg","fenlei":"","productname":"林俊杰同款高级羊毛混...","price":698,"createtime":"2017-02-21 15:55:24","status":0},{"id":1032,"topicsid":7,"productid":61198,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1048/XXY/048232014217CABHZSGDSY.jpg","fenlei":"","productname":"高级聚酯纤维层叠长袖...","price":732,"createtime":"2017-02-21 15:55:10","status":0},{"id":1031,"topicsid":7,"productid":61303,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1049/OPQ/049140832215CABHEVYVCF.jpg","fenlei":"","productname":"高级聚酯纤维双层长袖...","price":648,"createtime":"2017-02-21 15:54:57","status":0},{"id":1030,"topicsid":7,"productid":61532,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1050/SCJ/050183939945CABHGMKCUD.jpg","fenlei":"","productname":"华晨宇同款普洗/固色...","price":398,"createtime":"2017-02-21 15:54:28","status":0},{"id":1029,"topicsid":7,"productid":61335,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1049/QFI/049163938543CABHZZWPEY.jpg","fenlei":"","productname":"陈赫同款高级针织棉单...","price":261,"createtime":"2017-02-21 15:54:15","status":0},{"id":1028,"topicsid":7,"productid":61539,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1050/SCJ/050190026710CABHZJLSTW.jpg","fenlei":"","productname":"高级梭织棉双层无袖短...","price":440,"createtime":"2017-02-21 15:54:01","status":0},{"id":1027,"topicsid":7,"productid":61555,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1050/UKL/050200641585CABHTAYQXW.jpg","fenlei":"","productname":"薛之谦同款轻工钉珠高...","price":648,"createtime":"2017-02-21 15:53:36","status":0},{"id":1026,"topicsid":7,"productid":61650,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1051/AZA/051101446489CABHDMNCUK.jpg","fenlei":"","productname":"薛之谦同款高级棉麻混...","price":372,"createtime":"2017-02-21 15:53:22","status":0},{"id":1025,"topicsid":7,"productid":61542,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1050/TJK/050190621444CABHVUCITZ.jpg","fenlei":"","productname":"品质蕾丝花面短款吊带...","price":672,"createtime":"2017-02-21 15:53:03","status":0},{"id":1024,"topicsid":7,"productid":60595,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1045/VLW/045211845323CABHXVGNSQ.jpg","fenlei":"","productname":"高级混纺单层拼接长款...","price":384,"createtime":"2017-02-21 15:52:48","status":0}]
         * Topicss : {"id":7,"name":"今日开团","url":"#","img":"http://f1.myappcc.com/zfs/7E1/1047/KOG/047104558786CABHMYGNKY.jpg","createtime":"2016-11-15 19:29:36","miaoshu":"选衣服好似选饭馆，葛优大爷有名言曰：哪家人多我找哪家！赶快来看看穿友们正在定制的热款吧！最重要的是：以下款式均可联系客服享九折特惠！","guanjianzi":"大衣,裙,外套,衬衣,上衣,羽绒服,裤","morenwenben":"选衣服好似选饭馆，葛优大爷有名言曰：哪家人多我找哪家！赶快来看看穿友们正在定制的热款吧！最重要的是：以下款式均可联系客服享九折特惠！","status":1,"statusname":"瀑布流形式","GuanJianList":["大衣","裙","外套","衬衣","上衣","羽绒服","裤"],"bigimg":"http://f1.myappcc.com/zfs/7E0/1322/SCJ/322185833350CABGRTUHAI.jpg","SumLook":0}
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
             * id : 7
             * name : 今日开团
             * url : #
             * img : http://f1.myappcc.com/zfs/7E1/1047/KOG/047104558786CABHMYGNKY.jpg
             * createtime : 2016-11-15 19:29:36
             * miaoshu : 选衣服好似选饭馆，葛优大爷有名言曰：哪家人多我找哪家！赶快来看看穿友们正在定制的热款吧！最重要的是：以下款式均可联系客服享九折特惠！
             * guanjianzi : 大衣,裙,外套,衬衣,上衣,羽绒服,裤
             * morenwenben : 选衣服好似选饭馆，葛优大爷有名言曰：哪家人多我找哪家！赶快来看看穿友们正在定制的热款吧！最重要的是：以下款式均可联系客服享九折特惠！
             * status : 1
             * statusname : 瀑布流形式
             * GuanJianList : ["大衣","裙","外套","衬衣","上衣","羽绒服","裤"]
             * bigimg : http://f1.myappcc.com/zfs/7E0/1322/SCJ/322185833350CABGRTUHAI.jpg
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
             * id : 1033
             * topicsid : 7
             * productid : 50780
             * productmainpic : http://f1.myappcc.com/zfs/7E0/1362/XXY/362234637893CABGPQQISG.jpg
             * fenlei :
             * productname : 林俊杰同款高级羊毛混...
             * price : 698.0
             * createtime : 2017-02-21 15:55:24
             * status : 0
             */

            private int id;
            private int topicsid;
            private int productid;
            private String productmainpic;
            private String fenlei;
            private String productname;
            private double price;
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

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
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
