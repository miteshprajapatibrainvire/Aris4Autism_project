package com.example.aris4autism_project.viewmodel

import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.model.RequestLogin
import com.example.aris4autism_project.model.ResponseLogin
import com.example.aris4autism_project.repository.Authrepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel: ViewModel() {

    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$"

    var userRepository=Authrepository()

    val resultLogin: MutableLiveData<BaseResponse<ResponseLogin>> = MutableLiveData()

    fun sendLoginResponse(email:String, pass:String)
    {

        var resultData=userRepository.getLoginData(RequestLogin("ascscdscdscds1111111","Android",email=email,password=pass))
//      var resultData=userRepository.getLoginData(RequestLogin("ascscdscdscds1111111","Android",email="faizan9dec@mailinator.com",password="Test@123"))
        resultLogin.value=BaseResponse.Loading()
        resultData.enqueue(object : Callback<ResponseLogin>{

            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if(response.isSuccessful)
                {
                    if(response.code()==200)
                    {
                        resultLogin.value=BaseResponse.Success(response.body())
                    }
                }
                else
                {
                    resultLogin.value=BaseResponse.Error(response.body().toString())
                }
            }
            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                resultLogin.value=BaseResponse.Error(t.toString())
            }

        })

    }

    var email:String=""
    var password:String=""
    val contentString = ObservableInt()
     var SignInResultValidation= MutableLiveData<String>()
    fun getLogInResult(): LiveData<String> = SignInResultValidation

    fun performSignInValidation()
    {
        when{
            password.isEmpty() && email.isEmpty()->{
                SignInResultValidation.value ="email and password empty."
            }
            email.isBlank()->{
                SignInResultValidation.value="Enter your email"
            }
            !email.trim().matches(emailPattern.toRegex())-> {
                SignInResultValidation.value="Invalid email address!"
            }
            password.isBlank()->{
                SignInResultValidation.value="Please enter your Password"
            }
//            password.trim().matches(passwordPattern.toRegex())->{
//                SignInResultValidation.value="Password should be minimum of 6 characters and must contain at least one number,one special character, and both uppercase and lowercase letters"
//            }
//            password.length<6 || !password.matches(".*[A-Z].*".toRegex()) || !password.matches(".*[@#\$%^&+=].*".toRegex())->{
//                SignInResultValidation.value="Password should be minimum of 6 characters and must contain at least one number,one special character, and both uppercase and lowercase letters"
//            }
            password.length>10 ->{
                SignInResultValidation.value="Password Only 10 digit allow!"
            }
            password.isNotEmpty() && email.isNotEmpty()->{
                SignInResultValidation.value="valid credention!"
            }
        }
    }
}