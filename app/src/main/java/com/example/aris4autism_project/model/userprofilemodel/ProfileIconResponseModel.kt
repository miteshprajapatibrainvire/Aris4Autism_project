package com.example.aris4autism_project.model.userprofilemodel


import com.google.gson.annotations.SerializedName

data class ProfileIconResponseModel(
    @SerializedName("exception")
    val exception: Any,
    @SerializedName("headers")
    val headers: ProfileHeaders,
    @SerializedName("original")
    val Profileoriginal: ProfileOriginal
)
{
    override fun toString(): String {
        return "ProfileIconResponseModel(exception=$exception, headers=$headers, Profileoriginal=$Profileoriginal)"
    }
}
