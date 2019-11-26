package com.efly.models.inputs;

public class AddCartItemInput {
    public CartItemInput cart_item;

    public AddCartItemInput(CartItemInput item) {
        this.cart_item = item;
    }
}
