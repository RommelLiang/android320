package com.tiemuyu.chuanchuan.activity.chat_tools.bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Lonze on 2016/9/6.
 */
public class Contacts implements Parcelable,Comparable<Contacts> {


    private String header;
    private String name;
    private String accid;
    private String content = "";
    private int total = 0;
    private long time;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(accid);
        dest.writeString(content);
        dest.writeLong(time);
        dest.writeInt(total);
        dest.writeString(header);
    }

    public static final Parcelable.Creator<Contacts> CREATOR = new Creator<Contacts>()
    {
        @Override
        public Contacts[] newArray(int size)
        {
            return new Contacts[size];
        }

        @Override
        public Contacts createFromParcel(Parcel in)
        {
            return new Contacts(in);
        }
    };

    public Contacts(Parcel in)
    {
        name = in.readString();
        accid = in.readString();
        content = in.readString();
        total = in.readInt();
        time = in.readLong();
        header = in.readString();
    }

    @Override
    public boolean equals(Object o) {
        return this.getAccid().equals(((Contacts)o).getAccid())&&this.getName().equals(((Contacts)o).getName());
    }

    @Override
    public int compareTo(Contacts o) {
        int i = 0;
        if(this.getTime()>o.getTime())
            i = -1;
        else if(this.getTime()<o.getTime())
            i = 1;
        return i;
    }

//    @Override
//    public int compareTo(Contacts another) {
//        return this.getTime().compareTo(another.getTime());
//    }
}
