package com.example.aris4autism_project.model


import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.aris4autism_project.R
import com.google.gson.annotations.SerializedName

data class UserSubscriptions(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("currency_symbol")
    val currencySymbol: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("grandtotal")
    val grandtotal: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("title")
    val title: String
)
{
    fun statusBool():Boolean{
        if(status.isEmpty())
        {
            return false
        }
        else {
            return true
        }
    }

    fun statusData():String {
        if(status.isEmpty())
        {
            return "Expired"
        }
        else
        {
            return "Active"
        }
    }

    fun fullStartandEnd():String
    {
        if(startDate.isEmpty())
        {
            return ""
        }
        else
        {
            return "${startDate} to ${endDate}"
        }
    }

}