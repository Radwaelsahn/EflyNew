package com.efly.models.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class RegionFetched(
        @SerializedName("name")
        val region: String,
        @SerializedName("code")
        val region_code: String,
        @SerializedName("id")
        val region_id: Int,
        val extension_attributes: String


) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(region)
        parcel.writeString(region_code)
        parcel.writeInt(region_id)
        parcel.writeString(extension_attributes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RegionFetched> {
        override fun createFromParcel(parcel: Parcel): RegionFetched {
            return RegionFetched(parcel)
        }

        override fun newArray(size: Int): Array<RegionFetched?> {
            return arrayOfNulls(size)
        }
    }
}
