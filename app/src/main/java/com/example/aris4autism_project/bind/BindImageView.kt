package com.example.aris4autism_project.bind

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class BindImageView {

    companion object {

//        @JvmStatic
//        @BindingAdapter("imageUrlDATA")
//        fun loadImage(view: AppCompatImageView, url: String) { // This methods should not have any return type, = declaration would make it return that object declaration.
//            Glide.with(view.context).load(url).into(view)
//        }

            @JvmStatic
            @BindingAdapter("imageUrl")
            open fun loadImage(view: AppCompatImageView, url: String?) {
                Glide.with(view.context)
                    .load(url)
                    .into(view)
            }
    }
}