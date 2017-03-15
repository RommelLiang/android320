package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/2/19.
 */

public class ZhuanTiMessage {

    /**
     * Code : 1
     * Msg : OK
     * Data : {"Topics":{"id":20,"name":"裙装","url":"##","img":"http://f1.myappcc.com/zfs/7E1/1012/MUH/012123944253CABHWEUCSU.gif","createtime":"2017-01-12 12:41:11","miaoshu":"裙装作为增加女性专属魅力的单品，非常容易就能凹出百变造型。甜美、休闲、端庄、清新等各种，最重要的是：以下款式均可联系客服享九折特惠！","guanjianzi":"裙装","morenwenben":"裙装","status":1,"statusname":"瀑布流形式","GuanJianList":["裙装"],"bigimg":"http://f1.myappcc.com/zfs/7E1/1012/MUH/012123952284CABHTQLECT.jpg","SumLook":0},"List":[{"id":940,"topicsid":20,"productid":59491,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1038/WWX/040131949688CABHGQPHCW.jpg","fenlei":"","productname":"酵素洗高级梭织棉单层...","price":498,"createtime":"2017-02-16 12:51:33","status":0},{"id":939,"topicsid":20,"productid":59711,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1041/JNO/041095013654CABHNFATOV.jpg","fenlei":"","productname":"高级梭织棉层叠短款半...","price":267,"createtime":"2017-02-16 12:51:19","status":0},{"id":937,"topicsid":20,"productid":56417,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1018/QFI/018170415572CABHTXNMHU.jpg","fenlei":"","productname":"高级混纺双层短款半裙","price":264,"createtime":"2017-02-16 12:50:56","status":0},{"id":934,"topicsid":20,"productid":59500,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1040/NHP/040133324407CABHBHKUYO.jpg","fenlei":"","productname":"高级混纺廓形短款半裙","price":312,"createtime":"2017-02-16 12:50:23","status":0},{"id":932,"topicsid":20,"productid":59288,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1039/PQF/039151638676CABHMEQSXW.png","fenlei":"","productname":"高级混纺拼接长袖短款...","price":384,"createtime":"2017-02-16 12:49:52","status":0},{"id":926,"topicsid":20,"productid":59243,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1039/LGU/039114012708CABHDFHFYO.jpg","fenlei":"","productname":"高级羊毛混纺层叠无袖...","price":1032,"createtime":"2017-02-16 12:48:27","status":0},{"id":925,"topicsid":20,"productid":59005,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1038/JNO/038094334853CABHZJBTLL.jpg","fenlei":"","productname":"高级混纺拼接短袖中长...","price":600,"createtime":"2017-02-16 12:48:09","status":0},{"id":906,"topicsid":20,"productid":59481,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1040/MUH/040130012032CABHXKNGLD.jpg","fenlei":"","productname":"高级混纺双层无袖中长...","price":552,"createtime":"2017-02-13 11:50:45","status":0},{"id":905,"topicsid":20,"productid":59490,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1040/NHP/040131808267CABHLJKTIV.jpg","fenlei":"","productname":"高级混纺收腰长袖中长...","price":648,"createtime":"2017-02-13 11:50:36","status":0},{"id":904,"topicsid":20,"productid":59501,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1040/NHP/040133439688CABHVLQYVE.jpg","fenlei":"","productname":"高级混纺拼接中款半裙","price":498,"createtime":"2017-02-13 11:50:26","status":0}]}
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
         * Topics : {"id":20,"name":"裙装","url":"##","img":"http://f1.myappcc.com/zfs/7E1/1012/MUH/012123944253CABHWEUCSU.gif","createtime":"2017-01-12 12:41:11","miaoshu":"裙装作为增加女性专属魅力的单品，非常容易就能凹出百变造型。甜美、休闲、端庄、清新等各种，最重要的是：以下款式均可联系客服享九折特惠！","guanjianzi":"裙装","morenwenben":"裙装","status":1,"statusname":"瀑布流形式","GuanJianList":["裙装"],"bigimg":"http://f1.myappcc.com/zfs/7E1/1012/MUH/012123952284CABHTQLECT.jpg","SumLook":0}
         * List : [{"id":940,"topicsid":20,"productid":59491,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1038/WWX/040131949688CABHGQPHCW.jpg","fenlei":"","productname":"酵素洗高级梭织棉单层...","price":498,"createtime":"2017-02-16 12:51:33","status":0},{"id":939,"topicsid":20,"productid":59711,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1041/JNO/041095013654CABHNFATOV.jpg","fenlei":"","productname":"高级梭织棉层叠短款半...","price":267,"createtime":"2017-02-16 12:51:19","status":0},{"id":937,"topicsid":20,"productid":56417,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1018/QFI/018170415572CABHTXNMHU.jpg","fenlei":"","productname":"高级混纺双层短款半裙","price":264,"createtime":"2017-02-16 12:50:56","status":0},{"id":934,"topicsid":20,"productid":59500,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1040/NHP/040133324407CABHBHKUYO.jpg","fenlei":"","productname":"高级混纺廓形短款半裙","price":312,"createtime":"2017-02-16 12:50:23","status":0},{"id":932,"topicsid":20,"productid":59288,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1039/PQF/039151638676CABHMEQSXW.png","fenlei":"","productname":"高级混纺拼接长袖短款...","price":384,"createtime":"2017-02-16 12:49:52","status":0},{"id":926,"topicsid":20,"productid":59243,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1039/LGU/039114012708CABHDFHFYO.jpg","fenlei":"","productname":"高级羊毛混纺层叠无袖...","price":1032,"createtime":"2017-02-16 12:48:27","status":0},{"id":925,"topicsid":20,"productid":59005,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1038/JNO/038094334853CABHZJBTLL.jpg","fenlei":"","productname":"高级混纺拼接短袖中长...","price":600,"createtime":"2017-02-16 12:48:09","status":0},{"id":906,"topicsid":20,"productid":59481,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1040/MUH/040130012032CABHXKNGLD.jpg","fenlei":"","productname":"高级混纺双层无袖中长...","price":552,"createtime":"2017-02-13 11:50:45","status":0},{"id":905,"topicsid":20,"productid":59490,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1040/NHP/040131808267CABHLJKTIV.jpg","fenlei":"","productname":"高级混纺收腰长袖中长...","price":648,"createtime":"2017-02-13 11:50:36","status":0},{"id":904,"topicsid":20,"productid":59501,"productmainpic":"http://f1.myappcc.com/zfs/7E1/1040/NHP/040133439688CABHVLQYVE.jpg","fenlei":"","productname":"高级混纺拼接中款半裙","price":498,"createtime":"2017-02-13 11:50:26","status":0}]
         */

