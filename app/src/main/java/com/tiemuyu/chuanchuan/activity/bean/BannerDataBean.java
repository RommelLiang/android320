package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CC2.0 on 2017/1/19.
 */

public class BannerDataBean extends DataPacket {

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

    public String getImgUrl() {
        return ImgUrl;
    }
    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getLinkUrl() {
        return LinkUrl;
    }
    public void setLinkUrl(String linkUrl) {
        LinkUrl = linkUrl;
    }

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

    @SerializedName("Id")
    private int Id;//ID

    @SerializedName("Title")
    private String Title;//标题

    @SerializedName("LinkUrl")
    private String LinkUrl;//链接

    @SerializedName("ImgUrl")
    private String ImgUrl;//图像url

}
