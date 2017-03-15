package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

public class CashRollBean extends DataPacket {
/**
 *   "Id": 1,
    "UserId": 12,
    "TradeNo": "20150501101200001",
    "TradeVoucher": 500,
    "BeforeTrade": 23.21,
    "AfterTrade": 523.21,
    "TradeType": 1,
    "Summary": "注册穿穿账户",
    "PayType": "微信支付",
    "OrderNo": "2012050100001",
    "TradeTime": "2015-02-0111: 12: 13",
    "CreatorUsername": "test1"
 * */
	
	@SerializedName("Id")
	private int Id;//
	
	@SerializedName("UserId")
	private int UserId;//
	
	
	@SerializedName("TradeNo")
	private String TradeNo;//
	
	@SerializedName("TradeVoucher")
	private double TradeVoucher;//
	
	@SerializedName("BeforeTrade")
	private double BeforeTrade;//
	
	@SerializedName("AfterTrade")
	private double AfterTrade;//
	
	
	@SerializedName("TradeType")
	private int TradeType;//
	
	@SerializedName("Summary")
	private String Summary;//
	
	@SerializedName("PayType")
	private String PayType;//
	
	
	@SerializedName("OrderNo")
	private String OrderNo;//
	
	
	@SerializedName("TradeTime")
	private String TradeTime;//
	
	@SerializedName("CreatorUsername")
	private String CreatorUsername;//

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getTradeNo() {
		return TradeNo;
	}

	public void setTradeNo(String tradeNo) {
		TradeNo = tradeNo;
	}


	public double getTradeVoucher() {
		return TradeVoucher;
	}

	public void setTradeVoucher(double tradeVoucher) {
		TradeVoucher = tradeVoucher;
	}

	public double getBeforeTrade() {
		return BeforeTrade;
	}

	public void setBeforeTrade(double beforeTrade) {
		BeforeTrade = beforeTrade;
	}

	public double getAfterTrade() {
		return AfterTrade;
	}

	public void setAfterTrade(double afterTrade) {
		AfterTrade = afterTrade;
	}

	public int getTradeType() {
		return TradeType;
	}

	public void setTradeType(int tradeType) {
		TradeType = tradeType;
	}

	public String getSummary() {
		return Summary;
	}

	public void setSummary(String summary) {
		Summary = summary;
	}

	public String getPayType() {
		return PayType;
	}

	public void setPayType(String payType) {
		PayType = payType;
	}

	public String getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}

	public String getTradeTime() {
		return TradeTime;
	}

	public void setTradeTime(String tradeTime) {
		TradeTime = tradeTime;
	}

	public String getCreatorUsername() {
		return CreatorUsername;
	}

	public void setCreatorUsername(String creatorUsername) {
		CreatorUsername = creatorUsername;
	}
	
	
	
	
}
