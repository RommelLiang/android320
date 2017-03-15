package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

public class PayBean extends DataPacket {

	/**
	 *"appid": "wxb1e8ffb2177d4697",
    "nonce_str": "D85F40AEC5F840DC86311D599391843A",
    "package": "Sign=WXPay",
    "partnerid": "1253010001",
    "prepayid": "wx20150828170438a05a90077d0719447315",
    "sign": "9F9F70B6B7209C115045B97D6DFE0153",
    "timestamp": "39"
	 * 
	 * */
	
	@SerializedName("appid")
	private String appid;//
	
	@SerializedName("noncestr")
	private String nonce_str;//
	
	@SerializedName("package")
	private String packaged;//
	
	@SerializedName("partnerid")
	private String partnerid;//
	
	@SerializedName("prepayid")
	private String prepayid;//
	
	@SerializedName("sign")
	private String sign;//
	
	@SerializedName("timestamp")
	private String timestamp;//
	
	@SerializedName("ayType")
	private String ayType;//1 微信支付

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getPackaged() {
		return packaged;
	}

	public void setPackaged(String packaged) {
		this.packaged = packaged;
	}

	public String getPartnerid() {
		return partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getAyType() {
		return ayType;
	}

	public void setAyType(String ayType) {
		this.ayType = ayType;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "paybean>appid:"+getAppid()+",nonce_str:"+getNonce_str()+",package:"+getPackaged()
				+",partnerid:"+getPartnerid()+",prepayid:"+getPrepayid()+",sign:"+getSign()
				+",timestamp:"+getTimestamp()
				+",ayType:"+getAyType()
				;
	}
	
	
	
}
