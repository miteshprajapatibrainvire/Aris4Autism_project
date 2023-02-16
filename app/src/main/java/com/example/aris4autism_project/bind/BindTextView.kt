package com.example.aris4autism_project.bind

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.aris4autism_project.R
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView

class BindTextView {


    companion object {

        @JvmStatic
        @BindingAdapter("hintWithAsteriskTxt")
        fun setHintWithAsterisk(view: MaterialTextView, hint: String?) {
            val textSpannable = SpannableString("$hint*")
            textSpannable.setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(view.context!!, R.color.gray)
                ), 0, textSpannable.length - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            textSpannable.setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(view.context!!, R.color.colorText)
                ),
                textSpannable.length - 1,
                textSpannable.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            view.hint = textSpannable
        }
    }
}