package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

public class CashRollResultBean extends BaseBean{
	@SerializedName("Data")
	private CashRollDetilBean data;

	public CashRollDetilBean getData() {
		return data;
	}

	public void setData(CashRollDetilBean data) {
		this.data = data;
	}

	
}
