package com.example.aris4autism_project.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class GetDiagnosisData(
    @SerializedName("diagnosis_id")
    val diagnosisId: Int,
    @SerializedName("diagnosis_title")
    val diagnosisTitle: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("learners_id")
    val learnersId: Int,
    @SerializedName("slug")
    val slug: String
):java.io.Serializable
{
    override fun hashCode(): Int {
        val result = id.hashCode()
        val diagnosisId=diagnosisId.hashCode()
        val learnersId=learnersId.hashCode()

        return result
    }

    override fun toString(): String {
        return "GetDiagnosisData(diagnosisId=$diagnosisId, diagnosisTitle='$diagnosisTitle', id=$id, learnersId=$learnersId, slug='$slug')"
    }
}