        private TopicsBean Topics;
        private java.util.List<ListBean> List;

        public TopicsBean getTopics() {
            return Topics;
        }

        public void setTopics(TopicsBean Topics) {
            this.Topics = Topics;
        }

        public List<ListBean> getList() {
            return List;
        }

        public void setList(List<ListBean> List) {
            this.List = List;
        }

        public static class TopicsBean {
            /**
             * id : 20
             * name : 裙装
             * url : ##
             * img : http://f1.myappcc.com/zfs/7E1/1012/MUH/012123944253CABHWEUCSU.gif
             * createtime : 2017-01-12 12:41:11
             * miaoshu : 裙装作为增加女性专属魅力的单品，非常容易就能凹出百变造型。甜美、休闲、端庄、清新等各种，最重要的是：以下款式均可联系客服享九折特惠！
             * guanjianzi : 裙装
             * morenwenben : 裙装
             * status : 1
             * statusname : 瀑布流形式
             * GuanJianList : ["裙装"]
             * bigimg : http://f1.myappcc.com/zfs/7E1/1012/MUH/012123952284CABHTQLECT.jpg
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
            private java.util.List<String> GuanJianList;

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

        public static class ListBean {
            /**
             * id : 940
             * topicsid : 20
             * productid : 59491
             * productmainpic : http://f1.myappcc.com/zfs/7E1/1038/WWX/040131949688CABHGQPHCW.jpg
             * fenlei :
             * productname : 酵素洗高级梭织棉单层...
             * price : 498
             * createtime : 2017-02-16 12:51:33
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
