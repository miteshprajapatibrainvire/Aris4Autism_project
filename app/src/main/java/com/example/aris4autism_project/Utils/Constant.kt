package com.example.aris4autism_project.Utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.airbnb.lottie.utils.Utils
import com.example.aris4autism_project.R
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Constant {
    companion object{
        const val BASE_URL="https://aris4autism.demo.brainvire.dev/api/v1"
        const val TokenData="token"

        fun getDialogCustom(context: Context): Dialog {
            val dialog = Dialog(context)
            val inflate = LayoutInflater.from(context).inflate(R.layout.layout_progress, null)
            dialog.setContentView(inflate)
            dialog.setCancelable(false)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            return dialog
        }
    }


//


}