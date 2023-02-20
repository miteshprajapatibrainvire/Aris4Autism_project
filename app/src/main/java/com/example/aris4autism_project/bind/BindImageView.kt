package com.example.aris4autism_project.bind

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
class BindImageView {

    companion object {

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: AppCompatImageView, url: String?) {
            Glide.with(view.context)
                .load(url)
                .into(view)
        }

    }
}