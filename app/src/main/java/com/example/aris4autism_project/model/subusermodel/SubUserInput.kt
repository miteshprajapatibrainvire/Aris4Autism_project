package com.example.aris4autism_project.model.subusermodel


import com.google.gson.annotations.SerializedName

data class SubUserInput(
    @SerializedName("length")
    val length: Any,
    @SerializedName("start")
    val start: Int
)