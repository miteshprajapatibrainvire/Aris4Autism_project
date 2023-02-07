package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class Input(
    @SerializedName("length")
    val length: Any,
    @SerializedName("start")
    val start: Int
)