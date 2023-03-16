package com.example.aris4autism_project.model.networkresponse

open class ResponseData<T>: ResponseWrapper<T>() {
//    @SerializedName("data")
//    @Expose
//    var data: T? = null

    override fun toString(): String {
        return "ResponseWrapper{" +
                "data=" + data.toString()
    }
}