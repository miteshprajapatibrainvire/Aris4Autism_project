package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.model.OverViewInnerDetailResponse
import com.example.aris4autism_project.model.OverViewResponse
import com.example.aris4autism_project.repository.UserRespository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverViewViewModel(val context: Context):ViewModel() {

    var resultOverView:MutableLiveData<BaseResponse<OverViewResponse>> = MutableLiveData()
    val userRepository=UserRespository()

    var resultInnerOverView:MutableLiveData<BaseResponse<OverViewInnerDetailResponse>> = MutableLiveData()

    fun getOverViewInnerDetails(id:String,authToken: String,platform: String,ver:String)
    {
        resultInnerOverView.value=BaseResponse.Loading()
        val resultInnerData=userRepository.getOverViewInnerDetail(id,authToken,platform,ver)

        resultInnerData.enqueue(object: Callback<OverViewInnerDetailResponse>{
            override fun onResponse(
                call: Call<OverViewInnerDetailResponse>,
                response: Response<OverViewInnerDetailResponse>
            ) {
                if(response.isSuccessful)
                {
                    if(response.code()==200)
                    {
                        Log.e("overviewInnerData=",response.body().toString())
                        resultInnerOverView.value=BaseResponse.Success(response.body())
                    }
                }


            }

            override fun onFailure(call: Call<OverViewInnerDetailResponse>, t: Throwable) {

            }

        })

    }

    fun getOverViewDetails(authToken:String,platform:String,ver:String) {

        resultOverView.value = BaseResponse.Loading()

        val resultOverViewData=userRepository.getOverViewDetail(authToken,platform,ver)
        resultOverViewData.enqueue(object: Callback<OverViewResponse> {
            override fun onResponse(
                call: Call<OverViewResponse>,
                response: Response<OverViewResponse>
            ) {
                if(response.isSuccessful)
                {
                    if(response.code()==200)
                    {
                        Log.e("overViewResponse=",response.body().toString())
                        resultOverView.value=BaseResponse.Success(response.body())
                    }
                }
                else
                {
                    resultOverView.value=BaseResponse.Error(response.body().toString())
                }
            }

            override fun onFailure(call: Call<OverViewResponse>, t: Throwable)
            {
                    resultOverView.value=BaseResponse.Error(t.toString())
            }

        })

    }


}