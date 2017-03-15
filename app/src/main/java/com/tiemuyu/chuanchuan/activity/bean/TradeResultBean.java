package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

public class TradeResultBean extends BaseBean {

	@SerializedName("Data")
	private TradeDetilBean data;

	public TradeDetilBean getData() {
		return data;
	}

	public void setData(TradeDetilBean data) {
		this.data = data;
	}
	
	
	
}
