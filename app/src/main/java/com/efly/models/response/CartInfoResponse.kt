package com.eflyrx.app.data.model.response

import com.efly.models.ExtensionAttributes
import com.efly.models.ProductModel
import com.efly.models.ShippingAddress
import com.eflyrx.app.data.model.*
import com.stripe.android.model.Customer
import java.util.*

data class CartInfoResponse(
        val billing_address: ShippingAddress,
        val created_at: String,
        val currency: Currency,
        val customer: Customer,
        val customer_is_guest: Boolean,
        val customer_note_notify: Boolean,
        val customer_tax_class_id: Int,
        val extension_attributes: ExtensionAttributes,
        val id: String,
        val is_active: Boolean,
        val is_virtual: Boolean,
        val items: List<ProductModel>,
        val items_count: Int,
        val items_qty: Int,
        val orig_order_id: Int,
        val store_id: Int,
        val updated_at: String
) {
}