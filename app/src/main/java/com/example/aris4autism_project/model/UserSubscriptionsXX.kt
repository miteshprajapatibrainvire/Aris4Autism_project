package com.example.aris4autism_project.model


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

data class UserSubscriptionsXX(
    @SerializedName("currency_symbol")
    val currencySymbol: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("subscription_order_id")
    val subscriptionOrderId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("user_order_subscription")
    val userOrderSubscription: UserOrderSubscriptionX
)
{



    @JvmName("getStatus1")
    fun getStatus():String
    {
        if(status=="1")
        {
            return "Active"
        }
        else
        {
            return "Pending"
        }
    }

    fun getStatusBackground():Boolean
    {
        if(status.equals("active",true))
        {
            Log.e("status=",status.toString())
            return true
        }
        else
        {
            Log.e("statusfalse=",status.toString())
            return false
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fullDate():String
    {

        var parser = SimpleDateFormat("dd-MM-yyyy")
        var formatter = SimpleDateFormat("MMM dd yyyy")
        var startDob: String = formatter.format(parser.parse(startDate))
        var endDob:String=formatter.format(parser.parse(endDate))

        return startDob+" to "+endDob
    }
}