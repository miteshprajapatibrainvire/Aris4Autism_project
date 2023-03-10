package com.example.aris4autism_project.bind

import android.content.res.ColorStateList
import android.graphics.Color
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.youtube.player.internal.v


/**
 * Bind data used for data binding
 */
class BindAdapters {

    companion object {

        @JvmStatic
        @BindingAdapter("app:addTextChangeListener")
        fun addTextChangeListener(view: TextInputEditText, viewModel: ViewModel) {

            view.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?)
                {
                    var textInputLayout:TextInputLayout = view.parent.parent as TextInputLayout
                    textInputLayout.isErrorEnabled = false
                    textInputLayout.boxStrokeColor = Color.parseColor("#1E4884")
                }

            })
        }

//        @JvmStatic
//        @BindingAdapter("app:addTextChangeListener")
//        fun addTextChangeListener(view: TextInputEditText,viewlayout: TextInputLayout,viewModel: ViewModel) {
//
//            view.addTextChangedListener(object : TextWatcher {
//                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                }
//
//                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                }
//
//                override fun afterTextChanged(s: Editable?) {
//
//                    viewlayout.isErrorEnabled = false
//                    viewlayout.boxStrokeErrorColor =
//                        ColorStateList.valueOf(ContextCompat.getColor(viewlayout.context, R.color.gray))
//                    viewlayout.boxStrokeColor = 1
//                    viewlayout.boxStrokeWidthFocused = 1
////                    when (viewModel) {
////                        is ProfileDetailViewModel -> ProfileDetailViewModel(view.context).invisibleErrorTexts(view)
////                    }
//                }
//            })
//        }



        @JvmStatic
        @BindingAdapter("hintWithAsterisk")
        fun setHintWithAsterisk(view: TextInputLayout, hint: String?) {
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
