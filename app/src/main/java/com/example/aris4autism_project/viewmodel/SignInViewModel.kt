package com.example.aris4autism_project.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.R
import com.example.aris4autism_project.model.authmodel.RequestLogin
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.model.login.LoginModel
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.Authrepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel(val context: Context): ViewModel() {

    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    var authRepository=Authrepository(ApiClient.getApiInterface())

    var resultLogin: MutableLiveData<ResponseHandler<ResponseData<LoginModel>?>> = MutableLiveData()



    fun sendLoginResponse(email:String, pass:String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            resultLogin.postValue(ResponseHandler.Loading)
            resultLogin.postValue(authRepository.getLoginData(RequestLogin("ascscdscdscds1111111","Android",email,password)))
        }

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