package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class ResponseCountryModel(
    @SerializedName("data")
    val `data`: List<DataXX>,
    @SerializedName("meta")
    val meta: MetaXX
)