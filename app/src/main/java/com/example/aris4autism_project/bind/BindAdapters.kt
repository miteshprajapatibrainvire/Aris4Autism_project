package com.anandan.caretaker.bind


import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.aris4autism_project.R
import com.google.android.material.textfield.TextInputLayout


/**
 * Bind data used for data binding
 */
class BindAdapters {

    companion object {

        @JvmStatic
        @BindingAdapter("hintWithAsterisk")
        fun setHintWithAsterisk(view: TextInputLayout, hint: String?) {
            val textSpannable = SpannableString("$hint*")
            textSpannable.setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(view.context!!, R.color.black)
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
