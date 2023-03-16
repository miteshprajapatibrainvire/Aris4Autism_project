package com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse


import com.google.gson.annotations.SerializedName

data class SubscriptionOriginal(
    @SerializedName("data")
    val `data`: ArrayList<SubscriptionData>,
    @SerializedName("draw")
    val draw: Int,
    @SerializedName("input")
    val input: SubscriptionInput,
    @SerializedName("queries")
    val queries: List<SubscriptionQueries>,
    @SerializedName("recordsFiltered")
    val recordsFiltered: Int,
    @SerializedName("recordsTotal")
    val recordsTotal: Int
)
{
    override fun toString(): String {
        return "OriginalXXXX(`data`=$`data`, draw=$draw, input=$input, queries=$queries, recordsFiltered=$recordsFiltered, recordsTotal=$recordsTotal)"
    }
}
