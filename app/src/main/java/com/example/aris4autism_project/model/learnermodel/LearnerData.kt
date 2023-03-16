package com.example.aris4autism_project.model.learnermodel


import com.example.aris4autism_project.Utils.CalenderFormat
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.model.UserSubscriptions
import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class LearnerData(
    @SerializedName("age")
    val age: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("date_of_birth")
    val dateOfBirth: String,
    @SerializedName("extra_note")
    val extraNote: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("get_diagnosis_data")
    val getDiagnosisData: ArrayList<LearnerDiagnosisData>,
    @SerializedName("get_learner_icon")
    val getLearnerIcon: GetLearnerIcon,
    @SerializedName("icon_id")
    val iconId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("notification_pref")
    val notificationPref: NotificationPref,
    @SerializedName("srNum")
    val srNum: Int,
    @SerializedName("subscription_id")
    val subscriptionId: Int,
    @SerializedName("user_subscriptions")
    val userSubscriptions: UserSubscriptions,
    @SerializedName("uuid")
    val uuid: String
)
{

    fun getStatusUserSubscription():String{
        if(this.userSubscriptions==null)
        {
            return "Expired"
        }
        else
        {
            return "Active"
        }
    }

    fun getuserSubscriptinStatus():Boolean{
        if(this.userSubscriptions==null)
        {
            return false
        }
        else
        {
            return true
        }
    }
    override fun hashCode():Int
    {
        if(this.id==0)
        {
            return 0
        }
        else{
            return this.id
        }
    }

    fun getDob():String{
        if(dateOfBirth.isEmpty())
        {
            return " "
        }
        else
        {
            return "DOB :${dateOfBirth}"
        }
    }

    fun dobToAge(): String
    {
        return if (!Utils.checkDateFormat(dateOfBirth, CalenderFormat.MM_DD_YYYY_D.type)) {
            val formatter: DateFormat =
                SimpleDateFormat(CalenderFormat.YYYY_MM_DD.type, Locale.ROOT)
            val formatter2: DateFormat =
                SimpleDateFormat(CalenderFormat.MM_DD_YYYY_D.type, Locale.ROOT)
            val date = formatter.parse(dateOfBirth) as Date
            val date2 = formatter2.format(date)
            Utils.calculateAge(date2)
        } else {
            Utils.calculateAge(dateOfBirth)
        }
    }
}