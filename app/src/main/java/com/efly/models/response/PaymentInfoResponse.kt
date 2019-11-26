package com.eflyrx.app.data.model.response

import com.efly.models.PaymentMethod
import com.efly.models.ShippingAddress

data class PaymentInfoResponse(
        val billingAddress: ShippingAddress,
        val paymentMethod: PaymentMethod
)