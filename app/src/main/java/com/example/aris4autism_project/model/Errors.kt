package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class Errors(
    @SerializedName("gender")
    val gender: List<String>
)