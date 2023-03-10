package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class Subscription(
    @SerializedName("currency_symbol")
    val currencySymbol: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("status")
    var status: String
) {
    fun statusData(): String {
        if (status.isEmpty()) {
            this.status = "Expired"
            return status
        } else {
            this.status="Active"
            return status
        }
    }

    fun statusBool(): Boolean {
        if (status.isEmpty()) {
            return true
        } else {
            return false
        }
    }
}