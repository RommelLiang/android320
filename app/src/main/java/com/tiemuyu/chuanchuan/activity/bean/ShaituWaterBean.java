package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CC2.0 on 2017/2/12.
 */

public class ShaituWaterBean   extends DataPacket  {





    @SerializedName("ProductId")
    private String ProductId;

    public String getImage1() {
        return Image1;
    }

    public void setImage1(String image1) {
        Image1 = image1;
    }

    @SerializedName("Image1")
    private String Image1;




    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    @SerializedName("Images")
    private String Images;








//    {"Code":1,"Msg":"OK","Data":
//        {"Pagedata":
//            {"CurrentPage":1,"PageSize":8,"Total":70,"Rows":
//                [{"MomentTypeDesc":"定制发图","UserName":null,"SharedDay":null,"SharedMonth":0,"ReviewSum":0,"FriendReview":null,"FriendReviewTop3":null,"UserImg":null,"UserNickName":null,"ProductPrice":0.0,"ProductName":null,"ContentBrief":"","IsPraise":false,"SharedTimeStr":"2017-02-11 17:16:28","Images":["http://f1.myappcc.com/zfs/7E1/1042/RIC/042171608352CABHHRASJZ.jpg"],"Id":75644,"UserId":29122,"OrderId":0,"ProductId":60011,"SharedTime":"2017-02-11 17:16:28","Content":"","ReviewTimes":0,"ViewTimes":11,"PraiseTimes":0,"MomentType":1,"IsPrivate":0,"IsOffer":1,"OfferTime":"2017-02-11 17:20:53","Tag":"上装","ImageNum":1,"Image1":"http://f1.myappcc.com/zfs/7E1/1042/RIC/042171608352CABHHRASJZ.jpg","Image2":"","Image3":"","Image4":"","Image5":"","Image6":"","Image7":"","Image8":"","Image9":"","RegApp":"","PkValue":75644}]}}}








}
