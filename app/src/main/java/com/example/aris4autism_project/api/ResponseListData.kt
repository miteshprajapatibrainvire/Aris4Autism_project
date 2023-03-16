package com.example.aris4autism_project.api

import com.example.aris4autism_project.model.responsemodel.ResponseWrapper

class ResponseListData<T> : ResponseWrapper<T>(){

//    @SerializedName("data")
//    @Expose
//    var data: List<T>? = null

    override fun toString(): String {
        return "ResponseWrapper{" +
                "data=" + data .toString()
    }

}
