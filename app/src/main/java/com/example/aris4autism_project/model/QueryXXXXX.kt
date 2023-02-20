package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class QueryXXXXX(
    @SerializedName("bindings")
    val bindings: List<String>,
    @SerializedName("query")
    val query: String,
    @SerializedName("time")
    val time: Double
)