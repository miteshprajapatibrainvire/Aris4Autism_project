package com.example.aris4autism_project.model.userprofilemodel


import com.google.gson.annotations.SerializedName

data class ProfileOriginal(
    @SerializedName("data")
    val `data`: ArrayList<ProfileData>,
    @SerializedName("draw")
    val draw: Int,
    @SerializedName("input")
    val input: ProfileInput,
    @SerializedName("queries")
    val queries: List<ProfileQuery>,
    @SerializedName("recordsFiltered")
    val recordsFiltered: Int,
    @SerializedName("recordsTotal")
    val recordsTotal: Int
)
{
    override fun toString(): String {
        return "OriginalXXXXX(`data`=$`data`, draw=$draw, input=$input, queries=$queries, recordsFiltered=$recordsFiltered, recordsTotal=$recordsTotal)"
    }
}
