package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class AccountConnectResultBean extends BaseBean {

	@SerializedName("Data")
	private List<AccountConnectBean>data;

	public List<AccountConnectBean> getData() {
		return data;
	}

	public void setData(List<AccountConnectBean> data) {
		this.data = data;
	}
	
	
}
