package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class Original(
    @SerializedName("data")
    val `data`: List<DataXXXXX>,
    @SerializedName("draw")
    val draw: Int,
    @SerializedName("input")
    val input: Input,
    @SerializedName("queries")
    val queries: List<Query>,
    @SerializedName("recordsFiltered")
    val recordsFiltered: Int,
    @SerializedName("recordsTotal")
    val recordsTotal: Int
)