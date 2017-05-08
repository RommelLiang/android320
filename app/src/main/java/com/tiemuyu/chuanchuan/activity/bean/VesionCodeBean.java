package com.tiemuyu.chuanchuan.activity.bean;

/**
 * Created by 梁文硕 on 2017/4/11.
 */

public class VesionCodeBean {


	/**
	 * Code : 1
	 * Msg : OK
	 * Data : {"number":"4.0.6","isForce":0}
	 */

	private int Code;
	private String Msg;
	private DataBean Data;

	public int getCode() {
		return Code;
	}

	public void setCode(int Code) {
		this.Code = Code;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String Msg) {
		this.Msg = Msg;
	}

	public DataBean getData() {
		return Data;
	}

	public void setData(DataBean Data) {
		this.Data = Data;
	}

	public static class DataBean {
		/**
		 * number : 4.0.6
		 * isForce : 0
		 */

		private String number;
		private int isForce;


		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public int getIsForce() {
			return isForce;
		}

		public void setIsForce(int isForce) {
			this.isForce = isForce;
		}
	}
}
