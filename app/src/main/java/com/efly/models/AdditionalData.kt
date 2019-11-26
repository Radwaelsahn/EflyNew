package com.eflyrx.app.data.model

import com.efly.models.CustomAttribute
import com.efly.models.ExtensionAttributes

data class AdditionalData(
    val custom_attributes: List<CustomAttribute>,
    val extension_attributes: ExtensionAttributes
)