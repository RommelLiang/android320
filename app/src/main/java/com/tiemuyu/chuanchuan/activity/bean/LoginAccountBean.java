package com.tiemuyu.chuanchuan.activity.bean;

import java.io.Serializable;

import org.xutils.db.annotation.Column;

import com.google.gson.annotations.SerializedName;

public class LoginAccountBean implements Serializable{
	/**
	 *   "UserId": 491,
            "CcCoin": 0,
            "Voucher": 0,
            "PkValue": 491
	 * */


	@SerializedName("AccVoucher")
	private double AccVoucher;//

	@SerializedName("CcCoin")
	private int CcCoin;//总穿币

	@SerializedName("Voucher")
	private double Voucher;//

	@SerializedName("FrzCcCoin")
	private int FrzCcCoin;//被冻结的穿币
	
	@SerializedName("FrzVoucher")
	private int FrzVoucher;//被冻结的现金券
	
	@SerializedName("Amounts")
	private double Amounts;//总零钱
	
	@SerializedName("FrzAmounts")
	private double FrzAmounts;//冻结零钱


	public double getAccVoucher() {
		return AccVoucher;
	}

	public void setAccVoucher(double accVoucher) {
		AccVoucher = accVoucher;
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

	public int getFrzCcCoin() {
		return FrzCcCoin;
	}

	public void setFrzCcCoin(int frzCcCoin) {
		FrzCcCoin = frzCcCoin;
	}

	public int getFrzVoucher() {
		return FrzVoucher;
	}

	public void setFrzVoucher(int frzVoucher) {
		FrzVoucher = frzVoucher;
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
	
	
	
	
	
	

	
	
	
}
