package com.efly.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Region(
    @SerializedName("region")//"name")
    var region: String?,
    @SerializedName("region_code")//"code")
    var region_code: String?,
    @SerializedName("region_id")
    var region_id: Int


) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(region)
        parcel.writeString(region_code)
        parcel.writeInt(region_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Region> {
        override fun createFromParcel(parcel: Parcel): Region {
            return Region(parcel)
        }

        override fun newArray(size: Int): Array<Region?> {
            return arrayOfNulls(size)
        }
    }
}
