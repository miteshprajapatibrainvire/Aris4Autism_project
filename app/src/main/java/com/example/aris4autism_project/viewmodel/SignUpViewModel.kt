package com.example.aris4autism_project.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.sign

class SignUpViewModel  : ViewModel() {

    var fullname:String=""
    var password:String=""
    var email:String=""
    var mobile:String=""
    var dob:String=""
    var gender:String=""
    var confirmpassword:String=""
    private var signUpResult= MutableLiveData<String>()
    fun getSignUpResult(): LiveData<String> = signUpResult

    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    fun performSignUpValidation()
    {
        when {
                fullname.isEmpty() || mobile.isEmpty() || email.isEmpty() || gender.isEmpty() || dob.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()->{
                    signUpResult.value="invalid credential"
                }
                fullname.isBlank() -> {
                    signUpResult.value = "Enter fullname*"
                }
                mobile.isBlank()->{
                    signUpResult.value="Enter mobile number*"
                }
                mobile.length>10 || mobile.length < 10->{
                    signUpResult.value="Only 10 digit allow*"
                }
                email.isBlank()->{
                    signUpResult.value = "Enter Email address*"
                }
                !email.trim().matches(emailPattern.toRegex())-> {
                    signUpResult.value="Invalid email address*"
                }
//                gender.isEmpty() -> {
//                    signUpResult.value = "Select Gender*"
//                }
                dob.isBlank()-> {
                    signUpResult.value = "Select Date of birth*"
                }
                password.isBlank() -> {
                    signUpResult.value = "Enter Password*"
                }
                password.length<6 || !password.matches(".*[A-Z].*".toRegex()) || !password.matches(".*[@#\$%^&+=].*".toRegex())->{
                    signUpResult.value="Password should be minimum of 6 characters and must contain at least one number,one special character, and both uppercase and lowercase letters"
                }
                confirmpassword.isBlank() -> {
                    signUpResult.value = "Enter Confirm password*"
                }
                confirmpassword.length<10 || confirmpassword.length>10 ->{
                    signUpResult.value="Confirm Password allow only 10 digit allow*"
                }

            }
        }


}