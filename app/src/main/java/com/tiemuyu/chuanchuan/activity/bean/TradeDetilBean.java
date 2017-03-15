package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TradeDetilBean extends DataPacket {

	/**
	 *     "CurrentPage": 1,
        "PageSize": 10,
        "Total": 5,
        "PageUrl": null,
        "Rows": []
	 * */
	@SerializedName("CurrentPage")
	private int CurrentPage;//
	
	
	@SerializedName("PageSize")
	private int PageSize;//
	
	@SerializedName("Total")
	private int Total;//
	
	
	@SerializedName("PageUrl")
	private String PageUrl;//
	
	
	@SerializedName("Rows")
	private List<TradeBean> cash_list ;//


	public int getCurrentPage() {
		return CurrentPage;
	}


	public void setCurrentPage(int currentPage) {
		CurrentPage = currentPage;
	}


	public int getPageSize() {
		return PageSize;
	}


	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}


	public int getTotal() {
		return Total;
	}


	public void setTotal(int total) {
		Total = total;
	}


	public String getPageUrl() {
		return PageUrl;
	}


	public void setPageUrl(String pageUrl) {
		PageUrl = pageUrl;
	}


	public List<TradeBean> getCash_list() {
		return cash_list;
	}


	public void setCash_list(List<TradeBean> cash_list) {
		this.cash_list = cash_list;
	}
	
	
	

}
