package com.eflyrx.app.data.model.response

import com.efly.models.ExtensionAttributes
import com.stripe.android.model.Address

data class RegisterResponse(
    val addresses: List<Address>,
    val created_at: String,
    val created_in: String,
    val default_billing: String,
    val default_shipping: String,
    val disable_auto_group_change: Int,
    val email: String,
    val extension_attributes: ExtensionAttributes,
    val firstname: String,
    val gender: Int,
    val group_id: Int,
    val id: Int,
    val lastname: String,
    val store_id: Int,
    val updated_at: String,
    val website_id: Int
)