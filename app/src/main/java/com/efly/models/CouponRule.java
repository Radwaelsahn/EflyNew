package com.efly.models;

import com.google.gson.annotations.SerializedName;

//import javax.annotation.Generated;

public class CouponRule {

    @SerializedName("is_advanced")
    private String isAdvanced;

    @SerializedName("is_active")
    private String isActive;

    @SerializedName("from_date")
    private Object fromDate;

    @SerializedName("simple_free_shipping")
    private String simpleFreeShipping;

    @SerializedName("discount_amount")
    private String discountAmount;

    @SerializedName("description")
    private Object description;

    @SerializedName("times_used")
    private String timesUsed;

    @SerializedName("simple_action")
    private String simpleAction;

    @SerializedName("rule_id")
    private String ruleId;

    @SerializedName("apply_to_shipping")
    private String applyToShipping;

    @SerializedName("is_rss")
    private String isRss;

    @SerializedName("to_date")
    private Object toDate;

    @SerializedName("product_ids")
    private Object productIds;

    @SerializedName("name")
    private String name;

    @SerializedName("use_auto_generation")
    private String useAutoGeneration;

    @SerializedName("uses_per_customer")
    private String usesPerCustomer;

    @SerializedName("uses_per_coupon")
    private String usesPerCoupon;

    @SerializedName("discount_qty")
    private Object discountQty;

    @SerializedName("discount_step")
    private String discountStep;

    @SerializedName("coupon_type")
    private String couponType;

    public void setIsAdvanced(String isAdvanced) {
        this.isAdvanced = isAdvanced;
    }

    public String getIsAdvanced() {
        return isAdvanced;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setFromDate(Object fromDate) {
        this.fromDate = fromDate;
    }

    public Object getFromDate() {
        return fromDate;
    }

    public void setSimpleFreeShipping(String simpleFreeShipping) {
        this.simpleFreeShipping = simpleFreeShipping;
    }

    public String getSimpleFreeShipping() {
        return simpleFreeShipping;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getDescription() {
        return description;
    }

    public void setTimesUsed(String timesUsed) {
        this.timesUsed = timesUsed;
    }

    public String getTimesUsed() {
        return timesUsed;
    }

    public void setSimpleAction(String simpleAction) {
        this.simpleAction = simpleAction;
    }

    public String getSimpleAction() {
        return simpleAction;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setApplyToShipping(String applyToShipping) {
        this.applyToShipping = applyToShipping;
    }

    public String getApplyToShipping() {
        return applyToShipping;
    }

    public void setIsRss(String isRss) {
        this.isRss = isRss;
    }

    public String getIsRss() {
        return isRss;
    }

    public void setToDate(Object toDate) {
        this.toDate = toDate;
    }

    public Object getToDate() {
        return toDate;
    }

    public void setProductIds(Object productIds) {
        this.productIds = productIds;
    }

    public Object getProductIds() {
        return productIds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUseAutoGeneration(String useAutoGeneration) {
        this.useAutoGeneration = useAutoGeneration;
    }

    public String getUseAutoGeneration() {
        return useAutoGeneration;
    }

    public void setUsesPerCustomer(String usesPerCustomer) {
        this.usesPerCustomer = usesPerCustomer;
    }

    public String getUsesPerCustomer() {
        return usesPerCustomer;
    }

    public void setUsesPerCoupon(String usesPerCoupon) {
        this.usesPerCoupon = usesPerCoupon;
    }

    public String getUsesPerCoupon() {
        return usesPerCoupon;
    }

    public void setDiscountQty(Object discountQty) {
        this.discountQty = discountQty;
    }

    public Object getDiscountQty() {
        return discountQty;
    }

    public void setDiscountStep(String discountStep) {
        this.discountStep = discountStep;
    }

    public String getDiscountStep() {
        return discountStep;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getCouponType() {
        return couponType;
    }

    @Override
    public String toString() {
        return
                "CouponRule{" +
                        "is_advanced = '" + isAdvanced + '\'' +
                        ",is_active = '" + isActive + '\'' +
                        ",from_date = '" + fromDate + '\'' +
                        ",simple_free_shipping = '" + simpleFreeShipping + '\'' +
                        ",discount_amount = '" + discountAmount + '\'' +
                        ",description = '" + description + '\'' +
                        ",times_used = '" + timesUsed + '\'' +
                        ",simple_action = '" + simpleAction + '\'' +
                        ",rule_id = '" + ruleId + '\'' +
                        ",apply_to_shipping = '" + applyToShipping + '\'' +
                        ",is_rss = '" + isRss + '\'' +
                        ",to_date = '" + toDate + '\'' +
                        ",product_ids = '" + productIds + '\'' +
                        ",name = '" + name + '\'' +
                        ",use_auto_generation = '" + useAutoGeneration + '\'' +
                        ",uses_per_customer = '" + usesPerCustomer + '\'' +
                        ",uses_per_coupon = '" + usesPerCoupon + '\'' +
                        ",discount_qty = '" + discountQty + '\'' +
                        ",discount_step = '" + discountStep + '\'' +
                        ",coupon_type = '" + couponType + '\'' +
                        "}";
    }
}