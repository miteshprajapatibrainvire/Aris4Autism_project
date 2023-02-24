package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class Feature(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("enabled")
    val enabled: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("subscription_id")
    val subscriptionId: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)