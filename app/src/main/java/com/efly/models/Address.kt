package com.efly.models

import android.os.Parcel
import android.os.Parcelable
import com.efly.models.CustomAttribute
import com.efly.models.Region
import com.efly.models.ShippingAddress
import com.google.gson.annotations.SerializedName

class Address() : Parcelable {
    var city: String? = ""
    var company: String? = ""
    var region: Region? = null
    @SerializedName("country_id")
    var country_id: String = "US"
    var custom_attributes: List<CustomAttribute>? = null
    var customer_id: Int = 0
    var default_billing: String = "true"
    var default_shipping: String = "true"
    var extension_attributes: String? = ""
    var fax: String? = ""
    var firstname: String? = ""
    var id: Int = 0
    var lastname: String? = ""
    var middlename: String? = ""
    var postcode: String? = ""
    var prefix: String? = ""
    var region_id: Int = 0
    var street: List<String>? = null
    var suffix: String? = ""
    var telephone: String? = ""
    var vat_id: Int = 0

    constructor(parcel: Parcel) : this() {
        city = parcel.readString()
        company = parcel.readString()
        country_id = parcel.readString()!!
        customer_id = parcel.readInt()
        default_billing = parcel.readString()!!
        default_shipping = parcel.readString()!!
        extension_attributes = parcel.readString()
        fax = parcel.readString()
        firstname = parcel.readString()
        id = parcel.readInt()
        lastname = parcel.readString()
        middlename = parcel.readString()
        postcode = parcel.readString()
        prefix = parcel.readString()
        region = parcel.readParcelable(Region::class.java.classLoader)
//        region_code = parcel.readString()
        region_id = parcel.readInt()
        street = parcel.createStringArrayList()
        suffix = parcel.readString()
        telephone = parcel.readString()
        vat_id = parcel.readInt()
//        same_as_billing = parcel.readInt()

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(city)
        parcel.writeString(company)
        parcel.writeString(country_id)
        parcel.writeInt(customer_id)
        parcel.writeString(default_billing)
        parcel.writeString(default_shipping)
        parcel.writeString(extension_attributes)
        parcel.writeString(fax)
        parcel.writeString(firstname)
        parcel.writeInt(id)
        parcel.writeString(lastname)
        parcel.writeString(middlename)
        parcel.writeString(postcode)
        parcel.writeString(prefix)
        parcel.writeParcelable(region, flags)
//        parcel.writeString(region_code)
        parcel.writeInt(region_id)
        parcel.writeStringList(street)
        parcel.writeString(suffix)
        parcel.writeString(telephone)
        parcel.writeInt(vat_id)
//        parcel.writeInt(same_as_billing)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Address> {
        override fun createFromParcel(parcel: Parcel): Address {
            return Address(parcel)
        }

        override fun newArray(size: Int): Array<Address?> {
            return arrayOfNulls(size)
        }

        @JvmStatic
        fun convertToShippingAddress(addressModel: Address): ShippingAddress {
            val address = ShippingAddress()
            address.city = addressModel.city!!
            address.country_id = addressModel.country_id!!
            address.customer_id = addressModel.customer_id!!
            address.firstname = addressModel.firstname!!
            address.lastname = addressModel.lastname!!
            address.postcode = addressModel.postcode!!
            address.region = addressModel.city!!
            address.region_code = addressModel.region_id.toString() + ""
            address.region_id = addressModel.region_id!!
            address.same_as_billing = 1
            address.street = addressModel.street!!
            address.telephone = addressModel.telephone!!
            address.custom_attributes = addressModel.custom_attributes

            return address

        }
    }

    fun Address() {}

}