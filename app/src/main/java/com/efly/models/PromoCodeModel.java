package com.efly.models;

import com.google.gson.annotations.SerializedName;

public class PromoCodeModel {

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("coupon_code")
    private String couponCode;

    @SerializedName("subtotal")
    private String subtotal;

    @SerializedName("discount")
    private String discount;

    @SerializedName("coupon_rule")
    private CouponRule couponRule;

    @SerializedName("tax")
    private String tax;

    @SerializedName("grand_total")
    private String grandTotal;

    @SerializedName("delivery_fee")
    private String deliveryFee;

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setCouponRule(CouponRule couponRule) {
        this.couponRule = couponRule;
    }

    public CouponRule getCouponRule() {
        return couponRule;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTax() {
        return tax;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    @Override
    public String toString() {
        return "PromoCodeModel{" +
                "symbol='" + symbol + '\'' +
                ", couponCode='" + couponCode + '\'' +
                ", subtotal='" + subtotal + '\'' +
                ", discount='" + discount + '\'' +
                ", couponRule=" + couponRule +
                ", tax='" + tax + '\'' +
                ", grandTotal='" + grandTotal + '\'' +
                ", deliveryFee='" + deliveryFee + '\'' +
                '}';
    }
}