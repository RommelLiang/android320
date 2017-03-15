package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

public class LoginResultBean extends BaseBean {

	@SerializedName("Data")
	private LoginDataBean dataBean;

	public LoginDataBean getDataBean() {
		return dataBean;
	}


//	public String getDataBean() {
//		return dataBean;
//	}



	public void setDataBean(LoginDataBean dataBean) {
		this.dataBean = dataBean;
	}
	
	
}
