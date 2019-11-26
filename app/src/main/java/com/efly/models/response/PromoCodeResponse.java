package com.efly.models.response;

import com.efly.models.PromoCodeModel;
import com.google.gson.annotations.SerializedName;

public class PromoCodeResponse {

    @SerializedName("msg")
    private String msg;

    @SerializedName("code")
    private int code;

    @SerializedName("model")
    private PromoCodeModel model;

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

    public void setModel(PromoCodeModel model) {
        this.model = model;
    }

    public PromoCodeModel getModel() {
        return model;
    }

    @Override
    public String toString() {
        return
                "PromoCodeResponse{" +
                        "msg = '" + msg + '\'' +
                        ",code = '" + code + '\'' +
                        ",model = '" + model + '\'' +
                        "}";
    }
}