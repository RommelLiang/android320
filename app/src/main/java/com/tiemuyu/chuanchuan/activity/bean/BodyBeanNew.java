package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梁文硕 on 2017/4/17.
 */

public class BodyBeanNew {

	/**
	 * Code : 1
	 * Msg : OK
	 * Data : {"userCCInfoList":{"ID":41,"USER_ID":97238,"Name":"莱茵哈特","HEIGHT":5,"WEIGHT":99,"AGE":55,"PHYSIQUE":"苹果型","TOP_SIZE":"","WAISTBAND":0,"CT_TYPE":0,"CREATOR_DATE":"2017-04-17 10:58:50","CREATOR_USERNAME":"17688159789","RMK":"","UPDATE_DATE":"2017-04-17 10:58:50","UPDATE_USERNAME":"17688159789","BUST":12,"WAIST":1,"HIP":1,"PANTS":1,"SLEEVE":1,"DRESS_EFFECT":0,"WAIST_POSTION":0,"BMI":0,"SKIN_COLOR":"s","GENDER":1,"IS_CT_TEST":0,"CLOTH_SIZE":"L","SHOULDER_BREADTH":12,"ARM_CIRC":1,"THIGH_CIRC":22,"KNEE_CIRC":1,"CALF_CIRC":12,"BODY_PIC":"s","LOGO":"s","CHUANYIXUG":0,"TEBIESHUOMING":"s","GEXINGMINGCHENG":"s","ISRADIO":0}}
	 */

	private int Code;
	private String Msg;
	private DataBean Data;

	public static BodyBeanNew objectFromData(String str) {

		return new Gson().fromJson(str, BodyBeanNew.class);
	}

