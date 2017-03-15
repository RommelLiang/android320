package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

/**
 * @项目名： 215androidpay
 * @包名： com.tiemuyu.chuanchuan.activity.bean
 * @类描述：
 * @创建人： hr
 * @创建时间： 2017/2/24
 * @version：
 */

public class FindWaterBean {
    /**
     * Code : 1
     * Msg : OK
     * Data : {"Topics":[{"id":26,"name":"发现文章","url":"#","img":"http://f1.myappcc.com/zfs/7E1/1019/QFI/019164018631CABHPHMCVD.jpg","createtime":"2017-01-19 16:40:13","miaoshu":"网罗当季最热明星同款、热剧同款、时尚搭配等时装资讯，引领您走在潮流前线","guanjianzi":"明星专场,热剧扒款,综艺爆款,早春新装","morenwenben":"#","status":2,"statusname":"瀑布流形式","GuanJianList":["明星专场","热剧扒款","综艺爆款","早春新装"],"bigimg":"http://f1.myappcc.com/zfs/7E1/1019/QFI/019164028709CABHWFRYAE.jpg","SumLook":0}],"Num":23,"List":[{"id":122,"topicsid":26,"img":"http://f1.myappcc.com/zfs/7E1/1019/TJK/019194051599CABHZHPPBD.jpg","name":"D&G真丝双绉","fenlei":"大哥牌真丝双绉","miaoshu":"100%真丝面料，织物表面精炼后起隐约的细致绉纹，手感软糯、垂顺。质地轻柔、平滑光亮、坚韧、抗皱性良好，上身非常凉爽舒适。","url":"http://a1.myappcc.com/cc/Find/RankingList?id=161223164929051","createtime":"2017-01-19 19:40:30","looksum":6185},{"id":127,"topicsid":26,"img":"http://f1.myappcc.com/zfs/7E1/1020/OPQ/020144832768CABHMDKIZM.jpg","name":"法国订单高品质龙骨蕾丝","fenlei":"法国订单高品质龙骨蕾丝","miaoshu":"来自米兰蕾丝大王PIZVAL的客供面料，纱线般的光泽、骨感，整体面料的手感和紧密度远超平时见到的国产蕾丝水准。","url":"http://a1.myappcc.com/cc/Find/RankingList?id=161223164840035","createtime":"2017-01-20 14:48:22","looksum":3115},{"id":125,"topicsid":26,"img":"http://f1.myappcc.com/zfs/7E1/1020/OPQ/020144653830CABHYCCWHX.jpg","name":"玫瑰纯真丝双绉","fenlei":"玫瑰纯真丝双绉","miaoshu":"100%真丝面料，数码喷绘工艺，独家版清新亮丽的玫瑰图案，质地轻柔、平滑光亮、坚韧、抗皱性良好，上身非常凉爽舒适。","url":"http://a1.myappcc.com/cc/Find/RankingList?id=161223165142630","createtime":"2017-01-20 14:46:42","looksum":5985},{"id":123,"topicsid":26,"img":"http://f1.myappcc.com/zfs/7E1/1019/TJK/019194205209CABHUFYIHP.jpg","name":"小天鹅真丝双绉","fenlei":"小天鹅真丝双绉","miaoshu":"100%真丝面料，甜美清新小天鹅图案，织物表面精炼后起隐约的细致绉纹，手感软糯、垂顺。质地轻柔、平滑光亮、坚韧、抗皱性良好，上身非常凉爽舒适","url":"http://a1.myappcc.com/cc/Find/RankingList?id=161223165049999","createtime":"2017-01-19 19:41:44","looksum":6325},{"id":124,"topicsid":26,"img":"http://f1.myappcc.com/zfs/7E1/1019/TJK/019194341255CABHZXGIYK.jpg","name":"裸色全棉水溶蕾丝","fenlei":"裸色全棉水溶蕾丝","miaoshu":"高品质海外货源，重磅水溶，全棉材质，取材天然纤维，采用传统的工艺，有着柔软、高贵、精致的特点。","url":"http://a1.myappcc.com/cc/Find/RankingList?id=161223164322785","createtime":"2017-01-19 19:43:20","looksum":5301},{"id":121,"topicsid":26,"img":"http://f1.myappcc.com/zfs/7E1/1019/TJK/019193906927CABHLVPOJY.jpg","name":"进口飞鸟真丝双乔","fenlei":"进口飞鸟真丝双乔","miaoshu":"100%真丝，21姆米左右，双乔的手感极好，平整、舒适、不宜拔丝，越穿越软的真丝，亲肤性在真丝里面是非常好的。","url":"http://a1.myappcc.com/cc/Find/RankingList?id=161227150801027","createtime":"2017-01-19 19:38:47","looksum":5462}]}
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
         * Topics : [{"id":26,"name":"发现文章","url":"#","img":"http://f1.myappcc.com/zfs/7E1/1019/QFI/019164018631CABHPHMCVD.jpg","createtime":"2017-01-19 16:40:13","miaoshu":"网罗当季最热明星同款、热剧同款、时尚搭配等时装资讯，引领您走在潮流前线","guanjianzi":"明星专场,热剧扒款,综艺爆款,早春新装","morenwenben":"#","status":2,"statusname":"瀑布流形式","GuanJianList":["明星专场","热剧扒款","综艺爆款","早春新装"],"bigimg":"http://f1.myappcc.com/zfs/7E1/1019/QFI/019164028709CABHWFRYAE.jpg","SumLook":0}]
         * Num : 23
         * List : [{"id":122,"topicsid":26,"img":"http://f1.myappcc.com/zfs/7E1/1019/TJK/019194051599CABHZHPPBD.jpg","name":"D&G真丝双绉","fenlei":"大哥牌真丝双绉","miaoshu":"100%真丝面料，织物表面精炼后起隐约的细致绉纹，手感软糯、垂顺。质地轻柔、平滑光亮、坚韧、抗皱性良好，上身非常凉爽舒适。","url":"http://a1.myappcc.com/cc/Find/RankingList?id=161223164929051","createtime":"2017-01-19 19:40:30","looksum":6185},{"id":127,"topicsid":26,"img":"http://f1.myappcc.com/zfs/7E1/1020/OPQ/020144832768CABHMDKIZM.jpg","name":"法国订单高品质龙骨蕾丝","fenlei":"法国订单高品质龙骨蕾丝","miaoshu":"来自米兰蕾丝大王PIZVAL的客供面料，纱线般的光泽、骨感，整体面料的手感和紧密度远超平时见到的国产蕾丝水准。","url":"http://a1.myappcc.com/cc/Find/RankingList?id=161223164840035","createtime":"2017-01-20 14:48:22","looksum":3115},{"id":125,"topicsid":26,"img":"http://f1.myappcc.com/zfs/7E1/1020/OPQ/020144653830CABHYCCWHX.jpg","name":"玫瑰纯真丝双绉","fenlei":"玫瑰纯真丝双绉","miaoshu":"100%真丝面料，数码喷绘工艺，独家版清新亮丽的玫瑰图案，质地轻柔、平滑光亮、坚韧、抗皱性良好，上身非常凉爽舒适。","url":"http://a1.myappcc.com/cc/Find/RankingList?id=161223165142630","createtime":"2017-01-20 14:46:42","looksum":5985},{"id":123,"topicsid":26,"img":"http://f1.myappcc.com/zfs/7E1/1019/TJK/019194205209CABHUFYIHP.jpg","name":"小天鹅真丝双绉","fenlei":"小天鹅真丝双绉","miaoshu":"100%真丝面料，甜美清新小天鹅图案，织物表面精炼后起隐约的细致绉纹，手感软糯、垂顺。质地轻柔、平滑光亮、坚韧、抗皱性良好，上身非常凉爽舒适","url":"http://a1.myappcc.com/cc/Find/RankingList?id=161223165049999","createtime":"2017-01-19 19:41:44","looksum":6325},{"id":124,"topicsid":26,"img":"http://f1.myappcc.com/zfs/7E1/1019/TJK/019194341255CABHZXGIYK.jpg","name":"裸色全棉水溶蕾丝","fenlei":"裸色全棉水溶蕾丝","miaoshu":"高品质海外货源，重磅水溶，全棉材质，取材天然纤维，采用传统的工艺，有着柔软、高贵、精致的特点。","url":"http://a1.myappcc.com/cc/Find/RankingList?id=161223164322785","createtime":"2017-01-19 19:43:20","looksum":5301},{"id":121,"topicsid":26,"img":"http://f1.myappcc.com/zfs/7E1/1019/TJK/019193906927CABHLVPOJY.jpg","name":"进口飞鸟真丝双乔","fenlei":"进口飞鸟真丝双乔","miaoshu":"100%真丝，21姆米左右，双乔的手感极好，平整、舒适、不宜拔丝，越穿越软的真丝，亲肤性在真丝里面是非常好的。","url":"http://a1.myappcc.com/cc/Find/RankingList?id=161227150801027","createtime":"2017-01-19 19:38:47","looksum":5462}]
         */

