package com.tiemuyu.chuanchuan.activity.bean;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class RegistBean implements Serializable{
/**
 *  "UserId": 351,
        "TrueName": null,
        "NickName": "cczy_user351",
        "IdCard": null,
        "Gender": 0,
        "UserImg": "http://190.168.1.80:8001/frame/login/default_img.jpg",
        "CreatorDate": "0001-01-01T00:00:00",
        "CreatorUsername": null,
        "Rmk": null,
        "UpdateDate": "0001-01-01T00:00:00",
        "UpdateUsername": null,
        "PkValue": 351
 * 
 * */
	@SerializedName("UserId")
	private long UserId;
	
	@SerializedName("TrueName")
	private  String TrueName;
	
	@SerializedName("NickName")
	private  String NickName;
	
	
	@SerializedName("IdCard")
	private  String IdCard;
	
	@SerializedName("Gender")
	private  int Gender;
	
	@SerializedName("UserImg")
	private  String UserImg;

	public long getUserId() {
		return UserId;
	}

	public void setUserId(long userId) {
		UserId = userId;
	}

	public String getTrueName() {
		return TrueName;
	}

	public void setTrueName(String trueName) {
		TrueName = trueName;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getIdCard() {
		return IdCard;
	}

	public void setIdCard(String idCard) {
		IdCard = idCard;
	}

	public int getGender() {
		return Gender;
	}

	public void setGender(int gender) {
		Gender = gender;
	}

	public String getUserImg() {
		return UserImg;
	}

	public void setUserImg(String userImg) {
		UserImg = userImg;
	}
	
//	@SerializedName("")
//	private  String ;
//	
//	@SerializedName("")
//	private  String ;
//	@SerializedName("")
//	private  String ;
//	@SerializedName("")
//	private  String ;
//	@SerializedName("")
//	private  String ;
	
	
	
}
