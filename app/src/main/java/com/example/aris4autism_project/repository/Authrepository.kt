package com.example.aris4autism_project.repository

import android.util.Log
import com.example.aris4autism_project.api.ApiInterface
import com.example.aris4autism_project.model.authmodel.RequestLogin
import com.example.aris4autism_project.model.authmodel.RequestRegistration
import com.example.aris4autism_project.model.authmodel.ResponseRegistration
import com.example.aris4autism_project.model.login.LoginModel
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Authrepository(val apiInterface: ApiInterface):BaseRepository() {

    suspend fun getLoginData(login: RequestLogin): ResponseHandler<ResponseData<LoginModel>?> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall {
                apiInterface.requestLogin(login)
            }
        }
    }

//    fun getLoginData(login: RequestLogin): Call<ResponseLogin>
//    {
//        Log.e("AuthRepository=",login.toString())
//        return ApiInterface.getInterfaceData().requestLogin(login)
//    }

    suspend  fun setRegistrationData(register: RequestRegistration): ResponseHandler<ResponseData<ResponseRegistration>?>
    {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall {
                Log.e("registerDataRepository=", register.toString())
                apiInterface.requestRegistration(register)
            }
        }
    }


//    fun setRegistrationData(register: RequestRegistration): Call<ResponseRegistration> {
//        return ApiInterface.getInterfaceData().requestRegistration(register)
//    }
}


