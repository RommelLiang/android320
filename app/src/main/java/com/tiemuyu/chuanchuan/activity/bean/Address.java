package com.tiemuyu.chuanchuan.activity.bean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 梁文硕 on 2017/2/21.
 */

public class Address implements Serializable{

    /**
     * Code : 1
     * Msg : OK
     * Data : [{"Id":6096,"UserId":94589,"Contact":"莱因哈特","Mobile":"13569039897","Province":"北京市","City":"市辖区","District":"东城区","Address":"长安街中南海","PostCode":"000000","IsDefault":1,"CreatorDate":"2017-02-21 14:25:10","CreatorUsername":"17688159789","UpdateDate":"2017-02-21 14:25:10","UpdateUsername":"17688159789","PkValue":6096}]
     */

    private int Code;
    private String Msg;
    private List<DataBean> Data;

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

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        /**
         * Id : 6096
         * UserId : 94589
         * Contact : 莱因哈特
         * Mobile : 13569039897
         * Province : 北京市
         * City : 市辖区
         * District : 东城区
         * Address : 长安街中南海
         * PostCode : 000000
         * IsDefault : 1
         * CreatorDate : 2017-02-21 14:25:10
         * CreatorUsername : 17688159789
         * UpdateDate : 2017-02-21 14:25:10
         * UpdateUsername : 17688159789
         * PkValue : 6096
         */

        private int Id;
        private int UserId;
        private String Contact;
        private String Mobile;
        private String Province;
        private String City;
        private String District;
        private String Address;
        private String PostCode;
        private int IsDefault;
        private String CreatorDate;
        private String CreatorUsername;
        private String UpdateDate;
        private String UpdateUsername;
        private int PkValue;
        public String getTotalAddress(){
            return getProvince() + " " + getCity() + " " + getDistrict() +  " " + getAddress();
        }
        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public String getContact() {
            return Contact;
        }

        public void setContact(String Contact) {
            this.Contact = Contact;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getProvince() {
            return Province;
        }

        public void setProvince(String Province) {
            this.Province = Province;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getDistrict() {
            return District;
        }

        public void setDistrict(String District) {
            this.District = District;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getPostCode() {
            return PostCode;
        }

        public void setPostCode(String PostCode) {
            this.PostCode = PostCode;
        }

        public int getIsDefault() {
            return IsDefault;
        }

        public void setIsDefault(int IsDefault) {
            this.IsDefault = IsDefault;
        }

        public String getCreatorDate() {
            return CreatorDate;
        }

        public void setCreatorDate(String CreatorDate) {
            this.CreatorDate = CreatorDate;
        }

        public String getCreatorUsername() {
            return CreatorUsername;
        }

        public void setCreatorUsername(String CreatorUsername) {
            this.CreatorUsername = CreatorUsername;
        }

        public String getUpdateDate() {
            return UpdateDate;
        }

        public void setUpdateDate(String UpdateDate) {
            this.UpdateDate = UpdateDate;
        }

        public String getUpdateUsername() {
            return UpdateUsername;
        }

        public void setUpdateUsername(String UpdateUsername) {
            this.UpdateUsername = UpdateUsername;
        }

        public int getPkValue() {
            return PkValue;
        }

        public void setPkValue(int PkValue) {
            this.PkValue = PkValue;
        }
    }
}
