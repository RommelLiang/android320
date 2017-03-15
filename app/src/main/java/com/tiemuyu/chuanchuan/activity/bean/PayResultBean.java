package com.tiemuyu.chuanchuan.activity.bean;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class PayResultBean implements Serializable{
	
	@Expose
	private String resultStatus;//状态
	
	@Expose
	private String memo;//支付宝提示信息。。微信失败才有信息
	
	@Expose
	private String result;//支付宝签名串，微信没有

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
	
}
