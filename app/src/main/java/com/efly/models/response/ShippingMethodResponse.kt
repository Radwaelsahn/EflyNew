package com.eflyrx.app.data.model.response

import com.efly.models.PaymentMethod

data class ShippingMethodResponse(
    val payment_methods: List<PaymentMethod>
//    val totals: Totals
)