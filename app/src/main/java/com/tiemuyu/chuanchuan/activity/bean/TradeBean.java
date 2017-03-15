package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.annotations.SerializedName;

public class TradeBean extends DataPacket {
/**
 *              "Id": 6,
                "UserId": 371,
                "TradeCoin": -800,
                "TradeVoucher": -200,//
                "TradeMoney": 0,//
                "TradeType": 1,
                "PayType": 1,
                "Summary": "众筹制衣订单支付",
                "OrderNo": "2015010100003",
                "TradeTime": "2015-07-16T10:41:00",
                "CreatorUsername": "441",
                "Description": "穿币支付800元，现金券支付200元",
                "ProductNo": "10100001",
                "ProductName": "性感套装",
                "ProductImg": "http://190.168.2.102:8001/frame/login/default_img.jpg",
                "PkValue": 6,
                "TradeTypeName": "付款",
                "PayTypeName": "微信"
 * */
	@SerializedName("Id")
	private int Id;//
	
	@SerializedName("UserId")
	private int UserId;//
	
	@SerializedName("TradeCoin")
	private double TradeCoin;//穿币
	
	@SerializedName("TradeVoucher")
	private double TradeVoucher;//现金券
	
	@SerializedName("TradeMoney")
	private double TradeMoney;//现金
	
	@SerializedName("TradeType")
	private int TradeType;//
	
	@SerializedName("PayType")
	private int PayType;//
	
	@SerializedName("PkValue")
	private int PkValue;//
	
	@SerializedName("Summary")
	private String Summary;//
	
	@SerializedName("OrderNo")
	private String OrderNo;//
	
	@SerializedName("TradeTime")
	private String TradeTime;//
	
	@SerializedName("CreatorUsername")
	private String CreatorUsername;//
	
	@SerializedName("Description")
	private String Description;//
	
	@SerializedName("ProductNo")
	private String ProductNo;//
	
	@SerializedName("ProductName")
	private String ProductName;//
	
	@SerializedName("ProductImg")
	private String ProductImg;//
	
	@SerializedName("TradeTypeName")
	private String TradeTypeName;//
	
	@SerializedName("PayTypeName")
	private String PayTypeName;//

	
	

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


	public double getTradeCoin() {
		return TradeCoin;
	}

	public void setTradeCoin(double tradeCoin) {
		TradeCoin = tradeCoin;
	}

	public double getTradeVoucher() {
		return TradeVoucher;
	}

	public void setTradeVoucher(double tradeVoucher) {
		TradeVoucher = tradeVoucher;
	}

	public double getTradeMoney() {
		return TradeMoney;
	}

	public void setTradeMoney(double tradeMoney) {
		TradeMoney = tradeMoney;
	}

	public int getTradeType() {
		return TradeType;
	}

	public void setTradeType(int tradeType) {
		TradeType = tradeType;
	}

	public int getPayType() {
		return PayType;
	}

	public void setPayType(int payType) {
		PayType = payType;
	}

	public int getPkValue() {
		return PkValue;
	}

	public void setPkValue(int pkValue) {
		PkValue = pkValue;
	}

	public String getSummary() {
		return Summary;
	}

	public void setSummary(String summary) {
		Summary = summary;
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

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getProductNo() {
		return ProductNo;
	}

	public void setProductNo(String productNo) {
		ProductNo = productNo;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getProductImg() {
		return ProductImg;
	}

	public void setProductImg(String productImg) {
		ProductImg = productImg;
	}

	public String getTradeTypeName() {
		return TradeTypeName;
	}

	public void setTradeTypeName(String tradeTypeName) {
		TradeTypeName = tradeTypeName;
	}

	public String getPayTypeName() {
		return PayTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		PayTypeName = payTypeName;
	}
	
	
	
}
