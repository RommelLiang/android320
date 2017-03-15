package com.tiemuyu.chuanchuan.activity.bean;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class UpImgBean implements Serializable{
	//"ImageUrl":"http://190.168.1.80:8001/frame/login/default_img.jpg"
	@SerializedName("ImageUrl")
	private String ImageUrl;

	public String getImageUrl() {
		return ImageUrl;
	}

	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}

	
}
