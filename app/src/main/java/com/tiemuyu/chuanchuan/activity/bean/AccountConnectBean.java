package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

/**
  * @ClassName: AccountConnectBean
  * @Description: TODO 账号关联的信息
  * @author hw
  * @date 2015-7-20
  */
public class AccountConnectBean extends DataPacket {

	/**
	 *  "Id": 1,
            "UserId": 371,
            "OauthName": "QQ",
            "OauthOpenId": "12312313",
            "OauthToken": "123131231",
            "NickName": "XIAOWU",
            "UserImg": "",
            "LastLoginTime": "1900-01-01T00:00:00",
            "CreatrdDate": "2015-07-14T14:22:26Z",
            "PkValue": 1
	 * */
	@SerializedName("Id")
	private int Id;//
	
	@SerializedName("UserId")
	private int UserId;//
	
	@SerializedName("OauthName")
	private String OauthName;//
	
	@SerializedName("OauthOpenId")
	private String OauthOpenId;//
	
	@SerializedName("OauthToken")
	private String OauthToken;//
	
	@SerializedName("NickName")
	private String NickName;//
	
	@SerializedName("UserImg")
	private String UserImg;//
	
	@SerializedName("LastLoginTime")
	private String LastLoginTime;//
	
	@SerializedName("CreatrdDate")
	private String CreatrdDate;//

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getOauthName() {
		return OauthName;
	}

	public void setOauthName(String oauthName) {
		OauthName = oauthName;
	}

	public String getOauthOpenId() {
		return OauthOpenId;
	}

	public void setOauthOpenId(String oauthOpenId) {
		OauthOpenId = oauthOpenId;
	}

	public String getOauthToken() {
		return OauthToken;
	}

	public void setOauthToken(String oauthToken) {
		OauthToken = oauthToken;
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

	public String getLastLoginTime() {
		return LastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		LastLoginTime = lastLoginTime;
	}

	public String getCreatrdDate() {
		return CreatrdDate;
	}

	public void setCreatrdDate(String creatrdDate) {
		CreatrdDate = creatrdDate;
	}
	
	
	
}
