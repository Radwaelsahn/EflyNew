package com.efly.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountModel {

	@SerializedName("session")
	@Expose
	private String session;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("mobile")
	@Expose
    private String mobile;

	@SerializedName("entity_id")
	@Expose
	private String entityId;

	@SerializedName("email")
	@Expose
	private String email;

	@SerializedName("account_info")
	@Expose
	private AccountDetails accountDetails;

	@SerializedName("full_name")
	@Expose
	private String fullName;

	public void setSession(String session){
		this.session = session;
	}

	public String getSession(){
		return session;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

    public void setMobile(String mobile) {
        this.mobile = mobile;
	}

    public String getMobile() {
        return mobile;
	}

	public void setEntityId(String entityId){
		this.entityId = entityId;
	}

	public String getEntityId(){
		return entityId;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public AccountDetails getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(AccountDetails accountDetails) {
		this.accountDetails = accountDetails;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "AccountModel{" +
				"session='" + session + '\'' +
				", name='" + name + '\'' +
				", mobile='" + mobile + '\'' +
				", entityId='" + entityId + '\'' +
				", email='" + email + '\'' +
				", accountDetails=" + accountDetails +
				", fullName='" + fullName + '\'' +
				'}';
	}
}