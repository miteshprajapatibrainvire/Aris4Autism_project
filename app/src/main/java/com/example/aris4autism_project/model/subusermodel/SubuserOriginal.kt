package com.example.aris4autism_project.model.subusermodel


import com.google.gson.annotations.SerializedName

data class SubuserOriginal(
    @SerializedName("data")
    val `data`: ArrayList<SubUserData>,
    @SerializedName("draw")
    val draw: Int,
    @SerializedName("input")
    val input: SubUserInput,
    @SerializedName("queries")
    val queries: List<SubUserQuery>,
    @SerializedName("recordsFiltered")
    val recordsFiltered: Int,
    @SerializedName("recordsTotal")
    val recordsTotal: Int
)