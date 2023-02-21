package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class GetDiagnosisDataXXX(
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
)