package com.example.aris4autism_project.model.overviewmodel


import com.google.gson.annotations.SerializedName

data class Query(
    @SerializedName("bindings")
    val bindings: List<Int>,
    @SerializedName("query")
    val query: String,
    @SerializedName("time")
    val time: Double
)