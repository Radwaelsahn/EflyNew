package com.efly.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountInfo {

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("model")
    @Expose
    private AccountModel model;

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

    public void setModel(AccountModel model) {
        this.model = model;
    }

    public AccountModel getModel() {
        return model;
    }

    @Override
    public String toString() {
        return
                "AccountInfo{" +
                        "msg = '" + msg + '\'' +
                        ",code = '" + code + '\'' +
                        ",model = '" + model + '\'' +
                        "}";
    }
}