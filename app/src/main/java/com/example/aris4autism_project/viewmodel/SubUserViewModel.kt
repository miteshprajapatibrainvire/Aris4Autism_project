package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.model.responsemodel.ResponseData
import com.example.aris4autism_project.model.responsemodel.ResponseHandler
import com.example.aris4autism_project.model.editsubuserdetailmodel.EditSubUserDetailsModel
import com.example.aris4autism_project.model.subusermodel.SubUserResponseModel
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.UserRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubUserViewModel(val context: Context):ViewModel()
{

    var subUserResult: MutableLiveData<ResponseHandler<ResponseData<SubUserResponseModel>?>> = MutableLiveData()
    val userRepository=UserRespository(ApiClient.getApiInterface())

    //val subUserDetailResult:MutableLiveData<BaseResponse<SubUserDetailsResponse>> = MutableLiveData()
    var subUserDetailResult=MutableLiveData<ResponseHandler<ResponseData<EditSubUserDetailsModel>?>>()
    fun getSubUserEditDetailResult(id:String)
    {
        viewModelScope.launch {
            subUserDetailResult.postValue(ResponseHandler.Loading)
            subUserDetailResult.postValue(userRepository.getSubUserEditDetails(id))
        }
    }

   /* fun getSubUserEditDetailResult(id:String,auth:String,platform:String,ver:String)
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
    }*/

    fun getSubUserDetailsModel()
    {
        viewModelScope.launch(Dispatchers.Default)
        {
            subUserResult.postValue(ResponseHandler.Loading)
            Log.e("subuserDetail=",userRepository.getSubUserDetails().toString())
            subUserResult.postValue(userRepository.getSubUserDetails())
        }

//        val resultCallSubUser=userRepository.getSubUserDetails(authToken,platform,ver)
//        resultCallSubUser.enqueue(object:Callback<SubUserResponse>{
//            override fun onResponse(
//                call: Call<SubUserResponse>,
//                response: Response<SubUserResponse>
//            ) {
//
//                if(response.isSuccessful)
//                {
//                    if(response.code()==200) {
//                        Log.e("response=",response.body().toString())
//                        subUserResult.value=BaseResponse.Success(response.body())
//                    }
//                }
//                else
//                {
//                    subUserResult.value=BaseResponse.Error(response.body().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<SubUserResponse>, t: Throwable) {
//
//            }
//
//        })

    }

}