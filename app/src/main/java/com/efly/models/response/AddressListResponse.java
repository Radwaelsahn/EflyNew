package com.efly.models.response;

import com.efly.models.Address;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressListResponse {

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("items")
    @Expose
    private List<Address> model;

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

    public void setModel(List<Address> model) {
        this.model = model;
    }

    public List<Address> getModel() {
        return model;
    }

    @Override
    public String toString() {
        return
                "AddressListResponse{" +
                        "msg = '" + msg + '\'' +
                        ",code = '" + code + '\'' +
                        ",model = '" + model + '\'' +
                        "}";
    }
}