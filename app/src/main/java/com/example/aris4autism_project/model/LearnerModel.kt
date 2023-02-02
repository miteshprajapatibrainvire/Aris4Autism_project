package com.example.aris4autism_project.model

import android.os.Parcel
import android.os.Parcelable

class LearnerModel(var id:Int,var name:String,var gen:String,
var yearDetails:String,var dob:String,var monthPlan:String,var startToEndDob:String,var activeStatus:Boolean,var startDob:String,var  endDob:String,var dignosis:ArrayList<DiagnosisModel>):Parcelable
{


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        TODO("dignosis")
    ) {
    }

    override fun toString(): String {
        return "LearnerModel(name='$name', gen='$gen', yearDetails='$yearDetails', dob='$dob', monthPlan='$monthPlan', startToEndDob='$startToEndDob', activeStatus=$activeStatus)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(gen)
        parcel.writeString(yearDetails)
        parcel.writeString(dob)
        parcel.writeString(monthPlan)
        parcel.writeString(startToEndDob)
        parcel.writeByte(if (activeStatus) 1 else 0)
        parcel.writeString(startDob)
        parcel.writeString(endDob)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LearnerModel> {
        override fun createFromParcel(parcel: Parcel): LearnerModel {
            return LearnerModel(parcel)
        }

        override fun newArray(size: Int): Array<LearnerModel?> {
            return arrayOfNulls(size)
        }
    }


}