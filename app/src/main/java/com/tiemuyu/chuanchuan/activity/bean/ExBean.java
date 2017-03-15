package com.tiemuyu.chuanchuan.activity.bean;

import java.io.Serializable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Table(name="extab")
public class ExBean implements Serializable{
	@Column(name = "id", isId = true)
	private int id;
	
	@Expose
	@SerializedName("Msg")
	@Column(name = "Msg")
	private String Msg;//异常信息标题
	
	@Expose
	@Column(name = "Content")
	@SerializedName("Content")
	private String Content;//异常消息详细内容

	@Expose
	@Column(name = "Code")
	@SerializedName("Code")
	private int Code;//
	
	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public int getCode() {
		return Code;
	}

	public void setCode(int code) {
		Code = code;
	}

	
	

}
