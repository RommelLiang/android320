package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class AppUpdataBean extends DataPacket{
	
	@SerializedName("Urls")
	private UrlsBean urlsBean;

	@SerializedName("Version")
	private VersionBean Version;
	
	@SerializedName("ImServers")
	private String[] ImServers;

	public UrlsBean getUrlsBean() {
		return urlsBean;
	}

	public void setUrlsBean(UrlsBean urlsBean) {
		this.urlsBean = urlsBean;
	}

	public VersionBean getVersion() {
		return Version;
	}

	public void setVersion(VersionBean version) {
		Version = version;
	}

	public String[] getImServers() {
		return ImServers;
	}

	public void setImServers(String[] imServers) {
		ImServers = imServers;
	}


	
	
}
