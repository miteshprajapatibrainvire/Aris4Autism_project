package com.example.aris4autism_project.api

import com.example.aris4autism_project.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @POST("v1/signin")
    fun requestLogin(@Body body:RequestLogin): Call<ResponseLogin>

    @POST("v1/signup")
    fun requestRegistration(@Body body: RequestRegistration): Call<ResponseRegistration>

    @GET("v1/get-countries")
    fun getCountries():Call<ResponseCountryModel>

    @POST("v1/get-states?country_id=102")
    fun getStates():Call<ResponseStateModel>

    companion object{
        fun getInterfaceData():ApiInterface
        {
            return ApiCall.client!!.create(ApiInterface::class.java)
        }
    }

}


