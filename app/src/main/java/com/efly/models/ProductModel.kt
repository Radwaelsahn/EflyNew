package com.efly.models

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import kotlinx.android.parcel.Parcelize


class ProductModel() : Parcelable {

    @SerializedName("short_description")
    @Expose
    var desc: String? = null

    @SerializedName("image")
    @Expose
    var imageUrl: String? = null

    @SerializedName("special_price")
    @Expose
    var specialPrice: String? = null

    //    @SerializedName("product_id")
    @SerializedName("entity_id")
    @Expose
    var entityId: String? = null

    @SerializedName("price")
    @Expose
    var price: Double = 0.toDouble()

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("sku")
    @Expose
    var sku: String? = null

    @SerializedName("row_total_incl_tax")
    @Expose
    val totalPrice: Double = 0.toDouble()

    @SerializedName("qty")
    @Expose
    var count = 0

    constructor(parcel: Parcel) : this() {
        desc = parcel.readString()
        imageUrl = parcel.readString()
        specialPrice = parcel.readString()
        entityId = parcel.readString()
        price = parcel.readDouble()
        name = parcel.readString()
        sku = parcel.readString()
        count = parcel.readInt()
    }

    fun add() {
        count = count + 1
    }

    fun subtract() {
        if (count > 0) {
            count = count - 1
        }
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val that = o as ProductModel?

        return if (entityId != null) entityId == that!!.entityId else that!!.entityId == null

    }

    override fun hashCode(): Int {
        return if (entityId != null) entityId!!.hashCode() else 0
    }

    override fun toString(): String {
        return "ProductModel{" +
                //                        ",symbol = '" + symbol + '\'' +
                ",image_url = '" + imageUrl + '\''.toString() +
                //                        ",regular_price_with_tax = '" + regularPriceWithTax + '\'' +
                //                        ",stock_level = '" + stockLevel + '\'' +
                ",entity_id = '" + entityId + '\''.toString() +
                //                        ",url_key = '" + urlKey + '\'' +
                //                        ",final_price_with_tax = '" + finalPriceWithTax + '\'' +
                ",price = '" + price + '\''.toString() +
                ",name = '" + name + '\''.toString() +
                ",sku = '" + sku + '\''.toString() +
                "}"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(desc)
        dest.writeString(imageUrl)
        dest.writeString(specialPrice)
        dest.writeString(entityId)
        dest.writeDouble(price)
        dest.writeString(name)
        dest.writeString(sku)
        dest.writeInt(count)
    }

    companion object CREATOR : Parcelable.Creator<ProductModel> {
        override fun createFromParcel(parcel: Parcel): ProductModel {
            return ProductModel(parcel)
        }

        override fun newArray(size: Int): Array<ProductModel?> {
            return arrayOfNulls(size)
        }
    }
}