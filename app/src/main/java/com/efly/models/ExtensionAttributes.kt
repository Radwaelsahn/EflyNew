package com.efly.models

import android.os.Parcel
import android.os.Parcelable


 class ExtensionAttributes() :Parcelable {
         val checkout_fields: List<CheckoutField>? = null
         val agreement_ids: List<String>? = null
         val seller_data: String?= null
         val amazon_id: String? = ""
         val is_subscribed: String? = ""
         val vertex_customer_code: String? = ""

         constructor(parcel: Parcel) : this() {
         }

         override fun writeToParcel(parcel: Parcel, flags: Int) {

         }

         override fun describeContents(): Int {
                 return 0
         }

         companion object CREATOR : Parcelable.Creator<ExtensionAttributes> {
                 override fun createFromParcel(parcel: Parcel): ExtensionAttributes {
                         return ExtensionAttributes(parcel)
                 }

                 override fun newArray(size: Int): Array<ExtensionAttributes?> {
                         return arrayOfNulls(size)
                 }
         }
 }