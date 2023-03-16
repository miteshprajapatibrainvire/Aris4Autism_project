package com.example.aris4autism_project.model.statemodel


import com.google.gson.annotations.SerializedName

data class StateResponseModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
{
    override fun toString(): String
    {
        return "ResponseStateModel(id=$id, name='$name')"
    }
}
