package com.example.aris4autism_project.model


import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.gson.annotations.SerializedName


data class GetLearnerIcon(
    @SerializedName("full_path")
    val fullPath: String,
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String
)
{

    @BindingAdapter("profileImage")
    fun loadImage(view: AppCompatImageView, url: String)
    {
        Glide.with(view.context).load(url).into(view)
    }

}