package com.example.aris4autism_project.model.networkresponse
/**
 * Created by rajanbhavsar on 20/11/19, 2:02 PM
 * Package Name : com.appname.structure.network.Client
 * Project Name : BrainvireStructure
 */

sealed class ResponseHandler<out T> {
    object Loading : ResponseHandler<Nothing>()
    class OnFailed(val code:Int,val message:String, val messageCode:String) : ResponseHandler<Nothing>()
    class OnSuccessResponse<T>(val response: T) : ResponseHandler<T>()
}