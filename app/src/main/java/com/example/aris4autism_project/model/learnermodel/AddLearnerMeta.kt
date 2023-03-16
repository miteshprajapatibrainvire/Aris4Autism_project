package com.example.food_nutrition_recipe_app.model.clonemodel


import com.google.gson.annotations.SerializedName

data class AddLearnerMeta(
    @SerializedName("message")
    val message: String,
    @SerializedName("message_code")
    val messageCode: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("status_code")
    val statusCode: Int
)
{

    override fun toString(): String {
        return "MetaXXXXXXXXXXXXXXXXXXXXX(message='$message', messageCode='$messageCode', status=$status, statusCode=$statusCode)"
    }

}