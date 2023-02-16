package com.example.aris4autism_project.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.R

class ProfileDetailViewModel(val context:Context): ViewModel()
{
    var fullname:String=""
    var mobileNo:String=""
    var emailId:String=""
    var gen:String=""
    var dob:String=""
    var resultProfile= MutableLiveData<String>()
    fun getProfileResult() : LiveData<String> = resultProfile


    fun getProfileValidation()
    {
        when{
            fullname.isEmpty()->{
                resultProfile.value=context.getString(R.string.enterfullaname)
            }

            mobileNo.isEmpty()->{
                resultProfile.value=context.getString(R.string.entermobile)
            }
            fullname.isNotEmpty()&&mobileNo.isNotEmpty()->{
                resultProfile.value="valid Data"
            }
        }
    }
}