package com.example.aris4autism_project.model.diagnosismodel


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DiagnosisDetailResponseModel(
    @SerializedName("id")
    val id: Int=0,
    @SerializedName("title")
    val title: String="",
    @SerializedName("slug")
    val slug: String="",

    var isItemChecked : Boolean = false,
    var isNoItemChecked : Boolean = false,

//    @SerializedName("data")
//    var data: List<InnerData>

//    @SerializedName("meta")
//    val meta: MetaXXXXXXXXXXXXXXXXXXXX
):Parcelable
{

//    override fun toString(): String {
//        return "DiagnosisDetailResponse(`data`=$`data`, meta=$meta)"
//    }
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
    parcel.readString().toString(),
    parcel.readString().toString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(slug)
        parcel.writeByte(if (isItemChecked) 1 else 0)
        parcel.writeByte(if (isNoItemChecked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DiagnosisDetailResponseModel> {
        override fun createFromParcel(parcel: Parcel): DiagnosisDetailResponseModel {
            return DiagnosisDetailResponseModel(parcel)
        }

        override fun newArray(size: Int): Array<DiagnosisDetailResponseModel?> {
            return arrayOfNulls(size)
        }
    }

}
