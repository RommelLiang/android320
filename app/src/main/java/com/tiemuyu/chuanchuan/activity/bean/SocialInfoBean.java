package com.tiemuyu.chuanchuan.activity.bean;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class SocialInfoBean implements Serializable{
	
	@SerializedName("UserIdentity")
	private String UserIdentity;

	@SerializedName("NickName")
	private String NickName;
	
	@SerializedName("UserImg")
	private String UserImg;
	
	@SerializedName("Province")
	private String Province;

	@SerializedName("City")
	private String City;
	
	@SerializedName("District")
	private String District;
	
	@SerializedName("Signature")
	private String Signature;

	public String getUserIdentity() {
		return UserIdentity;
	}

	public void setUserIdentity(String userIdentity) {
		UserIdentity = userIdentity;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}


	public String getUserImg() {
		return UserImg;
	}

	public void setUserImg(String userImg) {
		UserImg = userImg;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public String getSignature() {
		return Signature;
	}

	public void setSignature(String signature) {
		Signature = signature;
	}
	
	
	
}
