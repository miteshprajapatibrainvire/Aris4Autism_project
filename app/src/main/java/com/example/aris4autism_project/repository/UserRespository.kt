package com.example.aris4autism_project.repository

import com.example.aris4autism_project.api.ApiCall
import com.example.aris4autism_project.api.ApiInterface
import com.example.aris4autism_project.model.*
import retrofit2.Call

class UserRespository {

    fun getCountryRepository(): Call<ResponseCountryModel>
    {
       return ApiInterface.getInterfaceData().getCountries()
    }

    fun getStatusRepository():Call<ResponseStateModel>
    {
        return ApiInterface.getInterfaceData().getStates()
    }

    fun checkEmailAlreadyRegister(email:String):Call<ResponseEmailCheck>
    {
        return ApiInterface.getInterfaceData().getCheckEmail(email)
    }

    fun getSubUserDetails(auth:String,platform:String,ver:String):Call<SubUserResponse>
    {
        return ApiInterface.getInterfaceData().getSubUserList("Bearer "+auth,platform,ver)
    }

    fun getLearnerDetail(auth:String,platform:String,ver:String):Call<LearnerResponse>
    {
        return ApiInterface.getInterfaceData().getLearnerList("Bearer "+auth,platform,ver)
    }

}