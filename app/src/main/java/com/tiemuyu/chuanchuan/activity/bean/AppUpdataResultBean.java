package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

/**
  * @ClassName: AppResultBean
  * @Description: TODO App更新
  * @author hw
  * @date 2015-8-13
  */
public class AppUpdataResultBean extends BaseBean {

	@SerializedName("Data")
	private AppUpdataBean data;

	public AppUpdataBean getData() {
		return data;
	}

	public void setData(AppUpdataBean data) {
		this.data = data;
	}
	
}
