package com.example.aris4autism_project.Utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.example.aris4autism_project.R
import com.example.aris4autism_project.model.LearnerXXXXXX
import com.google.android.material.button.MaterialButton


class Constant {
    companion object{

        var editUserId:String=""

        const val BASE_URL="https://aris4autism.demo.brainvire.dev/api/"
        const val TokenData="token"

        fun getDialogCustom(context: Context): Dialog {
            val dialog = Dialog(context)
            val inflate = LayoutInflater.from(context).inflate(R.layout.layout_progress, null)
            dialog.setContentView(inflate)
            dialog.setCancelable(false)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            return dialog
        }

        fun customDiagnosis(context: Context)
        {
            var dianosisDialog=Dialog(context)
            dianosisDialog.setContentView(LayoutInflater.from(context).inflate(R.layout.custom_dialogbox_diagnosis,null))
            var btnOk:MaterialButton=dianosisDialog.findViewById(R.id.idbtnBlue)
            btnOk.setOnClickListener {
                dianosisDialog.cancel()
            }
            dianosisDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dianosisDialog.show()
        }

    }


//


}