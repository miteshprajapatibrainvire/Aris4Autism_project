package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class OriginalXX(
    @SerializedName("data")
    val `data`: List<DataXXXXXXXXX>,
    @SerializedName("draw")
    val draw: Int,
    @SerializedName("input")
    val input: InputXX,
    @SerializedName("queries")
    val queries: List<QueryXX>,
    @SerializedName("recordsFiltered")
    val recordsFiltered: Int,
    @SerializedName("recordsTotal")
    val recordsTotal: Int
)