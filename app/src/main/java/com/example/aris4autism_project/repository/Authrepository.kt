package com.example.aris4autism_project.repository

import android.util.Log
import com.example.aris4autism_project.api.ApiInterface
import com.example.aris4autism_project.model.RequestLogin
import com.example.aris4autism_project.model.ResponseLogin
import retrofit2.Call

class Authrepository {

    fun getLoginData(login: RequestLogin): Call<ResponseLogin>
    {
        Log.e("AuthRepository=",login.toString())
        return ApiInterface.getLoginData().requestLogin(login)
    }

}