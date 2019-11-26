package com.efly.models.response;

import com.efly.models.AccountModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountResponse {

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("model")
    @Expose
    private AccountModel model;

    @SerializedName("session")
    @Expose
    private String session;

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

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return
                "AccountResponse{" +
                        "msg = '" + msg + '\'' +
                        ",code = '" + code + '\'' +
                        ",model = '" + model + '\'' +
                        "}";
    }
}