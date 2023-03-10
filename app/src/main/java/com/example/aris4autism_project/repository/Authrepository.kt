package com.example.aris4autism_project.repository

import android.util.Log
import com.example.aris4autism_project.api.ApiInterface
import com.example.aris4autism_project.model.*
import com.example.aris4autism_project.model.login.ResponseLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Authrepository(val apiInterface: ApiInterface):BaseRepository() {

    suspend fun getLoginData(login: RequestLogin): ResponseHandler<ResponseData<ResponseLogin>?> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.requestLogin(login)
                })
        }
    }

//    fun getLoginData(login: RequestLogin): Call<ResponseLogin>
//    {
//        Log.e("AuthRepository=",login.toString())
//        return ApiInterface.getInterfaceData().requestLogin(login)
//    }

    suspend  fun setRegistrationData(register:RequestRegistration):ResponseHandler<ResponseData<ResponseRegistration>?>
    {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall(
                call = {
                    Log.e("registerDataRepository=",register.toString())
                    apiInterface.requestRegistration(register)
                })
            }
    }


//    fun setRegistrationData(register: RequestRegistration): Call<ResponseRegistration> {
//        return ApiInterface.getInterfaceData().requestRegistration(register)
//    }
}


