package com.efly.models;

import com.google.gson.annotations.SerializedName;

public class AccountDetails {

    @SerializedName("store_id")
    private String storeId;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("is_active")
    private String isActive;

    @SerializedName("gender")
    private String gender;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("entity_type_id")
    private String entityTypeId;

    @SerializedName("entity_id")
    private String entityId;

    @SerializedName("stakeholder")
    private String stakeholder;

    @SerializedName("default_shipping")
    private String defaultShipping;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("attribute_set_id")
    private String attributeSetId;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("disable_auto_group_change")
    private String disableAutoGroupChange;

    @SerializedName("group_id")
    private String groupId;

    @SerializedName("increment_id")
    private Object incrementId;

    @SerializedName("password_hash")
    private String passwordHash;

    @SerializedName("default_billing")
    private String defaultBilling;

    @SerializedName("website_id")
    private String websiteId;

    @SerializedName("email")
    private String email;

    @SerializedName("created_in")
    private String createdIn;

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setEntityTypeId(String entityTypeId) {
        this.entityTypeId = entityTypeId;
    }

    public String getEntityTypeId() {
        return entityTypeId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setStakeholder(String stakeholder) {
        this.stakeholder = stakeholder;
    }

    public String getStakeholder() {
        return stakeholder;
    }

    public void setDefaultShipping(String defaultShipping) {
        this.defaultShipping = defaultShipping;
    }

    public String getDefaultShipping() {
        return defaultShipping;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setAttributeSetId(String attributeSetId) {
        this.attributeSetId = attributeSetId;
    }

    public String getAttributeSetId() {
        return attributeSetId;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setDisableAutoGroupChange(String disableAutoGroupChange) {
        this.disableAutoGroupChange = disableAutoGroupChange;
    }

    public String getDisableAutoGroupChange() {
        return disableAutoGroupChange;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setIncrementId(Object incrementId) {
        this.incrementId = incrementId;
    }

    public Object getIncrementId() {
        return incrementId;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setDefaultBilling(String defaultBilling) {
        this.defaultBilling = defaultBilling;
    }

    public String getDefaultBilling() {
        return defaultBilling;
    }

    public void setWebsiteId(String websiteId) {
        this.websiteId = websiteId;
    }

    public String getWebsiteId() {
        return websiteId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setCreatedIn(String createdIn) {
        this.createdIn = createdIn;
    }

    public String getCreatedIn() {
        return createdIn;
    }

    @Override
    public String toString() {
        return
                "AccountDetails{" +
                        "store_id = '" + storeId + '\'' +
                        ",firstname = '" + firstname + '\'' +
                        ",is_active = '" + isActive + '\'' +
                        ",gender = '" + gender + '\'' +
                        ",mobile = '" + mobile + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",entity_type_id = '" + entityTypeId + '\'' +
                        ",entity_id = '" + entityId + '\'' +
                        ",stakeholder = '" + stakeholder + '\'' +
                        ",default_shipping = '" + defaultShipping + '\'' +
                        ",lastname = '" + lastname + '\'' +
                        ",full_name = '" + fullName + '\'' +
                        ",attribute_set_id = '" + attributeSetId + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",disable_auto_group_change = '" + disableAutoGroupChange + '\'' +
                        ",group_id = '" + groupId + '\'' +
                        ",increment_id = '" + incrementId + '\'' +
                        ",password_hash = '" + passwordHash + '\'' +
                        ",default_billing = '" + defaultBilling + '\'' +
                        ",website_id = '" + websiteId + '\'' +
                        ",email = '" + email + '\'' +
                        ",created_in = '" + createdIn + '\'' +
                        "}";
    }
}