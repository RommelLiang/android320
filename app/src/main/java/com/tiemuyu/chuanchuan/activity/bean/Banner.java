package com.tiemuyu.chuanchuan.activity.bean;

/**
 * Created by CC2.0 on 2017/1/19.
 */

public class Banner extends DataPacket {

//    "AdTypeName":"Banner",
//            "Id":80,
//            "CategoryId":0,
//            "Title":"《我们十七岁》和男神们一起重返青春",
//            "LinkUrl":"http://a1.myappcc.com/cc/Find/RankingList?id=170116122011659",
//            "ImgUrl":"http://f1.myappcc.com/zfs/7E1/1017/TJK/017193208185CABHJGWGAI.jpg",
//            "SortId":158,
//            "Rmk":"",
//            "CreatorDate":"2017-01-17 18:17:11",
//            "CreatorUsername":"18776037331",
//            "Status":1,
//            "PkValue":80

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }

    public String getLinkUrl() {
        return LinkUrl;
    }
    public void setLinkUrl(String linkUrl) {
        LinkUrl = linkUrl;
    }

    public String getImgUrl() {
        return ImgUrl;
    }
    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getGetWenZhangListIndex() {
        return GetWenZhangListIndex;
    }
    public void setGetWenZhangListIndex(String getWenZhangListIndex) {
        GetWenZhangListIndex = getWenZhangListIndex;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    private int Id;

    private String Title;//标题

    private String LinkUrl;//链接

    private String ImgUrl;//图像url

    private int Type;//文章还是瀑布流

    private  String  GetWenZhangListIndex; //文章list id

//    public int getId() {
//        return Id;
//    }
//
//    public void setId(int id) {
//        Id=id;
//    }
//
//    public String getTitle() {
//        return Title;
//    }
//
//    public void setTitle(String title) {
//         Title=title;
//    }
//
//    public String getLinkUrl() {
//        return LinkUrl;
//    }
//
//    public int getType() {
//        return Type;
//    }
//
//    public String getImgUrl() {
//        return ImgUrl;
//    }
//
//    public int getGetWenZhangListIndex() {
//        return GetWenZhangListIndex;
//    }

}
