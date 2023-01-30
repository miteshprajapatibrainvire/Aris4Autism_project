package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class ResponseStateModel(
    @SerializedName("data")
    val `data`: List<DataXXX>,
    @SerializedName("meta")
    val meta: MetaXXX
)