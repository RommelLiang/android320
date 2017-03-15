package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by CC2.0 on 2017/1/20.
 */
public class WaterBeanData extends DataPacket {

    public int getCurrentPage() {
        return CurrentPage;
    }

    public int getPageRocordEndNum() {
        return PageRocordEndNum;
    }

    public int getPageRecordStartNum() {
        return PageRecordStartNum;
    }

    public List<BaoJiaWater> getRows() {
        System.out.println("在waterbeandata里面尝试去getrows");
        return Rows;
    }

    public int getPageSize() {
        return PageSize;
    }

    @SerializedName("CurrentPage")
    private int CurrentPage;

    @SerializedName("PageSize")
    private int PageSize;//标题

    @SerializedName("Rows")
    private List<BaoJiaWater> Rows;

    @SerializedName("PageRecordStartNum")
    private int PageRecordStartNum;//  页面开始标签

    @SerializedName("PageRocordEndNum")
    private int PageRocordEndNum;//页面结束标签

}