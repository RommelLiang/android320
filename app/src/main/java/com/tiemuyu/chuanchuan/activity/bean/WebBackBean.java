package com.tiemuyu.chuanchuan.activity.bean;

/**
  * @ClassName: WebBackBean
  * @Description: TODO html返回
  * @author hw
  * @date 2015-9-7
  */
public class WebBackBean extends DataPacket {
	
	private String show;
	
	private String url;

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
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
		return "webview:"+getShow()+",url:"+getUrl();
	}
	

}
