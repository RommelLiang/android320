package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

public class GetPassKey extends BaseBean {


	@SerializedName("Data")
	private PassKeyBean Data;



	public PassKeyBean getData() {
		return Data;
	}



	public void setData(PassKeyBean data) {
		Data = data;
	}



	public class PassKeyBean extends DataPacket{
		@SerializedName("PassKey")
		private String PassKey;

		public String getPassKey() {
			return PassKey;
		}

		public void setPassKey(String passKey) {
			PassKey = passKey;
		}
		
		
		
	}
	
}
