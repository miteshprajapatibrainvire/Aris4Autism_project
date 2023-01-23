package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.content.res.Resources
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.R
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
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$"

    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    fun performSignUpValidation()
    {
        when {
                fullname.isEmpty() && mobile.isEmpty() && email.isEmpty() && gender.isEmpty() && dob.isEmpty() && password.isEmpty() && confirmpassword.isEmpty() ->
                {
                    signUpResult.value="invalid credential"
                }
                fullname.isEmpty() ->
                {
                    signUpResult.value = "Enter fullname*"
                }
                mobile.isEmpty()->{
                    signUpResult.value="Enter mobile number"
                }
                mobile.length>10 || mobile.length < 10->{
                    signUpResult.value="Mobile number must be of 10 digits"
                }
                email.isEmpty()->{
                    signUpResult.value = "Enter Email address*"
                }
                !email.trim().matches(emailPattern.toRegex())-> {
                    signUpResult.value="Invalid email address*"
                }
                gender.equals("Select") -> {
                    signUpResult.value = "Select Gender*"
                }
                dob.isEmpty()-> {
                    signUpResult.value = "Select Date of birth*"
                }
                password.isEmpty() -> {
                    signUpResult.value = "Please enter your Password"
                }
                password.trim().matches(passwordPattern.toRegex())->{
                    signUpResult.value="Password should be minimum of 6 characters and must contain at least one number,one special character, and both uppercase and lowercase letters"
                }
                confirmpassword.trim().matches(passwordPattern.toRegex())->{
                    signUpResult.value="Confirm Password should be minimum of 6 characters and must contain at least one number,one special character, and both uppercase and lowercase letters"
                }
                confirmpassword.isEmpty() -> {
                    signUpResult.value = "Enter Confirm password*"
                    //|| mobile.isNotEmpty() || email.isNotEmpty() || dob.isNotEmpty() || password.isNotEmpty() || confirmpassword.isNotEmpty()
                }
//                fullname.isNotEmpty() ->{
//                    signUpResult.value="valid registration"
//                }
            }
        }



}