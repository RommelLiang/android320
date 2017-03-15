package com.tiemuyu.chuanchuan.activity.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import com.google.gson.annotations.SerializedName;

/**
  * @ClassName: NewProductBean
  * @Description: TODO 新品推荐
  * @author hw
  * @date 2015-7-31
  */
@Table(name ="newproducttab")
public class NewProductBean extends DataPacket {
	@Column(name = "id", isId = true)
	private int id;
	
	@SerializedName("CfDays")
	@Column(name ="CfDays")
	private int CfDays;

	@SerializedName("CfNumber")
	@Column(name ="CfNumber")
	private int CfNumber;

	@SerializedName("OnShelfTime")
	@Column(name ="OnShelfTime")
	private String OnShelfTime;

	@SerializedName("OffShelfTime")
	@Column(name ="OffShelfTime")
	private String OffShelfTime;

	@SerializedName("MainImg")
	@Column(name ="MainImg")
	private String MainImg;

	@SerializedName("Url")
	@Column(name ="Url")
	private String Url;

	@SerializedName("ViewTimes")
	@Column(name ="ViewTimes")
	private int ViewTimes;

	@SerializedName("PraiseTimes")
	@Column(name ="PraiseTimes")
	private int PraiseTimes;

	@SerializedName("Inspiration")
	@Column(name ="Inspiration")
	private String Inspiration;

	@SerializedName("GroupName")
	@Column(name ="GroupName")
	private String GroupName;

	@SerializedName("GroupNo")
	@Column(name ="GroupNo")
	private String GroupNo;

	@SerializedName("IsNew")
	@Column(name ="IsNew")
	private boolean IsNew;

	@SerializedName("ProductFee")
	@Column(name ="ProductFee")
	private double ProductFee;

	@SerializedName("MaxVoucher")
	@Column(name ="MaxVoucher")
	private double MaxVoucher;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCfDays() {
		return CfDays;
	}

	public void setCfDays(int cfDays) {
		CfDays = cfDays;
	}

	public int getCfNumber() {
		return CfNumber;
	}

	public void setCfNumber(int cfNumber) {
		CfNumber = cfNumber;
	}

	public String getOnShelfTime() {
		return OnShelfTime;
	}

	public void setOnShelfTime(String onShelfTime) {
		OnShelfTime = onShelfTime;
	}

	public String getOffShelfTime() {
		return OffShelfTime;
	}

	public void setOffShelfTime(String offShelfTime) {
		OffShelfTime = offShelfTime;
	}

	public String getMainImg() {
		return MainImg;
	}

	public void setMainImg(String mainImg) {
		MainImg = mainImg;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public int getViewTimes() {
		return ViewTimes;
	}

	public void setViewTimes(int viewTimes) {
		ViewTimes = viewTimes;
	}

	public int getPraiseTimes() {
		return PraiseTimes;
	}

	public void setPraiseTimes(int praiseTimes) {
		PraiseTimes = praiseTimes;
	}

	public String getInspiration() {
		return Inspiration;
	}

	public void setInspiration(String inspiration) {
		Inspiration = inspiration;
	}

	public String getGroupName() {
		return GroupName;
	}

	public void setGroupName(String groupName) {
		GroupName = groupName;
	}

	public String getGroupNo() {
		return GroupNo;
	}

	public void setGroupNo(String groupNo) {
		GroupNo = groupNo;
	}

	public boolean isIsNew() {
		return IsNew;
	}

	public void setIsNew(boolean isNew) {
		IsNew = isNew;
	}

	public double getProductFee() {
		return ProductFee;
	}

	public void setProductFee(double productFee) {
		ProductFee = productFee;
	}

	public double getMaxVoucher() {
		return MaxVoucher;
	}

	public void setMaxVoucher(double maxVoucher) {
		MaxVoucher = maxVoucher;
	}
	
	
	
	
}
