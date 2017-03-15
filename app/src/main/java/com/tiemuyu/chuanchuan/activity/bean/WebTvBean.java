package com.tiemuyu.chuanchuan.activity.bean;

public class WebTvBean extends DataPacket {
	private String show;

	private String text;
	
	private String url;

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "文字显示:"+getShow()+",url:"+getUrl()+",text:"+getText();
	}
}
