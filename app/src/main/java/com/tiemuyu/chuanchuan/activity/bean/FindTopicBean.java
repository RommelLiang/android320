package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

/**
 * @项目名： 227androidpay-master
 * @包名： com.tiemuyu.chuanchuan.activity.bean
 * @类描述：
 * @创建人： hr
 * @创建时间： 2017/3/1
 * @version：
 */

public class FindTopicBean {
    /**
     * Code : 1
     * Msg : OK
     * Data : [{"id":93,"topicsid":18,"img":"http://f1.myappcc.com/zfs/7E1/1010/RIC/010173636448CABHHGEFCF.jpg","name":"《梦想的声音》造型堪比时装秀","fenlei":"梦想的声音","miaoshu":"《梦想的声音》自热播以来，不少眼尖的网友发现，节目除了让人止不住想加入合唱大潮，更是一场时装视觉盛宴，导师们唱的每一首歌，他们穿的衣服也跟歌曲风格完美相融，两者的结合让观众既大饱耳福又大饱眼福。","url":"http://a1.myappcc.com/cc/Find/RankingList?id=170110154604164","createtime":"2017-01-10 17:38:01","looksum":8218},{"id":94,"topicsid":18,"img":"http://f1.myappcc.com/zfs/7E1/1011/RIC/011172849444CABHNFEXSK.jpg","name":"《火星情报局》服装引观众点赞","fenlei":"火星情报局","miaoshu":"《火星情报局》无疑是当前最火脱口秀综艺，由汪涵、郭雪芙、沈梦辰、李菲儿、薛之谦等人组成的火星情报成员性格各有特点，在节目中上演的各种搞笑模式，引发收视狂潮。其中就连成员们穿的衣服也都成了网友们重点关注","url":"http://a1.myappcc.com/cc/Find/RankingList?id=170111145113546","createtime":"2017-01-11 17:30:10","looksum":6626},{"id":95,"topicsid":18,"img":"http://f1.myappcc.com/zfs/7E1/1012/TJK/012195410767CABHSOVMWI.jpg","name":"《快乐大本营》主持嘉宾衣品超高","fenlei":"快乐大本营","miaoshu":"《快乐大本营》除了超高的搞笑指数让人欲罢不能，主持人和嘉宾的衣服也是一个很大的看点。","url":"http://a1.myappcc.com/cc/Find/RankingList?id=170112105245094","createtime":"2017-01-12 16:49:33","looksum":6610},{"id":96,"topicsid":18,"img":"http://f1.myappcc.com/zfs/7E1/1017/MUH/017120908717CABHFFHYRJ.jpg","name":"《我们十七岁》和男神一起重返青春","fenlei":"我们十七岁","miaoshu":"《我们十七岁》是一档大型原创明星旅游实景真人秀，节目由郭富城、林志颖、孙杨、华少、韩东君、范明等组成\u201c十七岁兄弟连\u201d，带着放肆的心情展开一段重回青春时期的旅行生活。对粉丝们来说，这又是一个了解男神衣品","url":"http://a1.myappcc.com/cc/Find/RankingList?id=170116122011659","createtime":"2017-01-17 12:10:20","looksum":3596},{"id":97,"topicsid":18,"img":"http://f1.myappcc.com/zfs/7E1/1018/QFI/018160345198CABHETHEAI.jpg","name":"《天天向上》主持嘉宾穿衣太时尚！","fenlei":"天天向上","miaoshu":"《天天向上》因风趣、幽默、睿智的节目风格稳在众脱口秀节目中脱颖而出，\u201c天天兄弟\u201d和嘉宾们在节目中的穿衣风格很有个人特色，受到很多粉丝的追捧。","url":"http://a1.myappcc.com/cc/Find/RankingList?id=170118114814822","createtime":"2017-01-18 16:03:27","looksum":5985},{"id":108,"topicsid":18,"img":"http://f1.myappcc.com/zfs/7E1/1019/SCJ/019181315959CABHBQVLHC.jpg","name":"《一年级》颜值衣品大比拼","fenlei":"一年级","miaoshu":"重回大学校园的明星大咖及小鲜肉们除了颜值不相上下，衣品也是各有风格，难分高低。","url":"http://a1.myappcc.com/cc/Find/RankingList?id=170119154520563","createtime":"2017-01-19 18:12:59","looksum":4219},{"id":176,"topicsid":18,"img":"http://f1.myappcc.com/zfs/7E1/1046/SCJ/046183721429CABHVIWDQA.jpg","name":"《歌手》造型抢眼 谁是\u201cPOSE\u201d王","fenlei":"我是歌手","miaoshu":"本季\u201c歌手\u201d首发阵容并没有人气偶像级的选手参赛，但却是《我歌》开播以来综合演唱实力最强的一届，汇集了老中青三代、体制内外的顶级唱功歌手。","url":"http://a1.myappcc.com/cc/Find/RankingList?id=170214183858988","createtime":"2017-02-15 17:53:14","looksum":5405}]
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
         * id : 93
         * topicsid : 18
         * img : http://f1.myappcc.com/zfs/7E1/1010/RIC/010173636448CABHHGEFCF.jpg
         * name : 《梦想的声音》造型堪比时装秀
         * fenlei : 梦想的声音
         * miaoshu : 《梦想的声音》自热播以来，不少眼尖的网友发现，节目除了让人止不住想加入合唱大潮，更是一场时装视觉盛宴，导师们唱的每一首歌，他们穿的衣服也跟歌曲风格完美相融，两者的结合让观众既大饱耳福又大饱眼福。
         * url : http://a1.myappcc.com/cc/Find/RankingList?id=170110154604164
         * createtime : 2017-01-10 17:38:01
         * looksum : 8218
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
