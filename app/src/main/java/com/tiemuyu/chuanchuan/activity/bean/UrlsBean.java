package com.tiemuyu.chuanchuan.activity.bean;

import java.io.Serializable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import com.google.gson.annotations.SerializedName;

@Table(name = "urlstab")
public class UrlsBean implements Serializable {

	@Column(name ="id",isId=true)
	private int id;

	@Column(name ="DefaultUserImage")
	private String DefaultUserImage;

	@Column(name ="Home")
	private String Home;//首页地址

	@Column(name ="AgreementRegister")
	private String AgreementRegister;

	@Column(name ="AgreementRefund")
	private String AgreementRefund;

	@Column(name ="Collocation")
	private String Collocation;

	@Column(name ="CtTest")
	private String CtTest;

	@Column(name ="Help")
	private String Help;//帮助

	@Column(name ="Novice")
	private String Novice;

	@Column(name ="Invite")
	private String Invite;

	@Column(name ="Shared")
	private String Shared;

	@Column(name ="oder")
	@SerializedName("Order")
	private String oder;

	@Column(name ="Product")
	private String Product;

	@Column(name ="ProductList")
	private String ProductList;

	@Column(name ="Fitting3D")
	private String Fitting3D;

	@Column(name ="Address")
	private String Address;

	@Column(name ="Measure")
	private String Measure;

	@Column(name ="VoucherList")
	private String VoucherList;

	@Column(name ="GarmentEdit")
	private String GarmentEdit;

	@Column(name ="Discovery")
	private String Discovery;

	@Column(name ="IOSAppStore")
	private String IOSAppStore;

	@Column(name ="AndroidAppStore")
	private String AndroidAppStore;//应用市场

	@Column(name ="VersionInfo")
	private String VersionInfo;//版本信息

	@Column(name ="About")
	private String About;//关于

	@Column(name ="startImage")
	private String StartImage;//启动页图片
	
	
	@Column(name ="Signature")
	private String Signature;//修改个性签名
	
	@Column(name ="Wallet")
	private String Wallet;//钱包
	
	@Column(name ="CcCoinList")
	private String CcCoinList;//穿币
	
	@Column(name ="RegisterSuccess")
	private String RegisterSuccess;//注册后的奖励界面
	
	@Column(name ="LocalAddress")
	private String LocalAddress;//修改所在地区
	
	@Column(name ="Affiche")
	private String Affiche;//im 公告
	
	@Column(name ="SocailInfo")
	private String SocailInfo;//im 社交资料
	
	@Column(name ="CFList")
	private String CFList;//众筹列表
	
	@Column(name ="MineCFList")
	private String MineCFList;//我的众筹列表
	
	@Column(name ="MinePreList")
	private String MinePreList;//我的美衣
	
	@Column(name ="Service")
	private String Service;//在线客服
	
	@SerializedName("3DShareUpload")
	@Column(name ="U3DShareUpload")
	private String U3DShareUpload;//3D分享上传图片
	
	@Column(name ="UserData")
	private String UserData;//个人资料
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDefaultUserImage(String DefaultUserImage) {
		this.DefaultUserImage = DefaultUserImage;
	}

	public String getDefaultUserImage() {
		return this.DefaultUserImage;
	}

	public void setHome(String Home) {
		this.Home = Home;
	}

	public String getHome() {
		return this.Home;
	}

	public void setAgreementRegister(String AgreementRegister) {
		this.AgreementRegister = AgreementRegister;
	}

	public String getAgreementRegister() {
		return this.AgreementRegister;
	}

	public void setAgreementRefund(String AgreementRefund) {
		this.AgreementRefund = AgreementRefund;
	}

	public String getAgreementRefund() {
		return this.AgreementRefund;
	}

	public void setCollocation(String Collocation) {
		this.Collocation = Collocation;
	}

	public String getCollocation() {
		return this.Collocation;
	}

	public void setCtTest(String CtTest) {
		this.CtTest = CtTest;
	}

	public String getCtTest() {
		return this.CtTest;
	}

	public void setHelp(String Help) {
		this.Help = Help;
	}

	public String getHelp() {
		return this.Help;
	}

	public void setNovice(String Novice) {
		this.Novice = Novice;
	}

	public String getNovice() {
		return this.Novice;
	}

	public void setInvite(String Invite) {
		this.Invite = Invite;
	}

	public String getInvite() {
		return this.Invite;
	}

	public void setShared(String Shared) {
		this.Shared = Shared;
	}

	public String getShared() {
		return this.Shared;
	}

	

	public String getOder() {
		return oder;
	}

	public void setOder(String oder) {
		this.oder = oder;
	}

	public void setProduct(String Product) {
		this.Product = Product;
	}

	public String getProduct() {
		return this.Product;
	}

	public void setProductList(String ProductList) {
		this.ProductList = ProductList;
	}

	public String getProductList() {
		return this.ProductList;
	}

