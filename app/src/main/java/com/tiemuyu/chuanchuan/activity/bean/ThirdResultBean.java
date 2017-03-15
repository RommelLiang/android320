package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

public class ThirdResultBean extends BaseBean {
	
	@SerializedName("Data")
	private ThirdBean Data;

	public ThirdBean getData() {
		return Data;
	}

	public void setData(ThirdBean data) {
		Data = data;
	}
	
	

}
