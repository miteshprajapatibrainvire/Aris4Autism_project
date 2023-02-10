package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class QueryXX(
    @SerializedName("bindings")
    val bindings: List<Int>,
    @SerializedName("query")
    val query: String,
    @SerializedName("time")
    val time: Double
)