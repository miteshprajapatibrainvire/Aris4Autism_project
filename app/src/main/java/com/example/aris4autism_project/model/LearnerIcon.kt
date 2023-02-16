package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class LearnerIcon(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("created_by")
    val createdBy: Int,
    @SerializedName("deleted_at")
    val deletedAt: Any,
    @SerializedName("deleted_by")
    val deletedBy: Any,
    @SerializedName("full_path")
    val fullPath: String,
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("updated_by")
    val updatedBy: Any,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("uuid")
    val uuid: String
)
{
    fun getIconUrlData():String{
        if(iconUrl.isEmpty())
        {
            return "gone"
        }
        else {
            return "visible"
        }
    }
}