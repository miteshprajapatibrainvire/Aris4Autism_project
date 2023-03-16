package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.model.overviewmodel.OverViewResponseModel
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.UserRespository
import com.example.food_nutrition_recipe_app.model.clonemodel.OverViewInnerDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.launch

class OverViewViewModel(val context: Context):ViewModel() {

    //var resultOverView:MutableLiveData<BaseResponse<OverViewResponse>> = MutableLiveData()
    var resultOverView = MutableLiveData<ResponseHandler<ResponseData<OverViewResponseModel>?>>()
    val userRepository=UserRespository(ApiClient.getApiInterface())
    var resultInnerOverView:MutableLiveData<ResponseHandler<ResponseData<OverViewInnerDetailResponse>?>> = MutableLiveData()

    fun getOverViewInnerDetails(id:String)
    {
        viewModelScope.launch(Dispatchers.Default)
        {
            resultInnerOverView.postValue(ResponseHandler.Loading)
            Log.e("overviewinnerdata=",userRepository.getOverViewInnerDetail(id).toString())
            resultInnerOverView.postValue(userRepository.getOverViewInnerDetail(id))
        }
    }

    fun getOverViewDetails()
    {
        viewModelScope.launch(coroutineContext)
        {
            resultOverView.postValue(ResponseHandler.Loading)
            Log.e("overviewResponse=",userRepository.getOverViewDetail().toString())
            resultOverView.value = userRepository.getOverViewDetail()
            //resultOverView.postValue(userRepository.getOverViewDetail(authToken, platform, ver))
        }
    }

}