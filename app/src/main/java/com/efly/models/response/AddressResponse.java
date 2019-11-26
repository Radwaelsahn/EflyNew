package com.efly.models.response;

import com.efly.models.Address;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressResponse {

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("model")
    @Expose
    private Address model;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setModel(Address model) {
        this.model = model;
    }

    public Address getModel() {
        return model;
    }

    @Override
    public String toString() {
        return
                "AddressResponse{" +
                        "msg = '" + msg + '\'' +
                        ",code = '" + code + '\'' +
                        ",model = '" + model + '\'' +
                        "}";
    }
}