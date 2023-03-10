package com.example.aris4autism_project.model.subusermodel


import com.google.gson.annotations.SerializedName

data class SubUserQuery(
    @SerializedName("bindings")
    val bindings: List<Any>,
    @SerializedName("query")
    val query: String,
    @SerializedName("time")
    val time: Double
)