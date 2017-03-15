package com.tiemuyu.chuanchuan.activity.bean;

import org.xutils.db.annotation.Column;

import com.google.gson.annotations.SerializedName;

public class AccountBean extends DataPacket {


	@SerializedName("UserId")
	private int UserId;//

	@SerializedName("CcCoin")
	private int CcCoin;//

	@SerializedName("Voucher")
	private double Voucher;//

	@SerializedName("AccVoucher")
	private double AccVoucher;//
	
	@SerializedName("FrzCcCoin")
	private int FrzCcCoin;//
	
	
	@SerializedName("FrzVoucher")
	private double FrzVoucher;//
	
	@SerializedName("Amounts")
	private double Amounts;//余额
	
	@Column(name ="FrzAmounts")
	private double FrzAmounts;//冻结零钱
	
	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public int getCcCoin() {
		return CcCoin;
	}

	public void setCcCoin(int ccCoin) {
		CcCoin = ccCoin;
	}

	
	public double getVoucher() {
		return Voucher;
	}

	public void setVoucher(double voucher) {
		Voucher = voucher;
	}

	public double getAccVoucher() {
		return AccVoucher;
	}

	public void setAccVoucher(double accVoucher) {
		AccVoucher = accVoucher;
	}

	public double getFrzVoucher() {
		return FrzVoucher;
	}

	public void setFrzVoucher(double frzVoucher) {
		FrzVoucher = frzVoucher;
	}

	public int getFrzCcCoin() {
		return FrzCcCoin;
	}

	public void setFrzCcCoin(int frzCcCoin) {
		FrzCcCoin = frzCcCoin;
	}

	
	public double getAmounts() {
		return Amounts;
	}

	public void setAmounts(double amounts) {
		Amounts = amounts;
	}

	public double getFrzAmounts() {
		return FrzAmounts;
	}

	public void setFrzAmounts(double frzAmounts) {
		FrzAmounts = frzAmounts;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "资金>穿币:"+getCcCoin()+",现金券:"+getVoucher();
	}
}
