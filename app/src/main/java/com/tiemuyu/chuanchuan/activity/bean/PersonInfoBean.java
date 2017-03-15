package com.tiemuyu.chuanchuan.activity.bean;


public class PersonInfoBean extends DataPacket {
	/**
	 *"Height": 165,
    "Weight": 12,
    "Age": 40,
    "Physique": "S形",
    "TopSize": "M",
    "Waistband": 28,
    "CtType": 1,
    "CtTypeName": "街头潮女"
	 * 
	 * */

	private int UserId;

	private int Waistband;

	private int CtType;

	private int Age;

	private int Height;

	private int Weight;

	private int Bust;//胸围

	private int Waist;//腰围

	private int Hip;

	private int Pants;

	private int Sleeve;

	private String CreatorDate;

	private String UpdateDate;

	private String Physique;

	private String TopSize;

	private String CreatorUsername;

	private String Rmk;

	private String UpdateUsername;

	private int PkValue;

	private String CtTypeName;

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}

	public int getWeight() {
		return Weight;
	}

	public void setWeight(int weight) {
		Weight = weight;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public int getWaistband() {
		return Waistband;
	}

	public void setWaistband(int waistband) {
		Waistband = waistband;
	}

	public int getCtType() {
		return CtType;
	}

	public void setCtType(int ctType) {
		CtType = ctType;
	}

	public String getPhysique() {
		return Physique;
	}

	public void setPhysique(String physique) {
		Physique = physique;
	}

	public String getTopSize() {
		return TopSize;
	}

	public void setTopSize(String topSize) {
		TopSize = topSize;
	}

	public String getCtTypeName() {
		return CtTypeName;
	}

	public void setCtTypeName(String ctTypeName) {
		CtTypeName = ctTypeName;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public int getBust() {
		return Bust;
	}

	public void setBust(int bust) {
		Bust = bust;
	}

	public int getWaist() {
		return Waist;
	}

	public void setWaist(int waist) {
		Waist = waist;
	}

	public int getHip() {
		return Hip;
	}

	public void setHip(int hip) {
		Hip = hip;
	}

	public int getPants() {
		return Pants;
	}

	public void setPants(int pants) {
		Pants = pants;
	}

	public int getSleeve() {
		return Sleeve;
	}

	public void setSleeve(int sleeve) {
		Sleeve = sleeve;
	}

	public String getCreatorDate() {
		return CreatorDate;
	}

	public void setCreatorDate(String creatorDate) {
		CreatorDate = creatorDate;
	}

	public String getUpdateDate() {
		return UpdateDate;
	}

	public void setUpdateDate(String updateDate) {
		UpdateDate = updateDate;
	}

	public String getCreatorUsername() {
		return CreatorUsername;
	}

	public void setCreatorUsername(String creatorUsername) {
		CreatorUsername = creatorUsername;
	}

	public String getRmk() {
		return Rmk;
	}

	public void setRmk(String rmk) {
		Rmk = rmk;
	}

	public String getUpdateUsername() {
		return UpdateUsername;
	}

	public void setUpdateUsername(String updateUsername) {
		UpdateUsername = updateUsername;
	}

	public int getPkValue() {
		return PkValue;
	}

	public void setPkValue(int pkValue) {
		PkValue = pkValue;
	}
	
	
	
}
