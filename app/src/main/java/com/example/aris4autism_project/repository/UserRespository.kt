package com.example.aris4autism_project.repository

import com.example.aris4autism_project.api.ApiInterface
import com.example.aris4autism_project.model.countrymodel.CountryResponseModel
import com.example.aris4autism_project.model.diagnosismodel.DiagnosisDetailResponseModel
import com.example.aris4autism_project.model.editlearnermodel.SingleUserEditLearnerModel
import com.example.aris4autism_project.model.editsubuserdetailmodel.EditSubUserDetailsModel
import com.example.aris4autism_project.model.emailmodel.ResponseEmailCheck
import com.example.aris4autism_project.model.howitworkmodel.YoutubeVideoResponseModel
import com.example.aris4autism_project.model.learnermodel.AddNewLearnerResponse
import com.example.aris4autism_project.model.learnermodel.CreateNewLearnerModel
import com.example.aris4autism_project.model.learnermodel.LearnerReponseModel
import com.example.aris4autism_project.model.overviewmodel.OverViewResponseModel
import com.example.aris4autism_project.model.profilemodel.UserProfileResponseModel
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.model.profilemodel.UpdateProfileResponse
import com.example.aris4autism_project.model.profilemodel.UpdateProfileSendData
import com.example.aris4autism_project.model.statemodel.StateResponseModel
import com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse.SubScriptionResponseModel
import com.example.aris4autism_project.model.subscriptionplanmodel.SubscriptionPlanListModelResponse
import com.example.aris4autism_project.model.subuserinnermodel.SubUserModelInnerResponse
import com.example.aris4autism_project.model.subusermodel.SubUserResponseModel
import com.example.aris4autism_project.model.userprofilemodel.ProfileIconResponseModel
import com.example.aris4autism_project.network.ApiClient
import com.example.food_nutrition_recipe_app.model.clonemodel.OverViewInnerDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class UserRespository(val apiInterface: ApiInterface) : BaseRepository() {


    //apply new project structure test
    suspend fun getLearnerList(

    ): ResponseHandler<ResponseData<LearnerReponseModel>?> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getLearnerList(/*auth, platform, version*/)
                })
        }
    }

    suspend fun getOverViewDetail(
    ): ResponseHandler<ResponseData<OverViewResponseModel>?> {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall {
                apiInterface.getOverViewList()
            }
        }
    }

    public suspend fun getSubUserDetails(

    ): ResponseHandler<ResponseData<SubUserResponseModel>?> {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall {
                apiInterface.getSubUserList()
            }
        }
    }

    suspend fun getOverViewInnerDetail(
        id: String
    ): ResponseHandler<ResponseData<OverViewInnerDetailResponse>?> {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall(
                call ={
                apiInterface.getOverViewInnerDetails(id)
            })
        }
    }

    suspend fun getCountryRepository(): ResponseHandler<ResponseData<List<CountryResponseModel>>?> {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall({
                apiInterface.getCountries()
            })
        }
    }

    suspend fun getStatusRepository(): ResponseHandler<ResponseData<List<StateResponseModel>>?> {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getStates()
                })
        }
    }

    fun checkEmailAlreadyRegister(email: String): Call<ResponseEmailCheck> {
        return ApiClient.getApiInterface().getCheckEmail(email)
    }

    suspend fun getSubscriptionDetails(): ResponseHandler<ResponseData<SubScriptionResponseModel>?> {
        return withContext(Dispatchers.IO)
        {
            return@withContext makeAPICall({
                apiInterface.getSubscriptionDetail()
            })
        }
    }


    suspend fun getSubUserInnerDetail(id: String): ResponseHandler<ResponseData<SubUserModelInnerResponse>?> {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getSubUserInnerDetails(id)
                })
        }
    }


    suspend fun getUserCurrentUserDeail(): ResponseHandler<ResponseData<UserProfileResponseModel>?> {
        return withContext(Dispatchers.IO)
        {
            return@withContext makeAPICall {
                apiInterface.getUserProfileDetails()
            }
        }
    }


    suspend fun getSubUserEditDetails(id: String): ResponseHandler<ResponseData<EditSubUserDetailsModel>?> {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getSubUserDetailEdit(id)
                })
        }
    }

    suspend fun getProfileIconDetails(): ResponseHandler<ResponseData<ProfileIconResponseModel>?> {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getProfileIcons()
                })
        }
    }

    suspend fun getEditLearnerDetails(id: String): ResponseHandler<ResponseData<SingleUserEditLearnerModel>?> {
        return withContext(Dispatchers.IO)
        {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getEditLearnerDetail(id)
                })
        }
    }

    suspend fun updateProfileData(upProfile: UpdateProfileSendData): ResponseHandler<ResponseData<UpdateProfileResponse>?> {
        return withContext(Dispatchers.IO)
        {
            return@withContext makeAPICall(
                call = {
                    apiInterface.updateProfileDetails(upProfile)
                })
        }
    }

    suspend fun getSubscriptionDetail(): ResponseHandler<ResponseData<SubscriptionPlanListModelResponse>?> {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getSubscriptionList()
                })
        }
    }


    suspend fun getYoutubeVideos(): ResponseHandler<ResponseData<YoutubeVideoResponseModel>?> {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall({
                apiInterface.getYoutubeVideoDetails()
            })
        }
    }

    suspend fun getDiagnosisUserDetail(): ResponseHandler<ResponseData<List<DiagnosisDetailResponseModel>>?> {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getDiagnosisDetails()
                })
        }
    }

    suspend fun addNewLearnerDetails(body: CreateNewLearnerModel): ResponseHandler<ResponseData<AddNewLearnerResponse>?> {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall(
                call = {
                    apiInterface.postLearnerData(body)
                })
        }
    }
}