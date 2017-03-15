package com.tiemuyu.chuanchuan.activity.bean;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class VersionBean implements Serializable{

	
	@SerializedName("Obsolete")
	private boolean Obsolete;//true 强制更新
	
	@SerializedName("DownloadUrl")
	private String DownloadUrl;
	
	@SerializedName("Description")
	private String Description;//更新描述
	
	@SerializedName("VersionCode")
	private String VersionCode;

	public boolean isObsolete() {
		return Obsolete;
	}

	public void setObsolete(boolean obsolete) {
		Obsolete = obsolete;
	}

	public String getDownloadUrl() {
		return DownloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		DownloadUrl = downloadUrl;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getVersionCode() {
		return VersionCode;
	}

	public void setVersionCode(String versionCode) {
		VersionCode = versionCode;
	}
	
	

}
