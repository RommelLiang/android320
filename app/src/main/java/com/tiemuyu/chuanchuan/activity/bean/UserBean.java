package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

public class UserBean extends DataPacket {

	@SerializedName("Username")
	private String Username;

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}
	
	
	
}
