package com.tiemuyu.chuanchuan.activity.bean;

public class ActCpModelBean extends DataPacket {

	
//	  "Id": 3,
//      "CpType": 1,
//      "ParValue": 500,
//      "CpNum": 1,
//      "ActCode": "register",
//      "ToplimitNum": 0,
//      "CurrentNum": 71,
//      "EffectTime": "1900-01-01T00:00:00",
//      "InvalidTime": "2100-01-01T00:00:00",
//      "PkValue": 3
	
	
	private int Id;

	private int CpType;

	private int ParValue;

	private int CpNum;

	private String ActCode;

	private int ToplimitNum;

	private int CurrentNum;

	private String EffectTime;

	private String InvalidTime;

	private int PkValue;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getCpType() {
		return CpType;
	}

	public void setCpType(int cpType) {
		CpType = cpType;
	}

	public int getParValue() {
		return ParValue;
	}

	public void setParValue(int parValue) {
		ParValue = parValue;
	}

	public int getCpNum() {
		return CpNum;
	}

	public void setCpNum(int cpNum) {
		CpNum = cpNum;
	}

	public String getActCode() {
		return ActCode;
	}

	public void setActCode(String actCode) {
		ActCode = actCode;
	}

	public int getToplimitNum() {
		return ToplimitNum;
	}

	public void setToplimitNum(int toplimitNum) {
		ToplimitNum = toplimitNum;
	}

	public int getCurrentNum() {
		return CurrentNum;
	}

	public void setCurrentNum(int currentNum) {
		CurrentNum = currentNum;
	}

	public String getEffectTime() {
		return EffectTime;
	}

	public void setEffectTime(String effectTime) {
		EffectTime = effectTime;
	}

	public String getInvalidTime() {
		return InvalidTime;
	}

	public void setInvalidTime(String invalidTime) {
		InvalidTime = invalidTime;
	}

	public int getPkValue() {
		return PkValue;
	}

	public void setPkValue(int pkValue) {
		PkValue = pkValue;
	}
	
	
	@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "--奖励信息:CpType"+getCpType()+",parvalue:"+getParValue();
		}
	
	
}
