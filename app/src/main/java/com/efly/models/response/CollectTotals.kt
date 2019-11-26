package com.efly.models.response

import com.efly.models.PaymentMethod
import com.efly.models.ProductModel
import com.eflyrx.app.data.model.AdditionalData
import com.eflyrx.app.data.model.TotalSegments

data class CollectTotals(
        val additionalData: AdditionalData,
        val paymentMethod: PaymentMethod,
        val shippingCarrierCode: String,
        val shippingMethodCode: String,
        val grand_total: Double,
        val base_grand_total: Double,
        val subtotal: Double,
        val base_subtotal: Double,
        val discount_amount: Double,
        val base_discount_amount: Double,
        val subtotal_with_discount: Double,
        val base_subtotal_with_discount: Double,
        val shipping_amount: Double,
        val base_shipping_amount: Double,
        val shipping_discount_amount: Double,
        val base_shipping_discount_amount: Double,
        val tax_amount: Double,
        val base_tax_amount: Double,
        val shipping_tax_amount: Double,
        val base_shipping_tax_amount: Double,
        val subtotal_incl_tax: Double,
        val shipping_incl_tax: Double,
        val base_shipping_incl_tax: Double,
        val base_currency_code: String,
        val quote_currency_code: String,
        val items_qty: Int,
        val items: List<ProductModel>,
        val total_segments: List<TotalSegments>

)