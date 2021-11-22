package com.xiaowei.xiaobai.dao;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private int mAge;

    private String mName;

    public User(int age, String name) {
        mAge = age;
        mName = name;
    }

    protected User(Parcel in) {
        mAge = in.readInt();
        mName = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mAge);
        dest.writeString(mName);
    }
}
