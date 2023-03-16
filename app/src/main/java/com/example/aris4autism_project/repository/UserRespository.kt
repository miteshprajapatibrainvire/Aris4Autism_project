package com.example.aris4autism_project.repository

import com.example.aris4autism_project.api.ApiInterface
import com.example.aris4autism_project.model.*
import com.example.aris4autism_project.model.diagnosismodel.DiagnosisDetailResponseModel
import com.example.aris4autism_project.model.editlearnermodel.SingleUserEditLearnerModel
import com.example.aris4autism_project.model.editsubuserdetailmodel.EditSubUserDetailsModel
import com.example.aris4autism_project.model.howitworkmodel.YoutubeVideoResponse
import com.example.aris4autism_project.model.learnermodel.LearnerReponseModel
import com.example.aris4autism_project.model.overviewmodel.OverViewResponseModel
import com.example.aris4autism_project.model.profilemodel.UserProfileResponseModel
import com.example.aris4autism_project.model.responsemodel.ResponseData
import com.example.aris4autism_project.model.responsemodel.ResponseHandler
import com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse.SubScriptionResponseModel
import com.example.aris4autism_project.model.subuserinnermodel.SubUserModelInnerResponse
import com.example.aris4autism_project.model.subusermodel.SubUserResponseModel
import com.example.aris4autism_project.model.userprofilemodel.ProfileIconResponseModel
import com.example.aris4autism_project.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class UserRespository(val apiInterface: ApiInterface) : BaseRepository() {


    //apply new project structure test
     suspend fun getLearnerList(

    ): ResponseHandler<ResponseData<LearnerReponseModel>?> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall (
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

//    public suspend fun getOverViewInnerDetail(
//        id: String,
//        auth: String,
//        platform: String,
//        ver: String
//    ): ResponseHandler<ResponseData<OverViewInnerDetailResponse>?> {
//        return withContext(Dispatchers.Default)
//        {
//            return@withContext makeAPICall
//            {
//                apiInterface.getOverViewInnerDetails(id, "Bearer " + auth, platform, ver)
//            }
//        }
//    }
//
//    fun getOverViewInnerDetail(
//        id: String,
//        auth: String,
//        platform: String,
//        ver: String
//    ): Call<OverViewInnerDetailResponse> {
//        return ApiInterface.getInterfaceData()
//            .getOverViewInnerDetails(id, "Bearer " + auth, platform, ver)
//    }


   suspend fun getOverViewInnerDetail(
        id:String
    ):ResponseHandler<ResponseData<OverViewInnerDetailResponse>?>
    {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall({
                apiInterface.getOverViewInnerDetails(id)
            })
        }
    }

    //    fun getSubUserDetails(auth: String, platform: String, ver: String): Call<SubUserResponse> {
//        return ApiInterface.getInterfaceData().getSubUserList("Bearer " + auth, platform, ver)
//    }
    fun getCountryRepository(): Call<ResponseCountryModel> {
        return ApiClient.getApiInterface().getCountries()
    }

    fun getStatusRepository(): Call<ResponseStateModel> {
        return ApiClient.getApiInterface().getStates()
    }

    fun checkEmailAlreadyRegister(email: String): Call<ResponseEmailCheck> {
        return ApiClient.getApiInterface().getCheckEmail(email)
    }


    //later change for update
//    fun getLearnerDetail(auth: String, platform: String, ver: String): Call<LearnerResponse> {
//        return ApiInterface.getInterfaceData().getLearnerList("Bearer " + auth, platform, ver)
//    }


//    fun getOverViewDetail(auth: String, platform: String, ver: String): Call<OverViewResponse> {
//        Log.e("userRepository=", "$auth $platform $ver")
//        return ApiInterface.getInterfaceData().getOverViewList("Bearer " + auth, platform, ver)
//    }

   suspend fun getSubscriptionDetails(): ResponseHandler<ResponseData<SubScriptionResponseModel>?>
    {
            return withContext(Dispatchers.IO)
            {
                return@withContext makeAPICall ({
                    apiInterface.getSubscriptionDetail()
                })
            }
    }

//    fun getSubscriptionDetails(
//        auth: String,
//        platform: String,
//        ver: String
//    ): Call<SubScriptionResponse> {
//        return ApiInterface.getInterfaceData()
//            .getSubscriptionDetail("Bearer " + auth, platform, ver)
//    }

  suspend  fun getSubUserInnerDetail(id:String):ResponseHandler<ResponseData<SubUserModelInnerResponse>?>
    {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall(
                call = {
                apiInterface.getSubUserInnerDetails(id)
            })
        }
    }

//    fun getSubUserInnerDetail(
//        id: String,
//        auth: String,
//        platform: String,
//        ver: String
//    ): Call<SubUserModelInnerResponse> {
//        Log.e("getSubInnerDetail=", id + "=" + auth + "=" + platform + "=" + ver)
//        return ApiClient.getApiInterface()
//            .getSubUserInnerDetails(id, "Bearer " + auth, platform, ver)
//    }

