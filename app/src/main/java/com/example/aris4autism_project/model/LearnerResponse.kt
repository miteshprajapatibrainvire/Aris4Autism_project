package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class LearnerResponse(
    @SerializedName("data")
    val `data`: DataXXXX,
    @SerializedName("meta")
    val meta: MetaXXXX
)