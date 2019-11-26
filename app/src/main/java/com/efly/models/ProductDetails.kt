package com.efly.models

import com.eflyrx.app.data.model.Price
import com.eflyrx.app.data.model.Stock

data class ProductDetails(


    val custom_attributes: List<CustomAttribute>,
    val description: String,
    val id: Int,
    val image: String,
    val image_resized: String,
    val is_salable: Int,
//    val media_gallery_sizes: List<MediaGallerySize>,
    val name: String,
    val options: List<Any>,
    val price: Price,
    val product_links: List<Any>,
    val sku: String,
    val stock: Stock,
    val tier_prices: List<Any>,
    val type_id: String,
    val url_path: String
)