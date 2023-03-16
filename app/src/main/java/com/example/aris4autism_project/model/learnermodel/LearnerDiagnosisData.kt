package com.example.aris4autism_project.model.learnermodel

import com.google.gson.annotations.SerializedName

data class LearnerDiagnosisData(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("diagnosis_id")
    val diagnosisId: Int = 0,
    @SerializedName("learners_id")
    val learnersId: Int = 0,
    @SerializedName("diagnosis_title")
    val diagnosisTitle: String = "",
    @SerializedName("slug")
    val slug: String = ""
) : java.io.Serializable {
    override fun hashCode(): Int {
        val result = id.hashCode()
        val diagnosisId = diagnosisId.hashCode()
        val learnersId = learnersId.hashCode()

        return result
    }

    override fun toString(): String {
        return "GetDiagnosisData(diagnosisId=$diagnosisId, diagnosisTitle='$diagnosisTitle', id=$id, learnersId=$learnersId, slug='$slug')"
    }
}