//    fun getOverViewInnerDetail(
//        id: String,
//        auth: String,
//        platform: String,
//        ver: String
//    ): Call<OverViewInnerDetailResponse> {
//        return ApiInterface.getInterfaceData()
//            .getOverViewInnerDetails(id, "Bearer " + auth, platform, ver)
//    }

   suspend fun getUserCurrentUserDeail():ResponseHandler<ResponseData<UserProfileResponseModel>?>
    {
        return withContext(Dispatchers.IO)
        {
            return@withContext makeAPICall {
                apiInterface.getUserProfileDetails()
            }
        }
    }

//    fun getUserCurrentUserDeail(
//        auth: String,
//        platform: String,
//        ver: String
//    ): Call<UserProfileResponse> {
//        return ApiInterface.getInterfaceData()
//            .getUserProfileDetails("Bearer " + auth, platform, ver)
//    }


    suspend  fun getSubUserEditDetails(id:String):ResponseHandler<ResponseData<EditSubUserDetailsModel>?>
    {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall(
                call = {
                apiInterface.getSubUserDetailEdit(id)
            })
        }
    }
//    fun getSubUserEditDetails(
//        id: String,
//        auth: String,
//        platform: String,
//        ver: String
//    ): Call<SubUserDetailsResponse> {
//        return ApiClient.getApiInterface()


   suspend fun getProfileIconDetails():ResponseHandler<ResponseData<ProfileIconResponseModel>?>
    {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall(
                call = {
                apiInterface.getProfileIcons()
            })
        }
    }
//fun getProfileIconDetails(
//    auth: String,
//    platform: String,
//    ver: String
//): Call<ProfileIconResponse> {
//    return ApiClient.getApiInterface().getProfileIcons("Bearer " + auth, platform, ver)
//}
//            .getSubUserDetailEdit(id, "Bearer " + auth, platform, ver)
//    }


  suspend  fun getEditLearnerDetails(id:String): ResponseHandler<ResponseData<SingleUserEditLearnerModel>?>
    {
        return withContext(Dispatchers.IO)
        {
            return@withContext makeAPICall(
                call = {
                apiInterface.getEditLearnerDetail(id)
            })
        }
    }
//    fun getEditLearnerDetails(
//        id: String,
//        auth: String,
//        platform: String,
//        ver: String
//    ): Call<EditLearnerModelResponse> {
//        return ApiClient.getApiInterface()
//            .getEditLearnerDetail(id, "Bearer " + auth, platform, ver)
//    }

   suspend fun updateProfileData(upProfile: UpdateProfileSendData):ResponseHandler<ResponseData<UpdateProfileResponse>?>
    {
        return withContext(Dispatchers.IO)
        {
            return@withContext makeAPICall (
                call = {
                    apiInterface.updateProfileDetails(upProfile)
                })
        }
    }

//    fun updateProfileData(
//        upProfile: UpdateProfileSendData,
//        auth: String,
//        platform: String,
//        ver: String
//    ): Call<UpdateProfileResponse> {
//        return ApiClient.getApiInterface()
//            .updateProfileDetails(upProfile, "Bearer " + auth, platform, ver)
//    }

//    fun getSubscriptionDetail(
//        auth: String,
//        platform: String,
//        ver: String
//    ): Call<SubscriptionListResponse> {
//        return ApiInterface.getInterfaceData().getSubscriptionList("Bearer " + auth, platform, ver)
//    }

    suspend fun  getYoutubeVideos():ResponseHandler<ResponseData<YoutubeVideoResponse>?>
    {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall({
                apiInterface.getYoutubeVideoDetails()
            })
        }
    }

//    fun getYoutubeVideos(auth: String, platform: String, ver: String): Call<YoutubeVideoResponse> {
//        return ApiClient.getApiInterface()
//            .getYoutubeVideoDetails("Bearer " + auth, platform, ver)
//    }


   suspend fun getDiagnosisUserDetail():ResponseHandler<ResponseData<List<DiagnosisDetailResponseModel>>?>
    {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall(
                call = {
                 apiInterface.getDiagnosisDetails()
            })
        }
    }

//    fun getDiagnosisUserDetail(
//        auth: String,
//        platform: String,
//        ver: String
//    ): Call<DiagnosisDetailResponse> {
//        return ApiClient.getApiInterface().getDiagnosisDetails("Bearer " + auth, platform, ver)
//    }

    suspend fun addNewLearnerDetails(body:CreateNewLearnerModel):ResponseHandler<ResponseData<AddNewLearnerResponse>?>
    {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall(
                call = {
                apiInterface.postLearnerData(body)
            })
        }
    }

//    fun addNewLearnerDetails(
//        body: CreateNewLearnerModel,
//        auth: String,
//        platform: String,
//        ver: String
//    ): Call<AddNewLearnerResponse> {
//        return ApiClient.getApiInterface()
//            .postLearnerData(body, "Bearer " + auth, platform, ver)
//    }

}