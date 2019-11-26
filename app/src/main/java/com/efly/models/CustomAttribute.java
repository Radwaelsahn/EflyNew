package com.efly.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CustomAttribute implements Parcelable {
    private String attribute_code;
    private String value;

    public CustomAttribute()
    {}

    public CustomAttribute(String att, String val) {
        setAttribute_code(att);
        setValue(val);
    }

    protected CustomAttribute(Parcel in) {
        setAttribute_code(in.readString());
        setValue(in.readString());
    }

    public static final Creator<CustomAttribute> CREATOR = new Creator<CustomAttribute>() {
        @Override
        public CustomAttribute createFromParcel(Parcel in) {
            return new CustomAttribute(in);
        }

        @Override
        public CustomAttribute[] newArray(int size) {
            return new CustomAttribute[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getAttribute_code());
        dest.writeString(getValue());
    }

    public String getAttribute_code() {
        return attribute_code;
    }

    public void setAttribute_code(String attribute_code) {
        this.attribute_code = attribute_code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
