package com.efly.models

class PaymentMethod {
    var additional_data: List<String>? = null
    var extension_attributes: ExtensionAttributes? = null
    var method: String = "stripe"
    var po_number: String? = ""
    var id: Int = 0
    lateinit var title: String
    lateinit var code: String
    var selected: Boolean = false


    fun PaymentMethod(method1: String) {
        this.method = method1
    }
}
