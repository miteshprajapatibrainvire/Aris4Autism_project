package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class UserOrderSubscriptionX(
    @SerializedName("autorenew_info")
    val autorenewInfo: Any,
    @SerializedName("id")
    val id: Int
)
{
    override fun hashCode(): Int {
        var result = id.hashCode()

        return result
    }
}
