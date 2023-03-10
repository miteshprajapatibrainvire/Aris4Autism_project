package com.example.aris4autism_project.repository

import android.util.Log
import com.example.aris4autism_project.api.ApiInterface
import com.example.aris4autism_project.model.*
import com.example.aris4autism_project.model.learnermodel.LearnerReponseModel
import com.example.aris4autism_project.model.overviewmodel.OverViewResponse
import com.example.aris4autism_project.model.subusermodel.SubUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class UserRespository(val apiInterface: ApiInterface) : BaseRepository() {


    //apply new project structure test
    public suspend fun getLearnerList(
        auth: String,
        platform: String,
        version: String
    ): ResponseHandler<ResponseData<LearnerReponseModel>?> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getLearnerList(auth, platform, version)
                })
        }
    }

    public suspend fun getSubUserDetails(
        auth: String,
        platform: String,
        ver: String
    ): ResponseHandler<ResponseData<SubUserResponse>?> {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getSubUserList("Bearer " + auth, platform, ver)
                })
        }
    }

    public suspend fun getOverViewInnerDetail(
        id: String,
        auth: String,
        platform: String,
        ver: String
    ): ResponseHandler<ResponseData<OverViewInnerDetailResponse>?> {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall({
                apiInterface.getOverViewInnerDetails(id, auth, platform, ver)
            })
        }
    }
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


//    fun getOverViewInnerDetail(
//        id:String,auth:String,platform:String,ver:String
//    ):ResponseHandler<ResponseData<OverViewInnerDetailResponse>?>
//    {
//        return withContext(Dispatchers.Default)
//        {
//            return@withContext makeAPICall({
//                apiInterface.getOverViewInnerDetails(id,auth,platform,ver)
//            })
//        }
//    }


    //    fun getSubUserDetails(auth: String, platform: String, ver: String): Call<SubUserResponse> {
//        return ApiInterface.getInterfaceData().getSubUserList("Bearer " + auth, platform, ver)
//    }
    fun getCountryRepository(): Call<ResponseCountryModel> {
        return ApiInterface.getInterfaceData().getCountries()
    }

    fun getStatusRepository(): Call<ResponseStateModel> {
        return ApiInterface.getInterfaceData().getStates()
    }

    fun checkEmailAlreadyRegister(email: String): Call<ResponseEmailCheck> {
        return ApiInterface.getInterfaceData().getCheckEmail(email)
    }


    //later change for update
//    fun getLearnerDetail(auth: String, platform: String, ver: String): Call<LearnerResponse> {
//        return ApiInterface.getInterfaceData().getLearnerList("Bearer " + auth, platform, ver)
//    }

    suspend fun getOverViewDetail(
        auth: String,
        platform: String,
        ver: String
    ): ResponseHandler<ResponseData<OverViewResponse>?> {
        return withContext(Dispatchers.Default)
        {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getOverViewList("Bearer " + auth, platform, ver)
                })
        }
    }
//    fun getOverViewDetail(auth: String, platform: String, ver: String): Call<OverViewResponse> {
//        Log.e("userRepository=", "$auth $platform $ver")
//        return ApiInterface.getInterfaceData().getOverViewList("Bearer " + auth, platform, ver)
//    }

    fun getSubscriptionDetails(
        auth: String,
        platform: String,
        ver: String
    ): Call<SubScriptionResponse> {
        return ApiInterface.getInterfaceData()
            .getSubscriptionDetail("Bearer " + auth, platform, ver)
    }

    fun getSubUserInnerDetail(
        id: String,
        auth: String,
        platform: String,
        ver: String
    ): Call<SubUserModelInnerResponse> {
        Log.e("getSubInnerDetail=", id + "=" + auth + "=" + platform + "=" + ver)
        return ApiInterface.getInterfaceData()
            .getSubUserInnerDetails(id, "Bearer " + auth, platform, ver)
    }


//    fun getOverViewInnerDetail(
//        id: String,
//        auth: String,
//        platform: String,
//        ver: String
//    ): Call<OverViewInnerDetailResponse> {
//        return ApiInterface.getInterfaceData()
//            .getOverViewInnerDetails(id, "Bearer " + auth, platform, ver)
//    }

    fun getUserCurrentUserDeail(
        auth: String,
        platform: String,
        ver: String
    ): Call<UserProfileResponse> {
        return ApiInterface.getInterfaceData()
            .getUserProfileDetails("Bearer " + auth, platform, ver)
    }

    fun getSubUserEditDetails(
        id: String,
        auth: String,
        platform: String,
        ver: String
    ): Call<SubUserDetailsResponse> {
        return ApiInterface.getInterfaceData()
            .getSubUserDetailEdit(id, "Bearer " + auth, platform, ver)
    }

    fun getProfileIconDetails(
        auth: String,
        platform: String,
        ver: String
    ): Call<ProfileIconResponse> {
        return ApiInterface.getInterfaceData().getProfileIcons("Bearer " + auth, platform, ver)
    }

    fun getEditLearnerDetails(
        id: String,
        auth: String,
        platform: String,
        ver: String
    ): Call<EditLearnerModelResponse> {
        return ApiInterface.getInterfaceData()
            .getEditLearnerDetail(id, "Bearer " + auth, platform, ver)
    }

    fun updateProfileData(
        upProfile: UpdateProfileSendData,
        auth: String,
        platform: String,
        ver: String
    ): Call<UpdateProfileResponse> {
        return ApiInterface.getInterfaceData()
            .updateProfileDetails(upProfile, "Bearer " + auth, platform, ver)
    }

    fun getSubscriptionDetail(
        auth: String,
        platform: String,
        ver: String
    ): Call<SubscriptionListResponse> {
        return ApiInterface.getInterfaceData().getSubscriptionList("Bearer " + auth, platform, ver)
    }

    fun getYoutubeVideos(auth: String, platform: String, ver: String): Call<YoutubeVideoResponse> {
        return ApiInterface.getInterfaceData()
            .getYoutubeVideoDetails("Bearer " + auth, platform, ver)
    }

    fun getDiagnosisUserDetail(
        auth: String,
        platform: String,
        ver: String
    ): Call<DiagnosisDetailResponse> {
        return ApiInterface.getInterfaceData().getDiagnosisDetails("Bearer " + auth, platform, ver)
    }

    fun addNewLearnerDetails(
        body: CreateNewLearnerModel,
        auth: String,
        platform: String,
        ver: String
    ): Call<AddNewLearnerResponse> {
        return ApiInterface.getInterfaceData()
            .postLearnerData(body, "Bearer " + auth, platform, ver)
    }

}