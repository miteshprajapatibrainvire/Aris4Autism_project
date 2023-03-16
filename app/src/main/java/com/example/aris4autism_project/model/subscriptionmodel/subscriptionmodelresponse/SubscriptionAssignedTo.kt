package com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse


import com.example.aris4autism_project.Utils.CalenderFormat
import com.example.aris4autism_project.Utils.Utils
import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

data class SubscriptionAssignedTo(
    @SerializedName("age")
    val age: Int,
    @SerializedName("age_unit")
    val ageUnit: String,
    @SerializedName("care_taker_id")
    val careTakerId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("created_by")
    val createdBy: Int,
    @SerializedName("date_of_birth")
    val dateOfBirth: String,
    @SerializedName("deleted_at")
    val deletedAt: Any,
    @SerializedName("deleted_by")
    val deletedBy: Any,
    @SerializedName("extra_note")
    val extraNote: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("icon_id")
    val iconId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("learner_icon")
    val learnerIcon: SubscriptionLearnerIcon,
    @SerializedName("name")
    val name: String,
    @SerializedName("notification_on")
    val notificationOn: Int,
    @SerializedName("subscription_id")
    val subscriptionId: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("updated_by")
    val updatedBy: Int,
    @SerializedName("uuid")
    val uuid: String
)
{

    @JvmName("getName1")
    fun getName():String {

        if (this.name.isEmpty()) {
            return " "
        }

            return this.name

    }


    fun getInvoiceId():String
    {
       return "INVOICE ID: #"+subscriptionId.toString()

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
