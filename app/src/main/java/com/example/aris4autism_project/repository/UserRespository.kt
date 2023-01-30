package com.example.aris4autism_project.repository

import com.example.aris4autism_project.api.ApiCall
import com.example.aris4autism_project.api.ApiInterface
import com.example.aris4autism_project.model.ResponseCountryModel
import com.example.aris4autism_project.model.ResponseStateModel
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
}