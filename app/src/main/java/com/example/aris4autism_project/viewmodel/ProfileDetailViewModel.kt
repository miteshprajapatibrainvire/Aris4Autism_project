package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.R
import com.example.aris4autism_project.api.ApiInterface
import com.example.aris4autism_project.databinding.FragmentProfileDetailsBinding
import com.example.aris4autism_project.fragment.ProfileDetailsFragment
import com.example.aris4autism_project.model.ProfileIconResponse
import com.example.aris4autism_project.model.UpdateProfileSendData
import com.example.aris4autism_project.model.UpdateProfileResponse
import com.example.aris4autism_project.model.UserProfileResponse
import com.example.aris4autism_project.repository.UserRespository
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body

class ProfileDetailViewModel( var context: Context): ViewModel()
{
    var fullname:String=""
    var mobileNo:String=""
//    var emailId:String=""
    var gen:String=""
    var dob:String=""
    var email:String=""

   lateinit var binding:FragmentProfileDetailsBinding

    //  fun getProfileResult() : LiveData<String> = resultProfileValidate

    val userRepository=UserRespository(ApiInterface.getInterfaceData())
    lateinit var bindLayout:ProfileDetailsFragment

    var resultProfileUser : MutableLiveData<BaseResponse<UserProfileResponse>> = MutableLiveData()
    var resultProfileIcon:MutableLiveData<BaseResponse<ProfileIconResponse>> = MutableLiveData()
    var resultProfileUpdate:MutableLiveData<BaseResponse<UpdateProfileResponse>> = MutableLiveData()
    var resultProfileValidation=MutableLiveData<String>()

    fun profileValidation()
    {
        when{
            fullname.isEmpty() && mobileNo.isEmpty()->{
                resultProfileValidation.value=context.getString(R.string.bothempty)
                //binding.editFullName.setBackgroundColor(ContextCompat.getColor(context,R.color.red))
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
        }
    }

    fun profileUpdateDetail(@Body profileUpdate:UpdateProfileSendData, auth:String, platform: String, ver:String)
    {
        resultProfileUpdate.value=BaseResponse.Loading()
        val resultProfile=userRepository.updateProfileData(profileUpdate,auth,platform,ver)
        resultProfile.enqueue(object : Callback<UpdateProfileResponse>{
            override fun onResponse(
                call: Call<UpdateProfileResponse>,
                response: Response<UpdateProfileResponse>
            ) {
                if(response.isSuccessful)
                {
                    if(response.code()==200)
                    {
                        resultProfileUpdate.value=BaseResponse.Success(response.body())
                    }
                    else
                    {
                        resultProfileUpdate.value=BaseResponse.Error(response.body().toString())
                    }
                }
                else
                {
                    resultProfileUpdate.value=BaseResponse.Success(response.body())
                }
            }

            override fun onFailure(call: Call<UpdateProfileResponse>, t: Throwable) {
                resultProfileUpdate.value=BaseResponse.Error(t.toString())
            }

        })
    }

    fun getUserProfileIconDetail(auth:String,platform:String,version:String)
    {
        resultProfileIcon.value=BaseResponse.Loading()
        val resultProfileData=userRepository.getProfileIconDetails(auth,platform,version)
        resultProfileData.enqueue(object : Callback<ProfileIconResponse> {
            override fun onResponse(
                call: Call<ProfileIconResponse>,
                response: Response<ProfileIconResponse>
            ) {
                if(response.isSuccessful)
                {
                    if(response.code()==200)
                    {
                        resultProfileIcon.value=BaseResponse.Success(response.body())
                    }
                    else
                    {
                        resultProfileIcon.value=BaseResponse.Error(response.body().toString())
                    }
                }
                else
                {
                    resultProfileIcon.value=BaseResponse.Error(response.body().toString())
                }
            }

            override fun onFailure(call: Call<ProfileIconResponse>, t: Throwable) {
                resultProfileIcon.value=BaseResponse.Error(t.toString())
            }


        })
    }

    fun getUserProfileDetails(auth:String,platform:String,version:String)
    {
        resultProfileUser.value=BaseResponse.Loading()

        val resultProfileData=userRepository.getUserCurrentUserDeail(auth,platform,version)
        resultProfileData.enqueue(object : Callback<UserProfileResponse>
        {
            override fun onResponse(
                call: Call<UserProfileResponse>,
                response: Response<UserProfileResponse>
            ) {
                if(response.isSuccessful)
                {
                    if(response.code()==200)
                    {
                        resultProfileUser.value = BaseResponse.Success(response.body())
                    }
                    else
                    {
//                       resultProfileDa          ta.value=BaseResponse.Success(response.body())
                    }
                }

            }

            override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
//                resultProfileUser.value= BaseResponse.Error(t.toString()).toString()
            }

        })
    }

    fun invisibleErrorTexts(txInput: TextInputEditText) {
//        txInput.isErrorEnabled = false
//        txInput.boxStrokeErrorColor =
//            ColorStateList.valueOf(ContextCompat.getColor(txInput.context, R.color.gray))
//        txInput.boxStrokeColor = 1
//        txInput.boxStrokeWidthFocused = 1
        txInput.hint = "enter data!"
    }


}