package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

public class RegistResultBean extends DataPacket {

	@SerializedName("Data")
	private RegistBean Data;

	public RegistBean getData() {
		return Data;
	}

	public void setData(RegistBean data) {
		Data = data;
	}
	
	
}
