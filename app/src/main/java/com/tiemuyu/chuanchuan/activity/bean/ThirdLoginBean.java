package com.tiemuyu.chuanchuan.activity.bean;

/**
  * @ClassName: ThirdLoginBean
  * @Description: TODO 第三方登录信息
  * @author hw
  * @date 2015-7-28
  */
public class ThirdLoginBean extends DataPacket {

	private String OauthName;
	private String OauthOpenId;
	private String OauthToken;
	private String UserImg;
	private String NickName;
	private String OauthId;
	
	
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
	public String getOauthId() {
		return OauthId;
	}
	public void setOauthId(String oauthId) {
		OauthId = oauthId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "第三方登录字段:nickname:"+getNickName()+",OauthId:"
				
				+getOauthId()
				+",OauthName:"+getOauthName()
				+",OauthOpenId:"+getOauthOpenId()
				+",OauthToken:"+getOauthToken()
				+",UserImg:"+getUserImg()
				;
	}
	
}
