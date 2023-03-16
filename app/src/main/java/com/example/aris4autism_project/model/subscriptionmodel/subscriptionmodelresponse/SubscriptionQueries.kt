package com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse


import com.google.gson.annotations.SerializedName

data class SubscriptionQueries(
    @SerializedName("bindings")
    val bindings: List<Any>,
    @SerializedName("query")
    val query: String,
    @SerializedName("time")
    val time: Double
)