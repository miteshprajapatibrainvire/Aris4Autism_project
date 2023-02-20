package com.example.aris4autism_project.model


import com.example.aris4autism_project.Utils.CalenderFormat
import com.example.aris4autism_project.Utils.Utils
import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

data class DataXXXXXXXXXXX(
    @SerializedName("age")
    val age: Int,
    @SerializedName("assessment")
    val assessment: AssessmentX,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("get_learner_icon")
    val getLearnerIcon: GetLearnerIconXX,
    @SerializedName("icon_id")
    val iconId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("srNum")
    val srNum: Int,
    @SerializedName("sub_user_ids")
    val subUserIds: List<SubUserIdX>,
    @SerializedName("subscription_id")
    val subscriptionId: Int,
    @SerializedName("user_subscriptions")
    val userSubscriptions: UserSubscriptionsXX,
    @SerializedName("uuid")
    val uuid: String
):java.io.Serializable
{

    override fun toString(): String {
        return "DataXXXXXXXXXXX(age=$age, assessment=$assessment, dateOfBirth='$dateOfBirth', gender='$gender', getLearnerIcon=$getLearnerIcon, iconId=$iconId, id=$id, name='$name', srNum=$srNum, subUserIds=$subUserIds, subscriptionId=$subscriptionId, userSubscriptions=$userSubscriptions, uuid='$uuid')"
    }

    fun getDob():String {

        return "DOB : "+dateOfBirth

    }




    fun dobToAge(): String {
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