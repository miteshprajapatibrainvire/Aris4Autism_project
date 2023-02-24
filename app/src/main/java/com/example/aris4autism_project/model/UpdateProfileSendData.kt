package com.example.aris4autism_project.model

import com.google.gson.annotations.SerializedName

data class UpdateProfileSendData(
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("icon_id")
    val icon_id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone_number")
    val phone_number: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("date_of_birth")
    val date_of_birth: String,
    @SerializedName("age")
    val age: String
) {
    override fun toString(): String {
        return "UpdateProfileBody(uuid='$uuid', icon_id='$icon_id', name='$name', phone_number='$phone_number', gender='$gender', date_of_birth='$date_of_birth', age='$age')"
    }
}