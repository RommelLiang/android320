package com.tiemuyu.chuanchuan.activity.entity;

/**
 * Created by SHEN on 2015/11/5.
 */
public class BannerInfo {
    //标题
    public String title;
    //图片URL
    public String url_pic;
    //内容URL
    public String url_content;
    public BannerInfo(String title, String url_pic, String url_content){
        this.title=title;
        this.url_pic=url_pic;
        this.url_content=url_content;
    }
}
