package com.efly.models.inputs;


import com.efly.models.Customer;

public class RegisterInput {
    private Customer customer;
    private String password;
    private String redirectUrl;

    public RegisterInput(Customer customer, String password) {
        this.setCustomer(customer);
        this.setPassword(password);
//        this.redirectUrl = redirectUrl;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