	public void setFitting3D(String Fitting3D) {
		this.Fitting3D = Fitting3D;
	}

	public String getFitting3D() {
		return this.Fitting3D;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}

	public String getAddress() {
		return this.Address;
	}

	public void setMeasure(String Measure) {
		this.Measure = Measure;
	}

	public String getMeasure() {
		return this.Measure;
	}

	public void setVoucherList(String VoucherList) {
		this.VoucherList = VoucherList;
	}

	public String getVoucherList() {
		return this.VoucherList;
	}

	public void setGarmentEdit(String GarmentEdit) {
		this.GarmentEdit = GarmentEdit;
	}

	public String getGarmentEdit() {
		return this.GarmentEdit;
	}

	public void setDiscovery(String Discovery) {
		//this.Discovery = "https://www.baidu.com/";
		this.Discovery = Discovery;
	}

	public String getDiscovery() {
		return this.Discovery;
	}

	public void setIOSAppStore(String IOSAppStore) {
		this.IOSAppStore = IOSAppStore;
	}

	public String getIOSAppStore() {
		return this.IOSAppStore;
	}

	public void setAndroidAppStore(String AndroidAppStore) {
		this.AndroidAppStore = AndroidAppStore;
	}

	public String getAndroidAppStore() {
		return this.AndroidAppStore;
	}

	public void setVersionInfo(String VersionInfo) {
		this.VersionInfo = VersionInfo;
	}

	public String getVersionInfo() {
		return this.VersionInfo;
	}

	public void setAbout(String About) {
		this.About = About;
	}

	public String getAbout() {
		return this.About;
	}

	public String getStartImage() {
		return StartImage;
	}

	public void setStartImage(String startImage) {
		StartImage = startImage;
	}

	
	public String getAffiche() {
		return Affiche;
	}

	public void setAffiche(String affiche) {
		Affiche = affiche;
	}

	public String getSocailInfo() {
		return SocailInfo;
	}

	public void setSocailInfo(String socailInfo) {
		SocailInfo = socailInfo;
	}

	
	public String getSignature() {
		return Signature;
	}

	public void setSignature(String signature) {
		Signature = signature;
	}

	public String getLocalAddress() {
		return LocalAddress;
	}

	public void setLocalAddress(String localAddress) {
		LocalAddress = localAddress;
	}

	
	public String getWallet() {
		return Wallet;
	}

	public void setWallet(String wallet) {
		Wallet = wallet;
	}

	public String getRegisterSuccess() {
		return RegisterSuccess;
	}

	public void setRegisterSuccess(String registerSuccess) {
		RegisterSuccess = registerSuccess;
	}
	

	public String getCFList() {
		return CFList;
	}

	public void setCFList(String cFList) {
		CFList = cFList;
	}

	public String getMineCFList() {
		return MineCFList;
	}

	public void setMineCFList(String mineCFList) {
		MineCFList = mineCFList;
	}

	public String getCcCoinList() {
		return CcCoinList;
	}

	public void setCcCoinList(String ccCoinList) {
		CcCoinList = ccCoinList;
	}

	
	public String getMinePreList() {
		return MinePreList;
	}

	public void setMinePreList(String minePreList) {
		MinePreList = minePreList;
	}

	
	public String getService() {
		return Service;
	}

	public void setService(String service) {
		Service = service;
	}

	
	public String getU3DShareUpload() {
		return U3DShareUpload;
	}

	public void setU3DShareUpload(String u3dShareUpload) {
		U3DShareUpload = u3dShareUpload;
	}

	
	public String getUserData() {
		return UserData;
	}

	public void setUserData(String userData) {
		UserData = userData;
	}

	@Override
	public String toString() {
		return "UrlsBean [id=" + id + ", DefaultUserImage=" + DefaultUserImage
				+ ", Home=" + Home + ", AgreementRegister=" + AgreementRegister
				+ ", AgreementRefund=" + AgreementRefund + ", Collocation="
				+ Collocation + ", CtTest=" + CtTest + ", Help=" + Help
				+ ", Novice=" + Novice + ", Invite=" + Invite + ", Shared="
				+ Shared + ", oder=" + oder + ", Product=" + Product
				+ ", ProductList=" + ProductList + ", Fitting3D=" + Fitting3D
				+ ", Address=" + Address + ", Measure=" + Measure
				+ ", VoucherList=" + VoucherList + ", GarmentEdit="
				+ GarmentEdit + ", Discovery=" + Discovery + ", IOSAppStore="
				+ IOSAppStore + ", AndroidAppStore=" + AndroidAppStore
				+ ", VersionInfo=" + VersionInfo + ", About=" + About
				+ ", StartImage=" + StartImage  
				+",Affiche="+Affiche
				+",SocailInfo="+SocailInfo
				+",MinePreList="+MinePreList
				+",Service="+Service
				+",3Dshareupload="+U3DShareUpload
				+",UserData="+UserData
				+"]";
	}



	

}