	public static BodyBeanNew objectFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);

			return new Gson().fromJson(jsonObject.getString(str), BodyBeanNew.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<BodyBeanNew> arrayBodyBeanNewFromData(String str) {

		Type listType = new TypeToken<ArrayList<BodyBeanNew>>() {
		}.getType();

		return new Gson().fromJson(str, listType);
	}

	public static List<BodyBeanNew> arrayBodyBeanNewFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);
			Type listType = new TypeToken<ArrayList<BodyBeanNew>>() {
			}.getType();

			return new Gson().fromJson(jsonObject.getString(str), listType);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return new ArrayList();


	}

	public int getCode() {
		return Code;
	}

	public void setCode(int Code) {
		this.Code = Code;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String Msg) {
		this.Msg = Msg;
	}

	public DataBean getData() {
		return Data;
	}

	public void setData(DataBean Data) {
		this.Data = Data;
	}

	public static class DataBean {
		/**
		 * userCCInfoList : {"ID":41,"USER_ID":97238,"Name":"莱茵哈特","HEIGHT":5,"WEIGHT":99,"AGE":55,"PHYSIQUE":"苹果型","TOP_SIZE":"","WAISTBAND":0,"CT_TYPE":0,"CREATOR_DATE":"2017-04-17 10:58:50","CREATOR_USERNAME":"17688159789","RMK":"","UPDATE_DATE":"2017-04-17 10:58:50","UPDATE_USERNAME":"17688159789","BUST":12,"WAIST":1,"HIP":1,"PANTS":1,"SLEEVE":1,"DRESS_EFFECT":0,"WAIST_POSTION":0,"BMI":0,"SKIN_COLOR":"s","GENDER":1,"IS_CT_TEST":0,"CLOTH_SIZE":"L","SHOULDER_BREADTH":12,"ARM_CIRC":1,"THIGH_CIRC":22,"KNEE_CIRC":1,"CALF_CIRC":12,"BODY_PIC":"s","LOGO":"s","CHUANYIXUG":0,"TEBIESHUOMING":"s","GEXINGMINGCHENG":"s","ISRADIO":0}
		 */

		private UserCCInfoListBean userCCInfoList;

		public static DataBean objectFromData(String str) {

			return new Gson().fromJson(str, DataBean.class);
		}

		public static DataBean objectFromData(String str, String key) {

			try {
				JSONObject jsonObject = new JSONObject(str);

				return new Gson().fromJson(jsonObject.getString(str), DataBean.class);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		public static List<DataBean> arrayDataBeanFromData(String str) {

			Type listType = new TypeToken<ArrayList<DataBean>>() {
			}.getType();

			return new Gson().fromJson(str, listType);
		}

		public static List<DataBean> arrayDataBeanFromData(String str, String key) {

			try {
				JSONObject jsonObject = new JSONObject(str);
				Type listType = new TypeToken<ArrayList<DataBean>>() {
				}.getType();

				return new Gson().fromJson(jsonObject.getString(str), listType);

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return new ArrayList();


		}

		public UserCCInfoListBean getUserCCInfoList() {
			return userCCInfoList;
		}

		public void setUserCCInfoList(UserCCInfoListBean userCCInfoList) {
			this.userCCInfoList = userCCInfoList;
		}

		public static class UserCCInfoListBean {
			/**
			 * ID : 41
			 * USER_ID : 97238
			 * Name : 莱茵哈特
			 * HEIGHT : 5.0
			 * WEIGHT : 99.0
			 * AGE : 55
			 * PHYSIQUE : 苹果型
			 * TOP_SIZE :
			 * WAISTBAND : 0
			 * CT_TYPE : 0
			 * CREATOR_DATE : 2017-04-17 10:58:50
			 * CREATOR_USERNAME : 17688159789
			 * RMK :
			 * UPDATE_DATE : 2017-04-17 10:58:50
			 * UPDATE_USERNAME : 17688159789
			 * BUST : 12.0
			 * WAIST : 1.0
			 * HIP : 1.0
			 * PANTS : 1.0
			 * SLEEVE : 1.0
			 * DRESS_EFFECT : 0
			 * WAIST_POSTION : 0
			 * BMI : 0.0
			 * SKIN_COLOR : s
			 * GENDER : 1
			 * IS_CT_TEST : 0
			 * CLOTH_SIZE : L
			 * SHOULDER_BREADTH : 12.0
			 * ARM_CIRC : 1.0
			 * THIGH_CIRC : 22.0
			 * KNEE_CIRC : 1.0
			 * CALF_CIRC : 12.0
			 * BODY_PIC : s
			 * LOGO : s
			 * CHUANYIXUG : 0
			 * TEBIESHUOMING : s
			 * GEXINGMINGCHENG : s
			 * ISRADIO : 0
			 */

			private int ID;
			private int USER_ID;
			private String Name;
			private double HEIGHT;
			private double WEIGHT;
			private int AGE;
			private String PHYSIQUE;
			private String TOP_SIZE;
			private int WAISTBAND;
			private int CT_TYPE;
			private String CREATOR_DATE;
			private String CREATOR_USERNAME;
			private String RMK;
			private String UPDATE_DATE;
			private String UPDATE_USERNAME;
			private double BUST;
			private double WAIST;
			private double HIP;
			private double PANTS;
			private double SLEEVE;
			private int DRESS_EFFECT;
			private int WAIST_POSTION;
			private double BMI;
			private String SKIN_COLOR;
			private int GENDER;
			private int IS_CT_TEST;
			private String CLOTH_SIZE;
			private double SHOULDER_BREADTH;
			private double ARM_CIRC;
			private double THIGH_CIRC;
			private double KNEE_CIRC;
			private double CALF_CIRC;
			private String BODY_PIC;
			private String LOGO;
			private int CHUANYIXUG;
			private String TEBIESHUOMING;
			private String GEXINGMINGCHENG;
			private int ISRADIO;

			public static UserCCInfoListBean objectFromData(String str) {

				return new Gson().fromJson(str, UserCCInfoListBean.class);
			}

			public static UserCCInfoListBean objectFromData(String str, String key) {

				try {
					JSONObject jsonObject = new JSONObject(str);

					return new Gson().fromJson(jsonObject.getString(str), UserCCInfoListBean.class);
				} catch (JSONException e) {
					e.printStackTrace();
				}

				return null;
			}

			public static List<UserCCInfoListBean> arrayUserCCInfoListBeanFromData(String str) {

				Type listType = new TypeToken<ArrayList<UserCCInfoListBean>>() {
				}.getType();

				return new Gson().fromJson(str, listType);
			}

			public static List<UserCCInfoListBean> arrayUserCCInfoListBeanFromData(String str, String key) {

				try {
					JSONObject jsonObject = new JSONObject(str);
					Type listType = new TypeToken<ArrayList<UserCCInfoListBean>>() {
					}.getType();

					return new Gson().fromJson(jsonObject.getString(str), listType);

				} catch (JSONException e) {
					e.printStackTrace();
				}

				return new ArrayList();


			}

			public int getID() {
				return ID;
			}

			public void setID(int ID) {
				this.ID = ID;
			}

			public int getUSER_ID() {
				return USER_ID;
			}

			public void setUSER_ID(int USER_ID) {
				this.USER_ID = USER_ID;
			}

			public String getName() {
				return Name;
			}

			public void setName(String Name) {
				this.Name = Name;
			}

			public double getHEIGHT() {
				return HEIGHT;
			}

			public void setHEIGHT(double HEIGHT) {
				this.HEIGHT = HEIGHT;
			}

			public double getWEIGHT() {
				return WEIGHT;
			}

			public void setWEIGHT(double WEIGHT) {
				this.WEIGHT = WEIGHT;
			}

			public int getAGE() {
				return AGE;
			}

			public void setAGE(int AGE) {
				this.AGE = AGE;
			}

			public String getPHYSIQUE() {
				return PHYSIQUE;
			}

			public void setPHYSIQUE(String PHYSIQUE) {
				this.PHYSIQUE = PHYSIQUE;
			}

			public String getTOP_SIZE() {
				return TOP_SIZE;
			}

			public void setTOP_SIZE(String TOP_SIZE) {
				this.TOP_SIZE = TOP_SIZE;
			}

			public int getWAISTBAND() {
				return WAISTBAND;
			}

			public void setWAISTBAND(int WAISTBAND) {
				this.WAISTBAND = WAISTBAND;
			}

			public int getCT_TYPE() {
				return CT_TYPE;
			}

			public void setCT_TYPE(int CT_TYPE) {
				this.CT_TYPE = CT_TYPE;
			}

			public String getCREATOR_DATE() {
				return CREATOR_DATE;
			}

			public void setCREATOR_DATE(String CREATOR_DATE) {
				this.CREATOR_DATE = CREATOR_DATE;
			}

			public String getCREATOR_USERNAME() {
				return CREATOR_USERNAME;
			}

			public void setCREATOR_USERNAME(String CREATOR_USERNAME) {
				this.CREATOR_USERNAME = CREATOR_USERNAME;
			}

			public String getRMK() {
				return RMK;
			}

			public void setRMK(String RMK) {
				this.RMK = RMK;
			}

			public String getUPDATE_DATE() {
				return UPDATE_DATE;
			}

			public void setUPDATE_DATE(String UPDATE_DATE) {
				this.UPDATE_DATE = UPDATE_DATE;
			}

			public String getUPDATE_USERNAME() {
				return UPDATE_USERNAME;
			}

			public void setUPDATE_USERNAME(String UPDATE_USERNAME) {
				this.UPDATE_USERNAME = UPDATE_USERNAME;
			}

			public double getBUST() {
				return BUST;
			}

			public void setBUST(double BUST) {
				this.BUST = BUST;
			}

			public double getWAIST() {
				return WAIST;
			}

			public void setWAIST(double WAIST) {
				this.WAIST = WAIST;
			}

			public double getHIP() {
				return HIP;
			}

			public void setHIP(double HIP) {
				this.HIP = HIP;
			}

			public double getPANTS() {
				return PANTS;
			}

			public void setPANTS(double PANTS) {
				this.PANTS = PANTS;
			}

			public double getSLEEVE() {
				return SLEEVE;
			}

			public void setSLEEVE(double SLEEVE) {
				this.SLEEVE = SLEEVE;
			}

			public int getDRESS_EFFECT() {
				return DRESS_EFFECT;
			}

			public void setDRESS_EFFECT(int DRESS_EFFECT) {
				this.DRESS_EFFECT = DRESS_EFFECT;
			}

			public int getWAIST_POSTION() {
				return WAIST_POSTION;
			}

			public void setWAIST_POSTION(int WAIST_POSTION) {
				this.WAIST_POSTION = WAIST_POSTION;
			}

			public double getBMI() {
				return BMI;
			}

			public void setBMI(double BMI) {
				this.BMI = BMI;
			}

			public String getSKIN_COLOR() {
				return SKIN_COLOR;
			}

			public void setSKIN_COLOR(String SKIN_COLOR) {
				this.SKIN_COLOR = SKIN_COLOR;
			}

			public int getGENDER() {
				return GENDER;
			}

			public void setGENDER(int GENDER) {
				this.GENDER = GENDER;
			}

			public int getIS_CT_TEST() {
				return IS_CT_TEST;
			}

			public void setIS_CT_TEST(int IS_CT_TEST) {
				this.IS_CT_TEST = IS_CT_TEST;
			}

			public String getCLOTH_SIZE() {
				return CLOTH_SIZE;
			}

			public void setCLOTH_SIZE(String CLOTH_SIZE) {
				this.CLOTH_SIZE = CLOTH_SIZE;
			}

			public double getSHOULDER_BREADTH() {
				return SHOULDER_BREADTH;
			}

			public void setSHOULDER_BREADTH(double SHOULDER_BREADTH) {
				this.SHOULDER_BREADTH = SHOULDER_BREADTH;
			}

			public double getARM_CIRC() {
				return ARM_CIRC;
			}

			public void setARM_CIRC(double ARM_CIRC) {
				this.ARM_CIRC = ARM_CIRC;
			}

			public double getTHIGH_CIRC() {
				return THIGH_CIRC;
			}

			public void setTHIGH_CIRC(double THIGH_CIRC) {
				this.THIGH_CIRC = THIGH_CIRC;
			}

			public double getKNEE_CIRC() {
				return KNEE_CIRC;
			}

			public void setKNEE_CIRC(double KNEE_CIRC) {
				this.KNEE_CIRC = KNEE_CIRC;
			}

			public double getCALF_CIRC() {
				return CALF_CIRC;
			}

			public void setCALF_CIRC(double CALF_CIRC) {
				this.CALF_CIRC = CALF_CIRC;
			}

			public String getBODY_PIC() {
				return BODY_PIC;
			}

			public void setBODY_PIC(String BODY_PIC) {
				this.BODY_PIC = BODY_PIC;
			}

			public String getLOGO() {
				return LOGO;
			}

			public void setLOGO(String LOGO) {
				this.LOGO = LOGO;
			}

			public int getCHUANYIXUG() {
				return CHUANYIXUG;
			}

			public void setCHUANYIXUG(int CHUANYIXUG) {
				this.CHUANYIXUG = CHUANYIXUG;
			}

			public String getTEBIESHUOMING() {
				return TEBIESHUOMING;
			}

			public void setTEBIESHUOMING(String TEBIESHUOMING) {
				this.TEBIESHUOMING = TEBIESHUOMING;
			}

			public String getGEXINGMINGCHENG() {
				return GEXINGMINGCHENG;
			}

			public void setGEXINGMINGCHENG(String GEXINGMINGCHENG) {
				this.GEXINGMINGCHENG = GEXINGMINGCHENG;
			}

			public int getISRADIO() {
				return ISRADIO;
			}

			public void setISRADIO(int ISRADIO) {
				this.ISRADIO = ISRADIO;
			}
		}
	}
}
