package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.api.ApiInterface
import com.example.aris4autism_project.model.SubScriptionResponse
import com.example.aris4autism_project.repository.UserRespository
import retrofit2.Call
import retrofit2.Response

class SubScriptionViewModel(val context: Context):ViewModel()
{
    val resultSubscription=MutableLiveData<BaseResponse<SubScriptionResponse>>()
    var userRepository= UserRespository(ApiInterface.getInterfaceData())

    fun getSubUserDetails(auth:String,platform:String,version:String)
    {
        resultSubscription.value=BaseResponse.Loading()
        val resultData=userRepository.getSubscriptionDetails(auth,platform,version)
        resultData.enqueue(object : retrofit2.Callback<SubScriptionResponse> {
            override fun onResponse(
                call: Call<SubScriptionResponse>,
                response: Response<SubScriptionResponse>
            ) {

                if(response.isSuccessful)
                {
                    if(response.code()==200)
                    {
                        Log.e("subscriptionResponse=",response.body().toString())
                        resultSubscription.value=BaseResponse.Success(response.body())
                    }
                }
                else
                {
                    resultSubscription.value=BaseResponse.Error(response.body().toString())
                }
            }

            override fun onFailure(call: Call<SubScriptionResponse>, t: Throwable) {
               resultSubscription.value=BaseResponse.Error(t.toString())
            }
        })
    }
}