        private int Num;
        private java.util.List<TopicsBean> Topics;
        private java.util.List<ListBean> List;

        public int getNum() {
            return Num;
        }

        public void setNum(int Num) {
            this.Num = Num;
        }

        public List<TopicsBean> getTopics() {
            return Topics;
        }

        public void setTopics(List<TopicsBean> Topics) {
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
             * id : 26
             * name : 发现文章
             * url : #
             * img : http://f1.myappcc.com/zfs/7E1/1019/QFI/019164018631CABHPHMCVD.jpg
             * createtime : 2017-01-19 16:40:13
             * miaoshu : 网罗当季最热明星同款、热剧同款、时尚搭配等时装资讯，引领您走在潮流前线
             * guanjianzi : 明星专场,热剧扒款,综艺爆款,早春新装
             * morenwenben : #
             * status : 2
             * statusname : 瀑布流形式
             * GuanJianList : ["明星专场","热剧扒款","综艺爆款","早春新装"]
             * bigimg : http://f1.myappcc.com/zfs/7E1/1019/QFI/019164028709CABHWFRYAE.jpg
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
             * id : 122
             * topicsid : 26
             * img : http://f1.myappcc.com/zfs/7E1/1019/TJK/019194051599CABHZHPPBD.jpg
             * name : D&G真丝双绉
             * fenlei : 大哥牌真丝双绉
             * miaoshu : 100%真丝面料，织物表面精炼后起隐约的细致绉纹，手感软糯、垂顺。质地轻柔、平滑光亮、坚韧、抗皱性良好，上身非常凉爽舒适。
             * url : http://a1.myappcc.com/cc/Find/RankingList?id=161223164929051
             * createtime : 2017-01-19 19:40:30
             * looksum : 6185
             */

            private int id;
            private int topicsid;
            private String img;
            private String name;
            private String fenlei;
            private String miaoshu;
            private String url;
            private String createtime;
            private int looksum;

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

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFenlei() {
                return fenlei;
            }

            public void setFenlei(String fenlei) {
                this.fenlei = fenlei;
            }

            public String getMiaoshu() {
                return miaoshu;
            }

            public void setMiaoshu(String miaoshu) {
                this.miaoshu = miaoshu;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public int getLooksum() {
                return looksum;
            }

            public void setLooksum(int looksum) {
                this.looksum = looksum;
            }
        }
    }
}