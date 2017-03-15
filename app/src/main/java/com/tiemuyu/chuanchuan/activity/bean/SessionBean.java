package com.tiemuyu.chuanchuan.activity.bean;

import java.io.Serializable;
/**webview 的Session*/
public class SessionBean implements Serializable{

	private String sessionKey;
	
	private String sessionValue;

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getSessionValue() {
		return sessionValue;
	}

	public void setSessionValue(String sessionValue) {
		this.sessionValue = sessionValue;
	}
	
	
}
