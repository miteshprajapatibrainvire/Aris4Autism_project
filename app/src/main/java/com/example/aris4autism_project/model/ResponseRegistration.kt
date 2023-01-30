package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class ResponseRegistration(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("meta")
    val meta: MetaX
)