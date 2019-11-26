package com.efly.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CategoryModel {
    @SerializedName("is_active")
    @Expose
    var isActive: String? = null

    @SerializedName("category_id")
    @Expose
    var categoryId: String? = null

    @SerializedName("image")
    @Expose
    var imageUrl: String? = null

    @SerializedName("position ")
    @Expose
    var position: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("thumbnail_url")
    @Expose
    var thumbnailUrl: String? = null

    @SerializedName("level ")
    @Expose
    var level: String? = null

    @SerializedName("url_key")
    @Expose
    var urlKey: String? = null


    @SerializedName("total_products")
    @Expose
    var count: String? = null

    @SerializedName("items")
    @Expose
    var products: List<ProductModel>? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val that = o as CategoryModel?

        if (if (isActive != null) isActive != that!!.isActive else that!!.isActive != null)
            return false
        if (if (categoryId != null) categoryId != that.categoryId else that.categoryId != null)
            return false
        if (if (imageUrl != null) imageUrl != that.imageUrl else that.imageUrl != null)
            return false
        if (if (position != null) position != that.position else that.position != null)
            return false
        if (if (name != null) name != that.name else that.name != null) return false
        if (if (thumbnailUrl != null)
                thumbnailUrl != that.thumbnailUrl
            else
                that.thumbnailUrl != null
        )
            return false
        if (if (level != null) level != that.level else that.level != null) return false
        return if (if (urlKey != null) urlKey != that.urlKey else that.urlKey != null) false else true
    }

    override fun hashCode(): Int {
        var result = if (isActive != null) isActive!!.hashCode() else 0
        result = 31 * result + if (categoryId != null) categoryId!!.hashCode() else 0
        result = 31 * result + if (imageUrl != null) imageUrl!!.hashCode() else 0
        result = 31 * result + if (position != null) position!!.hashCode() else 0
        result = 31 * result + if (name != null) name!!.hashCode() else 0
        result = 31 * result + if (thumbnailUrl != null) thumbnailUrl!!.hashCode() else 0
        result = 31 * result + if (level != null) level!!.hashCode() else 0
        result = 31 * result + if (urlKey != null) urlKey!!.hashCode() else 0
        return result
    }

    override fun toString(): String {
        return "CategoryModel{" +
                "isActive='" + isActive + '\''.toString() +
                ", categoryId='" + categoryId + '\''.toString() +
                ", imageUrl='" + imageUrl + '\''.toString() +
                ", position='" + position + '\''.toString() +
                ", name='" + name + '\''.toString() +
                ", thumbnailUrl=" + thumbnailUrl +
                ", level='" + level + '\''.toString() +
                ", urlKey='" + urlKey + '\''.toString() +

                '}'.toString()
    }
}