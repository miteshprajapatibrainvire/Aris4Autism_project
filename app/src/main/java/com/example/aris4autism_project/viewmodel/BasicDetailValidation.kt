package com.example.aris4autism_project.viewmodel

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.R

class BasicDetailValidation(val context: Context):ViewModel() {


    var fullName:String=""
    var gender:String=""
    var dateofbirth:String=""
    var subscription:String=""

    var resultBasicDetailValidation=MutableLiveData<String>()
    fun getBasicDetailResult() : MutableLiveData<String> = resultBasicDetailValidation

    fun getValidateBasicDetalResuit()
    {
        when{

//            fullName.isEmpty() || gender.isEmpty() || dateofbirth.isEmpty() || subscription.isEmpty()-> {
//                resultBasicDetailValidation.value=context.getString(R.string.fillalldetails)
//            }

            fullName.isEmpty() && gender.isEmpty() && dateofbirth.isEmpty() && subscription.isEmpty()-> {
                resultBasicDetailValidation.value=context.getString(R.string.fillalldetails)
            }

            fullName.isNotEmpty() && gender.isNotEmpty() && dateofbirth.isNotEmpty() && subscription.isNotEmpty()-> {
                resultBasicDetailValidation.value=context.getString(R.string.validcredential)
            }

            fullName.isEmpty()->{
                resultBasicDetailValidation.value=context.getString(R.string.basicDetailName)
            }

            gender.isEmpty()->{
                resultBasicDetailValidation.value=context.getString(R.string.basicDetailGen)
            }

            dateofbirth.isEmpty()->{
                resultBasicDetailValidation.value=context.getString(R.string.basicdateofbirth)
            }

            subscription.isEmpty()->{
                resultBasicDetailValidation.value=context.getString(R.string.basicselectsub)
            }
        }
    }


}