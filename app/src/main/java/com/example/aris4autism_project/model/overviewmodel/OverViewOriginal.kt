package com.example.aris4autism_project.model.overviewmodel


import com.google.gson.annotations.SerializedName

data class OverViewOriginal(
    @SerializedName("data")
    val `data`: ArrayList<OverViewListData>,
    @SerializedName("draw")
    val draw: Int,
    @SerializedName("input")
    val input: Input,
    @SerializedName("queries")
    val queries: ArrayList<OverViewQuery>,
    @SerializedName("recordsFiltered")
    val recordsFiltered: Int,
    @SerializedName("recordsTotal")
    val recordsTotal: Int
)