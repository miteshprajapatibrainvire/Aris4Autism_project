package com.example.aris4autism_project.model.userprofilemodel


import com.google.gson.annotations.SerializedName

data class ProfileQuery(
    @SerializedName("bindings")
    val bindings: List<String>,
    @SerializedName("query")
    val query: String,
    @SerializedName("time")
    val time: Double
)