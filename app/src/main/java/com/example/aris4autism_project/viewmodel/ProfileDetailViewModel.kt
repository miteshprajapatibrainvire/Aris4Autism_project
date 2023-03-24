package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.FragmentProfileDetailsBinding
import com.example.aris4autism_project.fragment.ProfileDetailsFragment
import com.example.aris4autism_project.model.userprofilemodel.ProfileIconResponseModel

import com.example.aris4autism_project.model.profilemodel.UserProfileResponseModel
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.model.profilemodel.UpdateProfileResponse
import com.example.aris4autism_project.model.profilemodel.UpdateProfileSendData
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.UserRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Body

class ProfileDetailViewModel( var context: Context): ViewModel()
{
    var fullname:String=""
    var mobileNo:String=""
//    var emailId:String=""
    var gen:String=""
    var dob:String=""
    var email:String=""
    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

   lateinit var binding:FragmentProfileDetailsBinding


    val userRepository=UserRespository(ApiClient.getApiInterface())
    lateinit var bindLayout:ProfileDetailsFragment

    var resultProfileUser = MutableLiveData<ResponseHandler<ResponseData<UserProfileResponseModel>?>>()
    var resultProfileIcon=MutableLiveData<ResponseHandler<ResponseData<ProfileIconResponseModel>?>>()
    var resultProfileUpdate=MutableLiveData<ResponseHandler<ResponseData<UpdateProfileResponse>?>>()
    var resultProfileValidation= MutableLiveData<String>()

    fun profileValidation()
    {
        when{
            fullname.isEmpty() && mobileNo.isEmpty()->{
                resultProfileValidation.value=context.getString(R.string.bothempty)
            }
           fullname.isEmpty()->{
               resultProfileValidation.value=context.getString(R.string.fllname)
           }
            mobileNo.isEmpty()->{
                resultProfileValidation.value=context.getString(R.string.entermobile)
            }
            mobileNo.length>10&& mobileNo.length<10 ->{
                resultProfileValidation.value=context.getString(R.string.mNo)
            }
            email.isEmpty()->{
                resultProfileValidation.value=context.getString(R.string.emailAddress)
            }
            fullname.isNotEmpty() && mobileNo.isNotEmpty()->{
                resultProfileValidation.value="data filled not empty"
            }
            !email.trim().matches(emailPattern.toRegex())-> {
                resultProfileValidation.value=context.getString(R.string.invalidEmailData)
            }
            gen.isEmpty()->{
                resultProfileValidation.value=context.getString(R.string.genderStr)
            }
            dob.isEmpty()->{
                resultProfileValidation.value=context.getString(R.string.selectDob)
            }
        }
    }
    fun profileUpdateDetail(@Body profileUpdate: UpdateProfileSendData)
    {
        viewModelScope.launch {

            resultProfileUpdate.postValue(ResponseHandler.Loading)
            Log.e("profileUpdateResponse=",userRepository.updateProfileData(profileUpdate).toString())
            resultProfileUpdate.value=userRepository.updateProfileData(profileUpdate)
        }
    }

    fun getUserProfileIconDetail() {
        viewModelScope.launch(Dispatchers.Default) {
            resultProfileIcon.postValue(ResponseHandler.Loading)
            resultProfileIcon.postValue(userRepository.getProfileIconDetails())
        }
    }

     fun getUserProfileDetails()
    {
        viewModelScope.launch {
            resultProfileUser.postValue(ResponseHandler.Loading)
            Log.e("userProfile=",userRepository.getUserCurrentUserDeail().toString())
            resultProfileUser.postValue(
                userRepository.getUserCurrentUserDeail(
                )
            )
        }
    }
}