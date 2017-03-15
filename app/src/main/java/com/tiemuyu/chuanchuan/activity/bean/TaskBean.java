package com.tiemuyu.chuanchuan.activity.bean;

import java.io.Serializable;

public class TaskBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mUrl;//请求的地址
	
	private String mTag;//请求的标识

	public String getmUrl() {
		return mUrl;
	}

	public void setmUrl(String mUrl) {
		this.mUrl = mUrl;
	}

	public String getmTag() {
		return mTag;
	}

	public void setmTag(String mTag) {
		this.mTag = mTag;
	}
	
	

}
