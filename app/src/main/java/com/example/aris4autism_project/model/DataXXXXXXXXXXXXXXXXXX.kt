package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class DataXXXXXXXXXXXXXXXXXX(
    @SerializedName("address_line_1")
    val addressLine1: String,
    @SerializedName("address_line_2")
    val addressLine2: String,
    @SerializedName("country_id")
    val countryId: Int,
    @SerializedName("date_of_birth")
    val dateOfBirth: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("get_profile_icon")
    val getProfileIcon: GetProfileIconXXXX,
    @SerializedName("icon_id")
    val iconId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("learners_count")
    val learnersCount: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("state_id")
    val stateId: Int,
    @SerializedName("street")
    val street: String,
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("zip_code")
    val zipCode: String
)
{
    override fun toString(): String {
        return "DataXXXXXXXXXXXXXXXXXX(addressLine1='$addressLine1', addressLine2='$addressLine2', countryId=$countryId, dateOfBirth='$dateOfBirth', email='$email', gender='$gender', getProfileIcon=$getProfileIcon, iconId=$iconId, id=$id, learnersCount=$learnersCount, name='$name', phoneNumber='$phoneNumber', stateId=$stateId, street='$street', uuid='$uuid', zipCode='$zipCode')"
    }
}