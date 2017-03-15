package com.tiemuyu.chuanchuan.activity.bean;

import org.xutils.db.annotation.Table;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
  * @ClassName: AppAccessBean
  * @Description: TODO app访问记录
  * @author hw
  * @date 2015-8-19
  */
@Table(name="appaccesstab")
public class AppAccessBean extends DataPacket {

	private int id;
	
	@Expose
	@SerializedName("ViewId")
	private String ViewId;//视图id

	@Expose
	@SerializedName("PreviousViewId")
	private String PreviousViewId;//上一视图id
	
	@Expose
	@SerializedName("StartTime")
	private String StartTime;//进入时间
	
	@Expose
	@SerializedName("ExitTime")
	private String ExitTime;//退出时间

	
	
//   private static AppAccessBean appAccessBean;
//	
//	private AppAccessBean(Context context){
//		appAccessBean=new AppAccessBean(context);
//	}
//	
//	public static synchronized AppAccessBean getAppAccess(Context context){
//		if(appAccessBean==null){
//			appAccessBean=new AppAccessBean(context);
//		}
//		return appAccessBean;
//	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getViewId() {
		return ViewId;
	}

	public void setViewId(String viewId) {
		ViewId = viewId;
	}

	public String getPreviousViewId() {
		return PreviousViewId;
	}

	public void setPreviousViewId(String previousViewId) {
		PreviousViewId = previousViewId;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getExitTime() {
		return ExitTime;
	}

	public void setExitTime(String exitTime) {
		ExitTime = exitTime;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "app访问记录,ViewId:"+getViewId()+",PreviousViewId:"+getPreviousViewId()
				+",StartTime:"+getStartTime()+",ExitTime:"+getExitTime()
				;
	}
	
}
