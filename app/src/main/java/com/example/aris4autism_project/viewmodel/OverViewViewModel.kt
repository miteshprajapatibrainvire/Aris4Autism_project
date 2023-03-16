package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.model.OverViewInnerDetailResponse
import com.example.aris4autism_project.model.responsemodel.ResponseData
import com.example.aris4autism_project.model.responsemodel.ResponseHandler
import com.example.aris4autism_project.model.overviewmodel.OverViewResponseModel
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.UserRespository
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

//    fun getOverViewInnerDetails(id:String,authToken: String,platform: String,ver:String)
//    {
//        resultInnerOverView.value=BaseResponse.Loading()
//        val resultInnerData=userRepository.getOverViewInnerDetail(id,authToken,platform,ver)
//
//        resultInnerData.enqueue(object: Callback<OverViewInnerDetailResponse>{
//            override fun onResponse(
//                call: Call<OverViewInnerDetailResponse>,
//                response: Response<OverViewInnerDetailResponse>
//            ) {
//                if(response.isSuccessful)
//                {
//                    if(response.code()==200)
//                    {
//                        Log.e("overviewInnerData=",response.body().toString())
//                        resultInnerOverView.value=BaseResponse.Success(response.body())
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<OverViewInnerDetailResponse>, t: Throwable) {
//
//            }
//
//        })
//
//   }



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

//    fun getOverViewDetails(authToken:String,platform:String,ver:String) {
//        resultOverView.value = BaseResponse.Loading()
//        val resultOverViewData=userRepository.getOverViewDetail(authToken,platform,ver)
//        resultOverViewData.enqueue(object: Callback<OverViewResponse> {
//            override fun onResponse(
//                call: Call<OverViewResponse>,
//                response: Response<OverViewResponse>
//            ) {
//                if(response.isSuccessful)
//                {
//                    if(response.code()==200)
//                    {
//                        Log.e("overViewResponse=",response.body().toString())
//                        resultOverView.value=BaseResponse.Success(response.body())
//                    }
//                }
//                else
//                {
//                    resultOverView.value=BaseResponse.Error(response.body().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<OverViewResponse>, t: Throwable)
//            {
//                    resultOverView.value=BaseResponse.Error(t.toString())
//            }
//        })
//    }


}