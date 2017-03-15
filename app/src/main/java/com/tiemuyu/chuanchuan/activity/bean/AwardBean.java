package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class AwardBean extends DataPacket {

	private int Id;

	private String ActCode;

	private String ActName;

	private int ActType;

	private String ActStartDate;

	private String ActEndDate;

	private int MaxPartTimes;

	private String Description;

	private String ActUrl;

	private int IsDisabled;

	private int IsDelete;

	private String Rmk;

	private String CreatorDate;

	private String CreatorUsername;

	private String UpdateDate;

	private String UpdateUsername;

	private String ShareTitle;

	private String ShareContent;

	private String ShareUrl;

	private String ShareImage;

	private int PkValue;

	@SerializedName("ActCpModels")
	private List<ActCpModelBean>ActCpModels;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getActCode() {
		return ActCode;
	}

	public void setActCode(String actCode) {
		ActCode = actCode;
	}

	public String getActName() {
		return ActName;
	}

	public void setActName(String actName) {
		ActName = actName;
	}

	public int getActType() {
		return ActType;
	}

	public void setActType(int actType) {
		ActType = actType;
	}

	public String getActStartDate() {
		return ActStartDate;
	}

	public void setActStartDate(String actStartDate) {
		ActStartDate = actStartDate;
	}

	public String getActEndDate() {
		return ActEndDate;
	}

	public void setActEndDate(String actEndDate) {
		ActEndDate = actEndDate;
	}

	public int getMaxPartTimes() {
		return MaxPartTimes;
	}

	public void setMaxPartTimes(int maxPartTimes) {
		MaxPartTimes = maxPartTimes;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getActUrl() {
		return ActUrl;
	}

	public void setActUrl(String actUrl) {
		ActUrl = actUrl;
	}

	public int getIsDisabled() {
		return IsDisabled;
	}

	public void setIsDisabled(int isDisabled) {
		IsDisabled = isDisabled;
	}

	public int getIsDelete() {
		return IsDelete;
	}

	public void setIsDelete(int isDelete) {
		IsDelete = isDelete;
	}

	public String getRmk() {
		return Rmk;
	}

	public void setRmk(String rmk) {
		Rmk = rmk;
	}

	public String getCreatorDate() {
		return CreatorDate;
	}

	public void setCreatorDate(String creatorDate) {
		CreatorDate = creatorDate;
	}

	public String getCreatorUsername() {
		return CreatorUsername;
	}

	public void setCreatorUsername(String creatorUsername) {
		CreatorUsername = creatorUsername;
	}

	public String getUpdateDate() {
		return UpdateDate;
	}

	public void setUpdateDate(String updateDate) {
		UpdateDate = updateDate;
	}

	public String getUpdateUsername() {
		return UpdateUsername;
	}

	public void setUpdateUsername(String updateUsername) {
		UpdateUsername = updateUsername;
	}

	public String getShareTitle() {
		return ShareTitle;
	}

	public void setShareTitle(String shareTitle) {
		ShareTitle = shareTitle;
	}

	public String getShareContent() {
		return ShareContent;
	}

	public void setShareContent(String shareContent) {
		ShareContent = shareContent;
	}

	public String getShareUrl() {
		return ShareUrl;
	}

	public void setShareUrl(String shareUrl) {
		ShareUrl = shareUrl;
	}

	public String getShareImage() {
		return ShareImage;
	}

	public void setShareImage(String shareImage) {
		ShareImage = shareImage;
	}

	public int getPkValue() {
		return PkValue;
	}

	public void setPkValue(int pkValue) {
		PkValue = pkValue;
	}

	public List<ActCpModelBean> getActCpModels() {
		return ActCpModels;
	}

	public void setActCpModels(List<ActCpModelBean> actCpModels) {
		ActCpModels = actCpModels;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "awardbean.奖励信息:分享内容:"+getShareContent()+",分享标题:"+getShareTitle()+",分享地址:"+getShareUrl();
	}
	
	
}
