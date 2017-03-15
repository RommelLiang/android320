package com.tiemuyu.chuanchuan.activity.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import com.google.gson.annotations.SerializedName;


/**
  * @ClassName: User
  * @Description: TODO 用户信息
  * @author hw
  * @date 2015-7-10
  */
@Table(name ="loginbeantab")
public class User extends DataPacket {
	
	@Column(name = "id", isId = true)
	private int id;

	@Column(name ="UserId")
	private int UserId;//用户id
	
	@Column(name ="CcCoin")
	private int CcCoin;//穿币
	
	@Column(name ="Voucher")
	private double Voucher;//金钱
	
	@Column(name ="AccVoucher")
	private double AccVoucher;//
	
	@Column(name ="Username")
	private String Username;//用户名
	
	@Column(name ="NickName")
	private String NickName;//昵称
	
	@Column(name ="UserImg")
	private String UserImg;//用户头像

	@Column(name ="pass")
    private String pass;//用户密码
	
	@Column(name ="oauthId")
	private String oauthId;//第三方的id
	
	
	
	@Column(name ="isThird")
	private int isThird;//是否是第三方登录  0不是 ，1是 
	
	@Column(name ="OauthName")
	private String OauthName;// QQ  Wechar   Sina
	
	@Column(name ="OauthOpenId")
	private String OauthOpenId;//第三方需要的id
	
	@Column(name ="OauthToken")
	private String OauthToken;//第三方的token
	
	@Column(name ="FrzCcCoin")
	private int FrzCcCoin;//被冻结的穿币
	
	@Column(name ="FrzVoucher")
	private double FrzVoucher;//被冻结的现金券
	
	@Column(name ="InviteCode")
	private String InviteCode;//我的邀请码
	
	@Column(name ="Amounts")
	private double Amounts;//总零钱
	
	@Column(name ="FrzAmounts")
	private double FrzAmounts;//冻结零钱
	
	@Column(name ="UserIdentity")
	private String UserIdentity;//用户等级
	
	@Column(name ="Province")
	private String Province;//省
	
	@Column(name ="City")
	private String City;//市
	
	@Column(name ="District")
	private String District;//区
	
	@Column(name ="Signature")
	private String Signature;//个性签名
	
	
	@Column(name ="Email")
	private String Email;//地址
	
	@Column(name ="QQ")
	private String QQ;//QQ
	
	@Column(name ="Wechat")
	private String Wechat;//微信
	
	@Column(name ="Weibo")
	private String Weibo;//新浪微博

	@Column(name ="Accid")
	private String Accid;//新浪微博

	@Column(name ="AccToken")
	private String AccToken;//新浪微博
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getUserId() {
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

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getUserImg() {
		return UserImg;
	}

	public void setUserImg(String userImg) {
		UserImg = userImg;
	}


	public String getOauthId() {
		return oauthId;
	}

	public void setOauthId(String oauthId) {
		this.oauthId = oauthId;
	}
	
	
	public double getAccVoucher() {
		return AccVoucher;
	}

	public void setAccVoucher(double accVoucher) {
		AccVoucher = accVoucher;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	
	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	
	public int getIsThird() {
		return isThird;
	}

	public void setIsThird(int isThird) {
		this.isThird = isThird;
	}

	
	
	public String getOauthName() {
		return OauthName;
	}

	public void setOauthName(String oauthName) {
		OauthName = oauthName;
	}

	
	public String getOauthOpenId() {
		return OauthOpenId;
	}

	public void setOauthOpenId(String oauthOpenId) {
		OauthOpenId = oauthOpenId;
	}

	public String getOauthToken() {
		return OauthToken;
	}

	public void setOauthToken(String oauthToken) {
		OauthToken = oauthToken;
	}

	public int getFrzCcCoin() {
		return FrzCcCoin;
	}

	public void setFrzCcCoin(int frzCcCoin) {
		FrzCcCoin = frzCcCoin;
	}

	public double getFrzVoucher() {
		return FrzVoucher;
	}

	public void setFrzVoucher(double frzVoucher) {
		FrzVoucher = frzVoucher;
	}

	
	public String getInviteCode() {
		return InviteCode;
	}

	public void setInviteCode(String inviteCode) {
		InviteCode = inviteCode;
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

	
	public String getUserIdentity() {
		return UserIdentity;
	}

	public void setUserIdentity(String userIdentity) {
		UserIdentity = userIdentity;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public String getSignature() {
		return Signature;
	}

	public void setSignature(String signature) {
		Signature = signature;
	}

	
	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public String getWechat() {
		return Wechat;
	}

	public void setWechat(String wechat) {
		Wechat = wechat;
	}

	public String getWeibo() {
		return Weibo;
	}

	public void setWeibo(String weibo) {
		Weibo = weibo;
	}


	public void setAccid(String accid) {
		Accid = accid;
	}

	public void setAccToken(String accToken) {
		AccToken = accToken;
	}



	public String getAccid() {
		return Accid;
	}
	public String getAccToken() {
		return AccToken;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "用户信息>userid:"+getUserId()
				+",昵称:"+getNickName()
				+",账户:"+getUsername()
				+",头像地址:"+getUserImg()
				+",用户密码:"+getPass()
				+",是否是第三方登录:"+getIsThird()
				+",Voucger:"+getVoucher()
				+",冻结:"+getFrzVoucher()
				+",邀请码:"+getInviteCode()
				+",零钱总是:"+getAmounts()
				+",被冻结零钱:"+getFrzAmounts()
				+",穿币总是:"+getCcCoin()
				+",被冻结穿币:"+getFrzCcCoin()
				+",省:"+getProvince()
				+",市:"+getCity()
				+",区:"+getDistrict()
				+",个性签名:"+getSignature()
				+",QQ："+getQQ()
				+",Email："+getEmail()
				+",微信："+getWechat()
				+",新浪微博："+getWeibo()
				+",Accid："+getAccid()
				+",AccToken："+getAccToken();

	}
	
	
}
