package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.model.SubUserModelInnerResponse
import com.example.aris4autism_project.repository.UserRespository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubUserInnerViewModel(val context: Context): ViewModel() {

    val userRepository= UserRespository()

//    var subUserInnerResult: MutableLiveData<BaseResponse<SubUserInnerDetailModelResponse>> = MutableLiveData()
    var subUserInnerResult: MutableLiveData<BaseResponse<SubUserModelInnerResponse>> = MutableLiveData()

    fun getSubUserInnerDetails(id:String,authToken: String,platform: String,ver:String)
    {
        subUserInnerResult.value=BaseResponse.Loading()
        val resultInnerSubUser=userRepository.getSubUserInnerDetail(id,authToken,platform,ver)
        resultInnerSubUser.enqueue(object: Callback<SubUserModelInnerResponse> {
            override fun onResponse(
                call: Call<SubUserModelInnerResponse>,
                response: Response<SubUserModelInnerResponse>
            ) {
                if(response.isSuccessful)
                {
                    if(response.code()==200)
                    {
                        Log.e("subuserResponse=",response.body().toString())
                        subUserInnerResult.value=BaseResponse.Success(response.body())
                    }
                    else
                    {
                        subUserInnerResult.value=BaseResponse.Error(response.body().toString())
                    }
                }
                else
                {
                    subUserInnerResult.value=BaseResponse.Error(response.body().toString())
                }
            }

            override fun onFailure(call: Call<SubUserModelInnerResponse>, t: Throwable)
            {
                subUserInnerResult.value=BaseResponse.Error(t.toString())
            }

        })
    }
}