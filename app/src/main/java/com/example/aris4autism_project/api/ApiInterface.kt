package com.example.aris4autism_project.api

import com.example.aris4autism_project.model.*
import retrofit2.Call
import retrofit2.http.*

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

    @POST("v1/sub-user-management/list")
    fun getSubUserList(@Header("Authorization") barearToken:String,@Header("Platform") plat:String,@Header("Version") version:String):Call<SubUserResponse>

    @GET("v1/learner/list")
    fun getLearnerList(@Header("Authorization") barearToken:String,@Header("Platform") plat:String,@Header("Version") version:String):Call<LearnerResponse>
//    fun getDailyMealPlan(@Header("X-RapidAPI-Key") key:String, @Header("X-RapidAPI-Host") host:String):Call<DailyPlanModel>

    @POST("v1/overview/list")
    fun getOverViewList(@Header("Authorization") barearToken:String,@Header("Platform") plat:String,@Header("Version") version:String):Call<OverViewResponse>

    @POST("v1/subscription/user-subscriptions")
    fun getSubscriptionDetail(@Header("Authorization") barearToken:String,@Header("Platform") plat:String,@Header("Version") version:String):Call<SubScriptionResponse>

    @GET("v1/sub-user-management/get-details/{id}")
    fun getSubUserInnerDetails(@Path("id") id:String,@Header("Authorization") barearToken:String,@Header("Platform") plat:String,@Header("Version") version:String):Call<SubUserModelInnerResponse>

    @GET("v1/overview/details/{id}")
    fun getOverViewInnerDetails(@Path("id") id:String,@Header("Authorization") barearToken:String,@Header("Platform") plat:String,@Header("Version") version:String):Call<OverViewInnerDetailResponse>

    @GET("v1/account/profile/get-user-data")
    fun getUserProfileDetails(@Header("Authorization") barearToken:String,@Header("Platform") plat:String,@Header("Version") version:String):Call<UserProfileResponse>

    @GET("v1/sub-user-management/get-details/{id}")
    fun getSubUserDetailEdit(@Path("id") id:String,@Header("Authorization") barearToken:String,@Header("Platform") plat:String,@Header("Version") version:String):Call<SubUserDetailsResponse>

    @GET("v1/profile-icon-list?user_type=caretaker")
    fun getProfileIcons(@Header("Authorization") barearToken:String,@Header("Platform") plat:String,@Header("Version") version:String):Call<ProfileIconResponse>


    companion object{
        fun getInterfaceData():ApiInterface
        {
            return ApiCall.client!!.create(ApiInterface::class.java)
        }
    }

}


