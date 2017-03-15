package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

public class UpImgResultBean extends BaseBean{
	
	@SerializedName("Data")
	public UpImgBean Data;

	public UpImgBean getData() {
		return Data;
	}

	public void setData(UpImgBean data) {
		Data = data;
	}

	
}
