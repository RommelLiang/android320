package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

public class BooleanDefaultBean extends BaseBean {
	//{"Code":1,"Msg":"OK","Data":true}
	@SerializedName("Data")
	private boolean data;

	public boolean isData() {
		return data;
	}

	public void setData(boolean data) {
		this.data = data;
	}
	
	

}
