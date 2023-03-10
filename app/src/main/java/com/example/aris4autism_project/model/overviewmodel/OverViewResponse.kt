package com.example.aris4autism_project.model.overviewmodel


import com.google.gson.annotations.SerializedName

data class OverViewResponse(

    @SerializedName("original")
    val original: OverViewOriginal
)