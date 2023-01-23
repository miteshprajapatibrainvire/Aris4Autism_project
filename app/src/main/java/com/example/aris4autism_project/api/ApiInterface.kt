package com.example.aris4autism_project.api

import com.example.aris4autism_project.model.RequestLogin
import com.example.aris4autism_project.model.ResponseLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("v1/signin")
    fun requestLogin(@Body body:RequestLogin): Call<ResponseLogin>

    companion object{
        fun getLoginData():ApiInterface
        {
            return ApiCall.client!!.create(ApiInterface::class.java)
        }
    }

}


