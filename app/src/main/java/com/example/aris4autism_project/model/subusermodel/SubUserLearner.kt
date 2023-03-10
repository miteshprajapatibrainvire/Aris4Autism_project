package com.example.aris4autism_project.model.subusermodel


import com.google.gson.annotations.SerializedName

data class SubUserLearner(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)