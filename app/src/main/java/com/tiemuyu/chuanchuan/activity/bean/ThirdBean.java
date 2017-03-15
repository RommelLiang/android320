package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

public class ThirdBean extends DataPacket {

	@SerializedName("OauthId")
	private String OauthId;
	
	@SerializedName("LoginCode")
	private String LoginCode;

	public String getOauthId() {
		return OauthId;
	}

	public void setOauthId(String oauthId) {
		OauthId = oauthId;
	}

	public String getLoginCode() {
		return LoginCode;
	}

	public void setLoginCode(String loginCode) {
		LoginCode = loginCode;
	}
	
	
}
