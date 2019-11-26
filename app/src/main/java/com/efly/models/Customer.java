package com.efly.models;


import java.util.List;

public class Customer {
    private List<Address> addresses;
    private String confirmation;
    private String created_at;
    private String created_in;
    private List<CustomAttribute> custom_attributes;
    private String default_billing="true";
    private String default_shipping="true";
    private String disable_auto_group_change;
    private String dob;
    private String email;
//    private String postcode;
    private ExtensionAttributes extension_attributes;
    private String firstname;
    private String gender;
    String group_id;
    private String id;
    private String lastname;
    private String middlename;
    String prefix;
    private String store_id;
    private String suffix;
    private String taxvat;
    private String updated_at;
    private String website_id;
//    private String phone;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_in() {
        return created_in;
    }

    public void setCreated_in(String created_in) {
        this.created_in = created_in;
    }

    public List<CustomAttribute> getCustom_attributes() {
        return custom_attributes;
    }

    public void setCustom_attributes(List<CustomAttribute> custom_attributes) {
        this.custom_attributes = custom_attributes;
    }

    public String getDefault_billing() {
        return default_billing;
    }

    public void setDefault_billing(String default_billing) {
        this.default_billing = default_billing;
    }

    public String getDefault_shipping() {
        return default_shipping;
    }

    public void setDefault_shipping(String default_shipping) {
        this.default_shipping = default_shipping;
    }

    public String getDisable_auto_group_change() {
        return disable_auto_group_change;
    }

    public void setDisable_auto_group_change(String disable_auto_group_change) {
        this.disable_auto_group_change = disable_auto_group_change;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ExtensionAttributes getExtension_attributes() {
        return extension_attributes;
    }

    public void setExtension_attributes(ExtensionAttributes extension_attributes) {
        this.extension_attributes = extension_attributes;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getTaxvat() {
        return taxvat;
    }

    public void setTaxvat(String taxvat) {
        this.taxvat = taxvat;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getWebsite_id() {
        return website_id;
    }

    public void setWebsite_id(String website_id) {
        this.website_id = website_id;
    }

//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }

//    public String getPostcode() {
//        return postcode;
//    }
//
//    public void setPostcode(String postcode) {
//        this.postcode = postcode;
//    }
}
