package com.tiemuyu.chuanchuan.activity.bean;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDataBean implements Serializable{
	
	@Expose
	@SerializedName("OnlineUser")
	private LoginOnlineUserBean onlineUserBean;
	
	@Expose
	@SerializedName("IsLoginOk")
	private boolean IsLoginOk;
	//  "IsLoginOk": true,
	
	@Expose
	@SerializedName("LoginCode")
	private int LoginCode;//6 未注册过
	
	@Expose
	@SerializedName("Account")
	private LoginAccountBean accountBean;
	
	@Expose
	@SerializedName("SocialInfo")
	private SocialInfoBean socialInfoBean;
	
	@Expose
	@SerializedName("Oauths")
	private OauthsBean Oauths;

	@Expose
	@SerializedName("Accid")
	private String Accid;

	@Expose
	@SerializedName("AccToken")
	private String AccToken;


	public String getAccid() {
		return Accid;
	}
	public String getAccToken() {
		return AccToken;
	}



	public LoginOnlineUserBean getOnlineUserBean() {
		return onlineUserBean;
	}

	public void setOnlineUserBean(LoginOnlineUserBean onlineUserBean) {
		this.onlineUserBean = onlineUserBean;
	}


	public SocialInfoBean getSocialInfoBean() {
		return socialInfoBean;
	}

	public void setSocialInfoBean(SocialInfoBean socialInfoBean) {
		this.socialInfoBean = socialInfoBean;
	}

	public LoginAccountBean getAccountBean() {
		return accountBean;
	}

	public void setAccountBean(LoginAccountBean accountBean) {
		this.accountBean = accountBean;
	}

	public boolean isIsLoginOk() {
		return IsLoginOk;
	}

	public void setIsLoginOk(boolean isLoginOk) {
		IsLoginOk = isLoginOk;
	}

	public int getLoginCode() {
		return LoginCode;
	}

	public void setLoginCode(int loginCode) {
		LoginCode = loginCode;
	}

	public OauthsBean getOauths() {
		return Oauths;
	}

	public void setOauths(OauthsBean oauths) {
		Oauths = oauths;
	}
	
	

}
