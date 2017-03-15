package com.tiemuyu.chuanchuan.activity.bean;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class OauthsBean implements Serializable{
	
	@SerializedName("QQ")
	private String QQ;
	
	@SerializedName("Wechat")
	private String Wechat;
	
	@SerializedName("Weibo")
	private String Weibo;
	
	@SerializedName("Email")
	private String Email;

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public String getWechat() {
		return Wechat;
	}

	public void setWechat(String wechat) {
		Wechat = wechat;
	}

	public String getWeibo() {
		return Weibo;
	}

	public void setWeibo(String weibo) {
		Weibo = weibo;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
	
	

}
