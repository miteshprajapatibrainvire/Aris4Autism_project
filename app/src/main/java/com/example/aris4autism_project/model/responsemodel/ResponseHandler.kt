package com.example.aris4autism_project.model.responsemodel

sealed class ResponseHandler<out T> {
    object Loading : ResponseHandler<Nothing>()
    class OnFailed(val code:Int,val message:String, val messageCode:String) : ResponseHandler<Nothing>()
    class OnSuccessResponse<T>(val response: T) : ResponseHandler<T>()
}