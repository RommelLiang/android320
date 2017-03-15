package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

public class AwardResultBean extends BaseBean {

	@SerializedName("Data")
	private AwardBean Data;

	public AwardBean getData() {
		return Data;
	}

	public void setData(AwardBean data) {
		Data = data;
	}
	
	
}
