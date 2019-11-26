package com.efly.models.inputs;

import com.eflyrx.app.data.model.inputs.ShippingAddress;

public class ShippingAddressInput {
    public ShippingAddress address;

    public ShippingAddressInput(ShippingAddress addressModel) {
        this.address = addressModel;
    }
}
