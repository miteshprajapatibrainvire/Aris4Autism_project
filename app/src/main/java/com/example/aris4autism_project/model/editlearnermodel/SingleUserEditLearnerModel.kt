package com.example.aris4autism_project.model.editlearnermodel


import com.example.aris4autism_project.model.editlearnermodel.SingleEditUserSubscriptions
import com.example.aris4autism_project.model.learnermodel.LearnerDiagnosisData
import com.google.gson.annotations.SerializedName

data class SingleUserEditLearnerModel(
//    @SerializedName("data")
//    val `data`: DataXXXXXXXXXXXXXXXXXXXXXX,
//    @SerializedName("meta")
//    val meta: MetaXXXXXXXXXXXXXXXX
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
    val extraNote: Any,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("get_diagnosis_data")
    val getDiagnosisData: ArrayList<LearnerDiagnosisData>,
    @SerializedName("get_learner_icon")
    val getLearnerIcon: SingleEditUserGetLearnerIcon,
    @SerializedName("icon_id")
    val iconId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("notification_on")
    val notificationOn: Int,
    @SerializedName("subscription_id")
    val subscriptionId: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("updated_by")
    val updatedBy: Any,
    @SerializedName("user_subscriptions")
    val userSubscriptions: SingleEditUserSubscriptions,
    @SerializedName("uuid")
    val uuid: String
)
{
    override fun toString(): String {
        return "EditLearnerModelResponse(age=$age, ageUnit='$ageUnit', careTakerId=$careTakerId, createdAt='$createdAt', createdBy=$createdBy, dateOfBirth='$dateOfBirth', deletedAt=$deletedAt, deletedBy=$deletedBy, extraNote=$extraNote, gender='$gender', getDiagnosisData=$getDiagnosisData, getLearnerIcon=$getLearnerIcon, iconId=$iconId, id=$id, name='$name', notificationOn=$notificationOn, subscriptionId=$subscriptionId, updatedAt='$updatedAt', updatedBy=$updatedBy, userSubscriptions=$userSubscriptions, uuid='$uuid')"
    }
}
