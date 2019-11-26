package com.efly.models.inputs;

import com.eflyrx.app.data.model.inputs.ShippingAddress;

public class ShippingMethodInputModel {

    public ShippingAddress shipping_address;
    public ShippingAddress billing_address;
    private String shipping_carrier_code ="flatrate";
    private String shipping_method_code ="flatrate";
}
