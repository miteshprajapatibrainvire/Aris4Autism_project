package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.model.howitworkmodel.YoutubeVideoResponseModel
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.UserRespository
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.launch

class HowItWorksViewModel(val context:Context): ViewModel() {

    var resultHowItWork=MutableLiveData<ResponseHandler<ResponseData<YoutubeVideoResponseModel>?>>()
    val userRepository=UserRespository(ApiClient.getApiInterface())

    fun getYoutubeVideosResponse()
    {
        viewModelScope.launch(coroutineContext) {
            resultHowItWork.postValue(ResponseHandler.Loading)
            Log.e("howitoworkapicall=",userRepository.getYoutubeVideos().toString())
            resultHowItWork.postValue(userRepository.getYoutubeVideos())
        }
    }


}