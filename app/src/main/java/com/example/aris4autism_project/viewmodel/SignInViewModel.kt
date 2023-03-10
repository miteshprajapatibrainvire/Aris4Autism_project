package com.example.aris4autism_project.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.R
import com.example.aris4autism_project.api.ApiInterface
import com.example.aris4autism_project.model.RequestLogin
import com.example.aris4autism_project.model.ResponseData
import com.example.aris4autism_project.model.ResponseHandler
import com.example.aris4autism_project.model.login.ResponseLogin
import com.example.aris4autism_project.repository.Authrepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel(val context: Context): ViewModel() {

    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    var authRepository=Authrepository(ApiInterface.getInterfaceData())

    var resultLogin: MutableLiveData<ResponseHandler<ResponseData<ResponseLogin>?>> = MutableLiveData()



    fun sendLoginResponse(email:String, pass:String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            resultLogin.postValue(ResponseHandler.Loading)
            resultLogin.postValue(authRepository.getLoginData(RequestLogin("ascscdscdscds1111111","Android",email,password)))
        }


       // val resultData=authRepository.getLoginData(RequestLogin("ascscdscdscds1111111","Android",email=email,password=pass))
//      var resultData=userRepository.getLoginData(RequestLogin("ascscdscdscds1111111","Android",email="faizan9dec@mailinator.com",password="Test@123"))
    //    resultLogin.value=BaseResponse.Loading()
//        resultData.enqueue(object : Callback<ResponseLogin>{
//
//            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
//
//                if(response.isSuccessful)
//                {
//                    if(response.code()==200)
//                    {
//                        Log.e("responseLogin=",response.body().toString())
//                        resultLogin.value=BaseResponse.Success(response.body())
//                    }
//                }
//                else
//                {
//                    resultLogin.value=BaseResponse.Error(response.body().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
//                resultLogin.value=BaseResponse.Error(t.toString())
//            }
//
//        })

    }

    var email:String=""
    var password:String=""
     var SignInResultValidation= MutableLiveData<String>()
    fun getLogInResult(): LiveData<String> = SignInResultValidation

    fun performSignInValidation()
    {
        when{
            password.isEmpty() && email.isEmpty()->{
                SignInResultValidation.value =context.getString(R.string.emailpassempty)
            }

            email.isBlank()->{
                SignInResultValidation.value=context.getString(R.string.emaildata)
            }

            !email.trim().matches(emailPattern.toRegex())-> {
                SignInResultValidation.value=context.getString(R.string.invalidEmailData)
            }

            password.isBlank()->{
                SignInResultValidation.value=context.getString(R.string.passwordStr)
            }

            password.length<6 || !password.matches(".*[A-Z].*".toRegex()) || !password.matches(".*[@#\$%^&+=].*".toRegex())->{
                   SignInResultValidation.value=context.getString(R.string.passwordValidation)
            }

            password.isNotEmpty() && email.isNotEmpty()->{
                SignInResultValidation.value=context.getString(R.string.validlogin)
            }

        }
    }
}