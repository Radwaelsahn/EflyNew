//package com.efly.models
//
//import android.os.Parcel
//import android.os.Parcelable
//import com.google.gson.annotations.Expose
//import com.google.gson.annotations.SerializedName
//
//class ProductModel() :Parcelable{
//
//    @SerializedName("entity_id")
//    @Expose
//    var entityId: String? = null
//
//    @SerializedName("short_description")
//    @Expose
//    var desc: String? = null
//
//    @SerializedName("image")
//    @Expose
//    var imageUrl: String? = null
//
//    @SerializedName("special_price")
//    @Expose
//    var specialPrice: String? = null
//
//    @SerializedName("price")
//    @Expose
//    var price: String? = null
//
//    @SerializedName("name")
//    @Expose
//    var name: String? = null
//
//    @SerializedName("sku")
//    @Expose
//    var sku: String? = null
//
//    @SerializedName("row_total_incl_tax")
//    @Expose
//    val totalPrice: Double = 0.toDouble()
//
//    @SerializedName("qty")
//    @Expose
//    var count = 0
//
//    constructor(parcel: Parcel) : this() {
//        desc = parcel.readString()
//        imageUrl = parcel.readString()
//        specialPrice = parcel.readString()
//        entityId = parcel.readString()
//        price = parcel.readString()
//        name = parcel.readString()
//        sku = parcel.readString()
//        count = parcel.readInt()
//    }
//
//    fun add() {
//        count = count + 1
//    }
//
//    fun subtract() {
//        if (count > 0) {
//            count = count - 1
//        }
//    }
//
//    override fun equals(o: Any?): Boolean {
//        if (this === o) return true
//        if (o == null || javaClass != o.javaClass) return false
//
//        val that = o as ProductModel?
//
//        return if (entityId != null) entityId == that!!.entityId else that!!.entityId == null
//
//    }
//
//    override fun hashCode(): Int {
//        return if (entityId != null) entityId!!.hashCode() else 0
//    }
//
//    override fun toString(): String {
//        return "ProductModel{" +
//                //                        ",symbol = '" + symbol + '\'' +
//                ",image_url = '" + imageUrl + '\''.toString() +
//                //                        ",regular_price_with_tax = '" + regularPriceWithTax + '\'' +
//                //                        ",stock_level = '" + stockLevel + '\'' +
//                ",entity_id = '" + entityId + '\''.toString() +
//                //                        ",url_key = '" + urlKey + '\'' +
//                //                        ",final_price_with_tax = '" + finalPriceWithTax + '\'' +
//                ",price = '" + price + '\''.toString() +
//                ",name = '" + name + '\''.toString() +
//                ",sku = '" + sku + '\''.toString() +
//                "}"
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(desc)
//        parcel.writeString(imageUrl)
//        parcel.writeString(specialPrice)
//        parcel.writeString(entityId)
//        parcel.writeString(price)
//        parcel.writeString(name)
//        parcel.writeString(sku)
//        parcel.writeInt(count)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<ProductModel> {
//        override fun createFromParcel(parcel: Parcel): ProductModel {
//            return ProductModel(parcel)
//        }
//
//        override fun newArray(size: Int): Array<ProductModel?> {
//            return arrayOfNulls(size)
//        }
//    }
//}