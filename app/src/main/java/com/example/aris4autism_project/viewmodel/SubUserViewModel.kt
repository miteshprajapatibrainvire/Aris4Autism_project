package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.model.SubUserDetailsResponse
import com.example.aris4autism_project.model.SubUserResponse
import com.example.aris4autism_project.repository.UserRespository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubUserViewModel(val context: Context):ViewModel()
{

    var subUserResult: MutableLiveData<BaseResponse<SubUserResponse>> = MutableLiveData()
    val userRepository=UserRespository()

    val subUserDetailResult:MutableLiveData<BaseResponse<SubUserDetailsResponse>> = MutableLiveData()

    fun getSubUserEditDetailResult(id:String,auth:String,platform:String,ver:String)
    {
        subUserDetailResult.value=BaseResponse.Loading()
        val resultSubDetail=userRepository.getSubUserEditDetails(id,auth,platform,ver)
        resultSubDetail.enqueue(object : Callback<SubUserDetailsResponse>{
            override fun onResponse(
                call: Call<SubUserDetailsResponse>,
                response: Response<SubUserDetailsResponse>
            ) {
                if(response.isSuccessful)
                {
                    if(response.code()==200)
                    {
                        subUserDetailResult.value=BaseResponse.Success(response.body())
                    }
                    else
                    {
                        subUserDetailResult.value=BaseResponse.Success(response.body())
                    }
                }
                else
                {
                    subUserDetailResult.value=BaseResponse.Success(response.body())
                }

            }

            override fun onFailure(call: Call<SubUserDetailsResponse>, t: Throwable) {

            }

        })
    }

    fun getSubUserDetailsModel(authToken:String,platform:String,ver:String)
    {
        subUserResult.value=BaseResponse.Loading()
        val resultCallSubUser=userRepository.getSubUserDetails(authToken,platform,ver)
        resultCallSubUser.enqueue(object:Callback<SubUserResponse>{
            override fun onResponse(
                call: Call<SubUserResponse>,
                response: Response<SubUserResponse>
            ) {

                if(response.isSuccessful)
                {
                    if(response.code()==200) {
                        Log.e("response=",response.body().toString())
                        subUserResult.value=BaseResponse.Success(response.body())
                    }
                }
                else
                {
                    subUserResult.value=BaseResponse.Error(response.body().toString())
                }
            }

            override fun onFailure(call: Call<SubUserResponse>, t: Throwable) {

            }

        })

    }



}