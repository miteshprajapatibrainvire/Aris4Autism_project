package com.example.aris4autism_project.model


import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.aris4autism_project.Utils.CalenderFormat
import com.example.aris4autism_project.Utils.Utils
import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class DataXXXXX(
    @SerializedName("age")
    val age: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("date_of_birth")
    val dateOfBirth: String,
    @SerializedName("extra_note")
    val extraNote: Any,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("get_diagnosis_data")
    val getDiagnosisData: ArrayList<GetDiagnosisData>,
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
):java.io.Serializable
{
    override fun toString(): String {
        return "DataXXXXX(age=$age, createdAt='$createdAt', dateOfBirth='$dateOfBirth', extraNote=$extraNote, gender='$gender', getDiagnosisData=$getDiagnosisData, getLearnerIcon=$getLearnerIcon, iconId=$iconId, id=$id, name='$name', notificationPref=$notificationPref, srNum=$srNum, subscriptionId=$subscriptionId, userSubscriptions=$userSubscriptions, uuid='$uuid')"
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


    fun getDob():String{
        if(dateOfBirth.isEmpty())
        {
            return ""
        }
        else
        {
            return "DOB :${dateOfBirth}"
        }
    }
}