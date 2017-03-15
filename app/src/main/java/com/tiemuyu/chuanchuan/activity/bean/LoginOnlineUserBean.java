package com.tiemuyu.chuanchuan.activity.bean;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class LoginOnlineUserBean implements Serializable{
	/**
  "UserId": 11165,
            "UserImg": "http://190.168.2.201/zfs/7DF/1327/RIC/327175540776CABFOYEWMM.jpg",
            "Username": "13480733848",
            "SId": "a3otfw04rfu3f3fk5rzhandq",
            "NickName": "卡特"
	 * 
	 * */
	
	@SerializedName("UserId")
	private int UserId;//
	
	@SerializedName("InviteCode")
	private String InviteCode;//我的邀请码
	
	@SerializedName("Username")
	private String Username;//

//	@SerializedName("Email")
//	private String Email;//
	
	@SerializedName("UserImg")
	private String UserImg;//
	
	@SerializedName("NickName")
	private String NickName;//
	
//	@SerializedName("OauthId")
//	private int OauthId;
	
	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

//	public String getEmail() {
//		return Email;
//	}
//
//	public void setEmail(String email) {
//		Email = email;
//	}

	public String getUserImg() {
		return UserImg;
	}

	public void setUserImg(String userImg) {
		UserImg = userImg;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

//	public int getOauthId() {
//		return OauthId;
//	}
//
//	public void setOauthId(int oauthId) {
//		OauthId = oauthId;
//	}

	public String getInviteCode() {
		return InviteCode;
	}

	public void setInviteCode(String inviteCode) {
		InviteCode = inviteCode;
	}

	
	
}
