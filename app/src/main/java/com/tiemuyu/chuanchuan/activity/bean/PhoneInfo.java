package com.tiemuyu.chuanchuan.activity.bean;

public class PhoneInfo extends DataPacket {

	private  String IMEI="";
	private  String ClientType="";//手机型号
	private  String ClientVer="";//Android 版本
	private  String LogonSys="CC_ANDROID";
	private  String Longitude;
	private  String Latitude;
	private  String versionCode;
	private  String versionName;
	private  String data;//时间
	/**
	 * CC/1.0 (Android Simulator; Android 5.0; IMEI/123123123asdada231; Longitude/123.0123123; Latitude/31.432323) Serial/1 Data/20150710
	 * */
	public String getIMEI() {
		return IMEI;
	}
	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}
	public String getClientType() {
		return ClientType;
	}
	public void setClientType(String clientType) {
		ClientType = clientType;
	}
	public String getClientVer() {
		return ClientVer;
	}
	public void setClientVer(String clientVer) {
		ClientVer = clientVer;
	}
	public String getLogonSys() {
		return LogonSys;
	}
	public void setLogonSys(String logonSys) {
		LogonSys = logonSys;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	
	
}
