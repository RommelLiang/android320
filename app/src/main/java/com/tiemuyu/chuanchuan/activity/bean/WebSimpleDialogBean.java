package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

public class WebSimpleDialogBean extends DataPacket {
	@SerializedName("message")
	private String message;

	@SerializedName("title")
	private String title;

	@SerializedName("ok")
	private String ok;

	@SerializedName("cancle")
	private String cancle;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

	public String getCancle() {
		return cancle;
	}

	public void setCancle(String cancle) {
		this.cancle = cancle;
	}
	
	
	
}
