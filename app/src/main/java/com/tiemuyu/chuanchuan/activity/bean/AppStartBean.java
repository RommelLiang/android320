package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

/**
  * @ClassName: AppStartBean
  * @Description: TODO APP启动记录
  * @author hw
  * @date 2015-8-19
  */
public class AppStartBean extends DataPacket {
	@SerializedName("StartTime")
	private String StartTime;//启动时间
	
	@SerializedName("Resolution")
	private String Resolution;//设备屏幕分辨率
	
	@SerializedName("DeviceLanguage")
	private String DeviceLanguage;//设备语言
	
	@SerializedName("NetworkType")
	private String NetworkType;//联网方式
	
	@SerializedName("NetworkProvider")
	private String NetworkProvider;//网络运营商
	
	@SerializedName("LocalTime")
	private String LocalTime;//客户端本地时间
	
	@SerializedName("LocalTimeArea")
	private String LocalTimeArea;//客户端时区
	
	@SerializedName("SysNo")
	private String SysNo;//系统标识_DICT

	
	@SerializedName("RegSource")
	private String RegSource;//渠道
	
	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getResolution() {
		return Resolution;
	}

	public void setResolution(String resolution) {
		Resolution = resolution;
	}

	public String getDeviceLanguage() {
		return DeviceLanguage;
	}

	public void setDeviceLanguage(String deviceLanguage) {
		DeviceLanguage = deviceLanguage;
	}

	public String getNetworkType() {
		return NetworkType;
	}

	public void setNetworkType(String networkType) {
		NetworkType = networkType;
	}

	public String getNetworkProvider() {
		return NetworkProvider;
	}

	public void setNetworkProvider(String networkProvider) {
		NetworkProvider = networkProvider;
	}

	public String getLocalTime() {
		return LocalTime;
	}

	public void setLocalTime(String localTime) {
		LocalTime = localTime;
	}

	public String getLocalTimeArea() {
		return LocalTimeArea;
	}

	public void setLocalTimeArea(String localTimeArea) {
		LocalTimeArea = localTimeArea;
	}

	public String getSysNo() {
		return SysNo;
	}

	public void setSysNo(String sysNo) {
		SysNo = sysNo;
	}
	
	public String getRegSource() {
		return RegSource;
	}

	public void setRegSource(String regSource) {
		RegSource = regSource;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "启动日志 StartTime:"+getStartTime()+",Resolution:"+getResolution()+",DeviceLanguage:"+getDeviceLanguage()
				+",NetworkType:"+getNetworkType()+",NetworkProvider:"+getNetworkProvider()
				+",LocalTime:"+getLocalTime()+",LocalTimeArea:"+getLocalTimeArea()
				+",SysNo:"+getSysNo()
				+",RegSource:"+getRegSource()
				;
	}

	
}
