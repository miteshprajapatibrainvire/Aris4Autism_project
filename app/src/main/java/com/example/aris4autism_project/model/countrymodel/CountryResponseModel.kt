package com.example.aris4autism_project.model.countrymodel


import com.google.gson.annotations.SerializedName

data class CountryResponseModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
{
    override fun toString(): String {
        return "CountryModelResponse(id=$id, name='$name')"
    }
}