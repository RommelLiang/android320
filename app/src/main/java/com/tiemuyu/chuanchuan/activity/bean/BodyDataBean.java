package com.tiemuyu.chuanchuan.activity.bean;

/**
 * @项目名： 215androidpay
 * @包名： com.tiemuyu.chuanchuan.activity.bean
 * @类描述：
 * @创建人： hr
 * @创建时间： 2017/2/22
 * @version：
 */

public class BodyDataBean {

    /**
     * Code : 1
     * Msg : OK
     * Data : {"UserId":74886,"CtType":0,"Age":55,"Gender":2,"Height":165,"Weight":57,"Bmi":0,"Bust":44,"Waist":8,"Hip":0,"Pants":0,"Sleeve":0,"ShoulderBreadth":44,"ArmCirc":0,"ThighCirc":0,"KneeCirc":0,"CalfCirc":0,"Physique":"梨形","SkinColor":"","DressEffect":0,"WaistPostion":0,"ClothSize":"XL","IsCtTest":0,"BodyPic":"http://f1.myappcc.com/zfs/7E1/1023/VLW/023215040036CABHPOHLXM.jpg","CreatorDate":"2016-11-23 15:13:35","UpdateDate":"2017-02-22 12:08:57","CreatorUsername":"13145975793","UpdateUsername":"13145975793","Rmk":"","PkValue":74886,"CtTypeName":null,"TEBIESHUOMING":"","CHUANYIXIGUAN":0,"GEXINGMINGCHENG":"","ISRADIO":0}
     */

    private int Code;
    private String Msg;
    private DataBean Data;

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
        @Override
        public String toString() {
            return "DataBean{" +
                    "UserId=" + UserId +
                    ", CtType=" + CtType +
                    ", Age=" + Age +
                    ", Gender=" + Gender +
                    ", Height=" + Height +
                    ", Weight=" + Weight +
                    ", Bmi=" + Bmi +
                    ", Bust=" + Bust +
                    ", Waist=" + Waist +
                    ", Hip=" + Hip +
                    ", Pants=" + Pants +
                    ", Sleeve=" + Sleeve +
                    ", ShoulderBreadth=" + ShoulderBreadth +
                    ", ArmCirc=" + ArmCirc +
                    ", ThighCirc=" + ThighCirc +
                    ", KneeCirc=" + KneeCirc +
                    ", CalfCirc=" + CalfCirc +
                    ", Physique='" + Physique + '\'' +
                    ", SkinColor='" + SkinColor + '\'' +
                    ", DressEffect=" + DressEffect +
                    ", WaistPostion=" + WaistPostion +
                    ", ClothSize='" + ClothSize + '\'' +
                    ", IsCtTest=" + IsCtTest +
                    ", BodyPic='" + BodyPic + '\'' +
                    ", CreatorDate='" + CreatorDate + '\'' +
                    ", UpdateDate='" + UpdateDate + '\'' +
                    ", CreatorUsername='" + CreatorUsername + '\'' +
                    ", UpdateUsername='" + UpdateUsername + '\'' +
                    ", Rmk='" + Rmk + '\'' +
                    ", PkValue=" + PkValue +
                    ", CtTypeName=" + CtTypeName +
                    ", TEBIESHUOMING='" + TEBIESHUOMING + '\'' +
                    ", CHUANYIXIGUAN=" + CHUANYIXIGUAN +
                    ", GEXINGMINGCHENG='" + GEXINGMINGCHENG + '\'' +
                    ", ISRADIO=" + ISRADIO +
                    '}';
        }

        /**
         * UserId : 74886
         * CtType : 0
         * Age : 55
         * Gender : 2
         * Height : 165.0
         * Weight : 57.0
         * Bmi : 0.0
         * Bust : 44.0
         * Waist : 8.0
         * Hip : 0.0
         * Pants : 0.0
         * Sleeve : 0.0
         * ShoulderBreadth : 44.0
         * ArmCirc : 0.0
         * ThighCirc : 0.0
         * KneeCirc : 0.0
         * CalfCirc : 0.0
         * Physique : 梨形
         * SkinColor :
         * DressEffect : 0
         * WaistPostion : 0
         * ClothSize : XL
         * IsCtTest : 0
         * BodyPic : http://f1.myappcc.com/zfs/7E1/1023/VLW/023215040036CABHPOHLXM.jpg
         * CreatorDate : 2016-11-23 15:13:35
         * UpdateDate : 2017-02-22 12:08:57
         * CreatorUsername : 13145975793
         * UpdateUsername : 13145975793
         * Rmk :
         * PkValue : 74886
         * CtTypeName : null
         * TEBIESHUOMING :
         * CHUANYIXIGUAN : 0
         * GEXINGMINGCHENG :
         * ISRADIO : 0
         */

        private int UserId;
        private int CtType;
        private int Age;
        private int Gender;
        private String Height;
        private String Weight;
        private String Bmi;
        private String Bust;
        private String Waist;
        private String Hip;
        private String Pants;
        private String Sleeve;
        private String ShoulderBreadth;
        private String ArmCirc;
        private String ThighCirc;
        private String KneeCirc;
        private String CalfCirc;
        private String Physique;
        private String SkinColor;
        private int DressEffect;
        private int WaistPostion;
        private String ClothSize;
        private int IsCtTest;
        private String BodyPic;
        private String CreatorDate;
        private String UpdateDate;
        private String CreatorUsername;
        private String UpdateUsername;
        private String Rmk;
        private int PkValue;
        private Object CtTypeName;
        private String TEBIESHUOMING;
        private int CHUANYIXIGUAN;
        private String GEXINGMINGCHENG;
        private int ISRADIO;

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getCtType() {
            return CtType;
        }

