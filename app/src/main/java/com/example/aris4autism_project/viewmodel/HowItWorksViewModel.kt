package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.model.howitworkmodel.YoutubeVideoResponse
import com.example.aris4autism_project.model.responsemodel.ResponseData
import com.example.aris4autism_project.model.responsemodel.ResponseHandler
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.UserRespository
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.launch

class HowItWorksViewModel(val context:Context): ViewModel() {

    //var resultHowItWork=MutableLiveData<BaseResponse<YoutubeVideoResponse>>()
    var resultHowItWork=MutableLiveData<ResponseHandler<ResponseData<YoutubeVideoResponse>?>>()
    val userRepository=UserRespository(ApiClient.getApiInterface())

    fun getYoutubeVideosResponse()
    {
        viewModelScope.launch(coroutineContext) {
            resultHowItWork.postValue(ResponseHandler.Loading)
            Log.e("howitoworkapicall=",userRepository.getYoutubeVideos().toString())
            resultHowItWork.postValue(userRepository.getYoutubeVideos())
        }
    }

   /* fun getYoutubeVideosResponse(auth:String,platform:String,ver:String)
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

    }*/


}