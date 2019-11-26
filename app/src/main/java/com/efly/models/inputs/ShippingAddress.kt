package com.eflyrx.app.data.model.inputs

import android.os.Parcel
import android.os.Parcelable
import com.efly.models.CustomAttribute

class ShippingAddress() : Parcelable {
    var city: String = ""
    var country_id: String = ""
    var customer_id: Int = 0
    var email: String = ""
    var firstname: String = ""
    var lastname: String = ""
    var postcode: String = ""
    var region: String = ""
    var region_code: String = ""
    var region_id: Int = 0
    var same_as_billing: Int = 0
    var street: List<String>? = null
    var telephone: String = ""
    var custom_attributes: List<CustomAttribute>? = null

    constructor(parcel: Parcel) : this() {
        city = parcel.readString()
        country_id = parcel.readString()
        customer_id = parcel.readInt()
        email = parcel.readString()
        firstname = parcel.readString()
        lastname = parcel.readString()
        postcode = parcel.readString()
        region = parcel.readString()
        region_code = parcel.readString()
        region_id = parcel.readInt()
        same_as_billing = parcel.readInt()
        street = parcel.createStringArrayList()
        telephone = parcel.readString()
        custom_attributes = parcel.createTypedArrayList(CustomAttribute.CREATOR)
    }
//
//    constructor(city: String, country_id: String, customer_id: Int, email: String, firstname: String, lastname: String, postcode: String,
//                region: String, regioncode: String, region_id: Int, sameasbilling: Int, street: MutableList<String>, telephone: String, custom_attributes: MutableList<CustomAttribute>) {
//
//    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(city)
        parcel.writeString(country_id)
        parcel.writeInt(customer_id)
        parcel.writeString(email)
        parcel.writeString(firstname)
        parcel.writeString(lastname)
        parcel.writeString(postcode)
        parcel.writeString(region)
        parcel.writeString(region_code)
        parcel.writeInt(region_id)
        parcel.writeInt(same_as_billing)
        parcel.writeStringList(street)
        parcel.writeString(telephone)
        parcel.writeTypedList(custom_attributes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShippingAddress> {
        override fun createFromParcel(parcel: Parcel): ShippingAddress {
            return ShippingAddress(parcel)
        }

        override fun newArray(size: Int): Array<ShippingAddress?> {
            return arrayOfNulls(size)
        }
    }
}