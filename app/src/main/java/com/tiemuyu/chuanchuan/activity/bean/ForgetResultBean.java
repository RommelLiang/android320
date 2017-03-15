package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CC2.0 on 2017/2/7.
 */

public class ForgetResultBean extends BaseBean{

    @SerializedName("Data")
    private ForgetDataBean dataBean;

    public ForgetDataBean getDataBean() {
        return dataBean;
    }


//	public String getDataBean() {
//		return dataBean;
//	}



    public void setDataBean(ForgetDataBean dataBean) {
        this.dataBean = dataBean;
    }


}
