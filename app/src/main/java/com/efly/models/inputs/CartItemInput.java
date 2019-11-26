package com.efly.models.inputs;

public class CartItemInput {
    public String quote_id;
    public int qty;
    public String sku;


    public CartItemInput(String quote_id, int quantity, String sku) {
        this.quote_id = quote_id;
        this.qty = quantity;
        this.sku = sku;
    }
}
