package com.example.aris4autism_project.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.R
import com.example.aris4autism_project.model.ProfileIconResponse
import com.example.aris4autism_project.model.UserProfileResponse
import com.example.aris4autism_project.repository.UserRespository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileDetailViewModel(val context:Context): ViewModel()
{
    var fullname:String=""
    var mobileNo:String=""
    var emailId:String=""
    var gen:String=""
    var dob:String=""
    var resultProfileValidate= MutableLiveData<String>()
    fun getProfileResult() : LiveData<String> = resultProfileValidate

    val userRepository=UserRespository()
    var resultProfileUser : MutableLiveData<BaseResponse<UserProfileResponse>> = MutableLiveData()
    var resultProfileIcon:MutableLiveData<BaseResponse<ProfileIconResponse>> = MutableLiveData()



    fun getUserProfileIconDetail(auth:String,platform:String,version:String)
    {
        resultProfileUser.value=BaseResponse.Loading()
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
        resultProfileData.enqueue(object : retrofit2.Callback<UserProfileResponse>
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
                else
                {

                }
            }

            override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
//                resultProfileUser.value= BaseResponse.Error(t.toString()).toString()
            }

        })
    }


    fun getProfileValidation()
    {
        when{
            fullname.isEmpty()->{
                resultProfileValidate.value=context.getString(R.string.enterfullaname)
            }

            mobileNo.isEmpty()->{
                resultProfileValidate.value=context.getString(R.string.entermobile)
            }
            fullname.isNotEmpty()&&mobileNo.isNotEmpty()->{
                resultProfileValidate.value="valid Data"
            }
        }
    }
}