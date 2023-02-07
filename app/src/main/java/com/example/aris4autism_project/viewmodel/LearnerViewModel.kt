package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.model.LearnerResponse
import com.example.aris4autism_project.model.RequestLogin
import com.example.aris4autism_project.repository.UserRespository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LearnerViewModel(val context: Context): ViewModel() {

    val resultLearner=MutableLiveData<BaseResponse<LearnerResponse>>()
    val userReposiroty=UserRespository()

    fun getLearnerList(authToken:String,platform:String,version:String)
    {
        resultLearner.value=BaseResponse.Loading()

        val resultData=userReposiroty.getLearnerDetail(authToken,platform,version)
        resultData.enqueue(object : Callback<LearnerResponse>{
            override fun onResponse(
                call: Call<LearnerResponse>,
                response: Response<LearnerResponse>
            ) {
                if(response.isSuccessful)
                {
                    if(response.code()==200) {
                        resultLearner.value=BaseResponse.Success(response.body())
                    }
                }
                else
                {
                    resultLearner.value=BaseResponse.Error(response.body().toString())
                }
            }

            override fun onFailure(call: Call<LearnerResponse>, t: Throwable) {
                resultLearner.value=BaseResponse.Error(t.toString())
            }

        })


    }


}