        public void setCtType(int CtType) {
            this.CtType = CtType;
        }

        public int getAge() {
            return Age;
        }

        public void setAge(int Age) {
            this.Age = Age;
        }

        public int getGender() {
            return Gender;
        }

        public void setGender(int Gender) {
            this.Gender = Gender;
        }

        public String getHeight() {
            return Height;
        }

        public void setHeight(String Height) {
            this.Height = Height;
        }

        public String getWeight() {
            return Weight;
        }

        public void setWeight(String Weight) {
            this.Weight = Weight;
        }

        public String getBmi() {
            return Bmi;
        }

        public void setBmi(String Bmi) {
            this.Bmi = Bmi;
        }

        public String getBust() {
            return Bust;
        }

        public void setBust(String Bust) {
            this.Bust = Bust;
        }

        public String getWaist() {
            return Waist;
        }

        public void setWaist(String Waist) {
            this.Waist = Waist;
        }

        public String getHip() {
            return Hip;
        }

        public void setHip(String Hip) {
            this.Hip = Hip;
        }

        public String getPants() {
            return Pants;
        }

        public void setPants(String Pants) {
            this.Pants = Pants;
        }

        public String getSleeve() {
            return Sleeve;
        }

        public void setSleeve(String Sleeve) {
            this.Sleeve = Sleeve;
        }

        public String getShoulderBreadth() {
            return ShoulderBreadth;
        }

        public void setShoulderBreadth(String ShoulderBreadth) {
            this.ShoulderBreadth = ShoulderBreadth;
        }

        public String getArmCirc() {
            return ArmCirc;
        }

        public void setArmCirc(String ArmCirc) {
            this.ArmCirc = ArmCirc;
        }

        public String getThighCirc() {
            return ThighCirc;
        }

        public void setThighCirc(String ThighCirc) {
            this.ThighCirc = ThighCirc;
        }

        public String getKneeCirc() {
            return KneeCirc;
        }

        public void setKneeCirc(String KneeCirc) {
            this.KneeCirc = KneeCirc;
        }

        public String getCalfCirc() {
            return CalfCirc;
        }

        public void setCalfCirc(String CalfCirc) {
            this.CalfCirc = CalfCirc;
        }

        public String getPhysique() {
            return Physique;
        }

        public void setPhysique(String Physique) {
            this.Physique = Physique;
        }

        public String getSkinColor() {
            return SkinColor;
        }

        public void setSkinColor(String SkinColor) {
            this.SkinColor = SkinColor;
        }

        public int getDressEffect() {
            return DressEffect;
        }

        public void setDressEffect(int DressEffect) {
            this.DressEffect = DressEffect;
        }

        public int getWaistPostion() {
            return WaistPostion;
        }

        public void setWaistPostion(int WaistPostion) {
            this.WaistPostion = WaistPostion;
        }

        public String getClothSize() {
            return ClothSize;
        }

        public void setClothSize(String ClothSize) {
            this.ClothSize = ClothSize;
        }

        public int getIsCtTest() {
            return IsCtTest;
        }

        public void setIsCtTest(int IsCtTest) {
            this.IsCtTest = IsCtTest;
        }

        public String getBodyPic() {
            return BodyPic;
        }

        public void setBodyPic(String BodyPic) {
            this.BodyPic = BodyPic;
        }

        public String getCreatorDate() {
            return CreatorDate;
        }

        public void setCreatorDate(String CreatorDate) {
            this.CreatorDate = CreatorDate;
        }

        public String getUpdateDate() {
            return UpdateDate;
        }

        public void setUpdateDate(String UpdateDate) {
            this.UpdateDate = UpdateDate;
        }

        public String getCreatorUsername() {
            return CreatorUsername;
        }

        public void setCreatorUsername(String CreatorUsername) {
            this.CreatorUsername = CreatorUsername;
        }

        public String getUpdateUsername() {
            return UpdateUsername;
        }

        public void setUpdateUsername(String UpdateUsername) {
            this.UpdateUsername = UpdateUsername;
        }

        public String getRmk() {
            return Rmk;
        }

        public void setRmk(String Rmk) {
            this.Rmk = Rmk;
        }

        public int getPkValue() {
            return PkValue;
        }

        public void setPkValue(int PkValue) {
            this.PkValue = PkValue;
        }

        public Object getCtTypeName() {
            return CtTypeName;
        }

        public void setCtTypeName(Object CtTypeName) {
            this.CtTypeName = CtTypeName;
        }

        public String getTEBIESHUOMING() {
            return TEBIESHUOMING;
        }

        public void setTEBIESHUOMING(String TEBIESHUOMING) {
            this.TEBIESHUOMING = TEBIESHUOMING;
        }

        public int getCHUANYIXIGUAN() {
            return CHUANYIXIGUAN;
        }

        public void setCHUANYIXIGUAN(int CHUANYIXIGUAN) {
            this.CHUANYIXIGUAN = CHUANYIXIGUAN;
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