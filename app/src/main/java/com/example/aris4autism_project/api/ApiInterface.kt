package com.example.aris4autism_project.api

import com.example.aris4autism_project.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @POST("v1/signin")
    fun requestLogin(@Body body:RequestLogin): Call<ResponseLogin>

    @POST("v1/signup")
    fun requestRegistration(@Body body: RequestRegistration): Call<ResponseRegistration>

    @GET("v1/get-countries")
    fun getCountries():Call<ResponseCountryModel>

    @POST("v1/get-states?country_id=102")
    fun getStates():Call<ResponseStateModel>

    @POST("v1/check_email?email={email}")
    fun getCheckEmail(@Path("email") email:String):Call<ResponseEmailCheck>


    companion object{

        fun getInterfaceData():ApiInterface
        {
            return ApiCall.client!!.create(ApiInterface::class.java)
        }

    }

}


