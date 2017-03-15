package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

public class TokenResultBean extends BaseBean {
	
	@SerializedName("Data")
	private TokenBean Data;

	public TokenBean getData() {
		return Data;
	}

	public void setData(TokenBean data) {
		Data = data;
	}
	
	

}
