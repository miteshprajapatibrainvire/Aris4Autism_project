package com.example.aris4autism_project.viewmodel

import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.R

class SignInViewModel: ViewModel() {

    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$"

    var email:String=""
    var password:String=""
    val contentString = ObservableInt()
     var SignInResultValidation= MutableLiveData<String>()
    fun getLogInResult(): LiveData<String> = SignInResultValidation

    fun performSignInValidation()
    {
        when{
            password.isEmpty() && email.isEmpty()->{
                SignInResultValidation.value= "email and password empty."
            }
            email.isBlank()->{
                SignInResultValidation.value="Enter your email"
            }
            !email.trim().matches(emailPattern.toRegex())-> {
                SignInResultValidation.value="Invalid email address!"
            }
            password.isBlank()->{
                SignInResultValidation.value="Please enter your Password."
            }
//            password.trim().matches(passwordPattern.toRegex())->{
//                SignInResultValidation.value="Password should be minimum of 6 characters and must contain at least one number,one special character, and both uppercase and lowercase letters"
//            }
            password.length<6 || !password.matches(".*[A-Z].*".toRegex()) || !password.matches(".*[@#\$%^&+=].*".toRegex())->{
                SignInResultValidation.value="Password should be minimum of 6 characters and must contain at least one number,one special character, and both uppercase and lowercase letters"
            }

            password.length>10 ->{
                SignInResultValidation.value="Password Only 10 digit allow!"
            }
            password.isNotEmpty() && email.isNotEmpty()->{
                SignInResultValidation.value="valid credention!"
            }
        }
    }
}