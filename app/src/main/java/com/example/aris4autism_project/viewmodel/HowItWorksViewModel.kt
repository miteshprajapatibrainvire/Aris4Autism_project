package com.example.aris4autism_project.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.api.ApiInterface
import com.example.aris4autism_project.model.YoutubeVideoResponse
import com.example.aris4autism_project.repository.UserRespository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HowItWorksViewModel(val context:Context): ViewModel() {

    var resultHowItWork=MutableLiveData<BaseResponse<YoutubeVideoResponse>>()
    val userRepository=UserRespository(ApiInterface.getInterfaceData())

    fun getYoutubeVideosResponse(auth:String,platform:String,ver:String)
    {
        resultHowItWork.value=BaseResponse.Loading()
        val resultVideos=userRepository.getYoutubeVideos(auth,platform,ver)
        resultVideos.enqueue(object : Callback<YoutubeVideoResponse>
        {
            override fun onResponse(
                call: Call<YoutubeVideoResponse>,
                response: Response<YoutubeVideoResponse>
            ) {
                if(response.isSuccessful)
                {
                    if(response.code()==200)
                    {
                        resultHowItWork.value=BaseResponse.Success(response.body())
                    }
                    else
                    {
                        resultHowItWork.value=BaseResponse.Error(response.body().toString())
                    }
                }
                else
                {
                    resultHowItWork.value=BaseResponse.Error(response.body().toString())
                }
            }

            override fun onFailure(call: Call<YoutubeVideoResponse>, t: Throwable) {
               resultHowItWork.value=BaseResponse.Error(t.toString())
            }

        })

    }


}