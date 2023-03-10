package com.example.aris4autism_project.model.overviewmodel


import com.google.gson.annotations.SerializedName

data class OverViewOriginal(
    @SerializedName("data")
    val `data`: List<Data>,
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