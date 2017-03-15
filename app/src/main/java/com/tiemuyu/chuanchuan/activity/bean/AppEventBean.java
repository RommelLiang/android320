package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

/**
  * @ClassName: AppEventBean
  * @Description: TODO app事件记录
  * @author hw
  * @date 2015-8-19
  */
public class AppEventBean extends DataPacket {
	
	@SerializedName("EventId")
	private String EventId;//事件id

	@SerializedName("EventName")
	private String EventName;//事件名称
	
	@SerializedName("EventTime")
	private String EventTime;//事件发生时间
	
	@SerializedName("EventValue")
	private String EventValue;//事件值
	
	@SerializedName("Status")
	private String Status;//事件达成状态(1:成功,0:失败)

	public String getEventId() {
		return EventId;
	}

	public void setEventId(String eventId) {
		EventId = eventId;
	}

	public String getEventName() {
		return EventName;
	}

	public void setEventName(String eventName) {
		EventName = eventName;
	}

	public String getEventTime() {
		return EventTime;
	}

	public void setEventTime(String eventTime) {
		EventTime = eventTime;
	}

	public String getEventValue() {
		return EventValue;
	}

	public void setEventValue(String eventValue) {
		EventValue = eventValue;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
	
}
