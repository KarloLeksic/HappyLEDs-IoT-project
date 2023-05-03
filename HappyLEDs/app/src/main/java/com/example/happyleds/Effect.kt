package com.example.happyleds

import android.os.Parcel
import android.os.Parcelable

data class Effect(val image: Int, val text: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(text)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Effect> {
        override fun createFromParcel(parcel: Parcel): Effect {
            return Effect(parcel)
        }

        override fun newArray(size: Int): Array<Effect?> {
            return arrayOfNulls(size)
        }
    }
}
