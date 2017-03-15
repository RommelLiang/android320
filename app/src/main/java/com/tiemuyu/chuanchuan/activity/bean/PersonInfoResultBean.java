package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

public class PersonInfoResultBean extends BaseBean {

	@SerializedName("Data")
	private PersonInfoBean data;

	public PersonInfoBean getData() {
		return data;
	}

	public void setData(PersonInfoBean data) {
		this.data = data;
	}
	
	
}
