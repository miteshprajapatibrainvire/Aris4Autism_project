package com.example.aris4autism_project.api

import com.example.aris4autism_project.model.*
import com.example.aris4autism_project.model.authmodel.RequestLogin
import com.example.aris4autism_project.model.authmodel.RequestRegistration
import com.example.aris4autism_project.model.authmodel.ResponseRegistration
import com.example.aris4autism_project.model.countrymodel.CountryResponseModel
import com.example.aris4autism_project.model.diagnosismodel.DiagnosisDetailResponseModel
import com.example.aris4autism_project.model.editlearnermodel.SingleUserEditLearnerModel
import com.example.aris4autism_project.model.editsubuserdetailmodel.EditSubUserDetailsModel
import com.example.aris4autism_project.model.emailmodel.ResponseEmailCheck
import com.example.aris4autism_project.model.howitworkmodel.YoutubeVideoResponseModel
import com.example.aris4autism_project.model.learnermodel.AddNewLearnerResponse
import com.example.aris4autism_project.model.learnermodel.CreateNewLearnerModel
import com.example.aris4autism_project.model.learnermodel.LearnerReponseModel
import com.example.aris4autism_project.model.login.LoginModel
import com.example.aris4autism_project.model.overviewmodel.OverViewResponseModel
import com.example.aris4autism_project.model.profilemodel.UserProfileResponseModel
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.profilemodel.UpdateProfileResponse
import com.example.aris4autism_project.model.profilemodel.UpdateProfileSendData
import com.example.aris4autism_project.model.statemodel.StateResponseModel
import com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse.SubScriptionResponseModel
import com.example.aris4autism_project.model.subscriptionplanmodel.SubscriptionPlanListModelResponse
import com.example.aris4autism_project.model.subuserinnermodel.SubUserModelInnerResponse
import com.example.aris4autism_project.model.subusermodel.SubUserResponseModel
import com.example.aris4autism_project.model.userprofilemodel.ProfileIconResponseModel
import com.example.food_nutrition_recipe_app.model.clonemodel.OverViewInnerDetailResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @POST("v1/signin")
    suspend fun requestLogin(@Body body: RequestLogin): Response<ResponseData<LoginModel>>

    @POST("v1/signup")
    fun requestRegistration(@Body body: RequestRegistration): Response<ResponseData<ResponseRegistration>>

    @GET("v1/overview/details/{id}")
    suspend fun getOverViewInnerDetails(
        @Path("id") id: String,
    ): Response<ResponseData<OverViewInnerDetailResponse>>

    @GET("v1/get-countries")
    suspend fun getCountries(): Response<ResponseData<List<CountryResponseModel>>>

    @POST("v1/get-states?country_id=102")
    suspend fun getStates(): Response<ResponseData<List<StateResponseModel>>>

    @POST("v1/check_email?email={email}")
    fun getCheckEmail(@Path("email") email: String): Call<ResponseEmailCheck>

    @POST("v1/overview/list")
    suspend fun getOverViewList(
    ): Response<ResponseData<OverViewResponseModel>>

    @GET("v1/learner/list/")
    suspend fun getLearnerList(
    ): Response<ResponseData<LearnerReponseModel>>

    @POST("v1/sub-user-management/list")
    suspend fun getSubUserList(
    ): Response<ResponseData<SubUserResponseModel>>


    @POST("v1/subscription/user-subscriptions")
    suspend fun getSubscriptionDetail(
    ): Response<ResponseData<SubScriptionResponseModel>>


    @GET("v1/sub-user-management/get-details/{id}")
    suspend fun getSubUserInnerDetails(
        @Path("id") id: String
    ): Response<ResponseData<SubUserModelInnerResponse>>

    @GET("v1/account/profile/get-user-data")
    suspend fun getUserProfileDetails(): Response<ResponseData<UserProfileResponseModel>>

    @GET("v1/sub-user-management/get-details/{id}")
    suspend fun getSubUserDetailEdit(@Path("id") id: String): Response<ResponseData<EditSubUserDetailsModel>>

    @GET("v1/profile-icon-list?user_type=caretaker")
    suspend fun getProfileIcons(): Response<ResponseData<ProfileIconResponseModel>>

    @GET("v1/learner/show/{id}")
    suspend fun getEditLearnerDetail(@Path("id") id: String): Response<ResponseData<SingleUserEditLearnerModel>>

    // @HTTP(method = "GET", path = "v1/account/profile/update-profile", hasBody = true)
    @PUT("v1/account/profile/update-profile")
    suspend fun updateProfileDetails(
        @Body body: UpdateProfileSendData,
    ): Response<ResponseData<UpdateProfileResponse>>

    @GET("v1/subscription/list")
   suspend fun getSubscriptionList(): Response<ResponseData<SubscriptionPlanListModelResponse>>

    @POST("v1/all-videos-list-by-page")
    suspend fun getYoutubeVideoDetails(): Response<ResponseData<YoutubeVideoResponseModel>>

    @GET("v1/learner/fetch-diagnosis")
    suspend fun getDiagnosisDetails(): Response<ResponseData<List<DiagnosisDetailResponseModel>>>

    @POST("v1/learner/add")
    suspend fun postLearnerData(
        @Body Body: CreateNewLearnerModel,
    ): Response<ResponseData<AddNewLearnerResponse>>


}


