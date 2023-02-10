package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class OriginalXXX(
    @SerializedName("data")
    val `data`: List<DataXXXXXXXXXXX>,
    @SerializedName("draw")
    val draw: Int,
    @SerializedName("input")
    val input: InputXXX,
    @SerializedName("queries")
    val queries: List<QueryXXX>,
    @SerializedName("recordsFiltered")
    val recordsFiltered: Int,
    @SerializedName("recordsTotal")
    val recordsTotal: Int
)