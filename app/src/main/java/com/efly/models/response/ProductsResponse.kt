package com.efly.models.response

import android.os.Parcel
import android.os.Parcelable
import com.efly.models.ProductModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ProductsResponse() :Parcelable{


    @SerializedName("msg")
    @Expose
    internal var msg: String = ""

    @SerializedName("code")
    @Expose
    var code: Int = 0

    @SerializedName("model")
    @Expose
    var model: List<ProductModel>?= null

    constructor(parcel: Parcel) : this() {
        msg = parcel.readString()
        code = parcel.readInt()
        model = parcel.createTypedArrayList(ProductModel)
    }

    fun setMsg(msg: String) {
        this.msg = msg
    }

    fun getMsg(): Any {
        return msg
    }

    override fun toString(): String {
        return "Product{" +
                "msg = '" + msg + '\''.toString() +
                ",code = '" + code + '\''.toString() +
                ",model = '" + model + '\''.toString() +
                "}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(msg)
        parcel.writeInt(code)
        parcel.writeTypedList(model)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductsResponse> {
        override fun createFromParcel(parcel: Parcel): ProductsResponse {
            return ProductsResponse(parcel)
        }

        override fun newArray(size: Int): Array<ProductsResponse?> {
            return arrayOfNulls(size)
        }
    }
}