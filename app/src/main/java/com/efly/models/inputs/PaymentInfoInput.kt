package com.eflyrx.app.data.model.inputs

import com.efly.models.inputs.PaymentMethodInput

data class PaymentInfoInput(
    val billing_address: ShippingAddress,
    val paymentMethod: PaymentMethodInput
)