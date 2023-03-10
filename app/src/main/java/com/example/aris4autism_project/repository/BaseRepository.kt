package com.example.aris4autism_project.repository

import android.accounts.NetworkErrorException
import android.util.Log
import com.example.aris4autism_project.api.HttpCommonMethod
import com.example.aris4autism_project.api.HttpErrorCode
import com.example.aris4autism_project.api.ResponseListData
import com.example.aris4autism_project.model.ErrorWrapper
import com.example.aris4autism_project.model.ResponseData
import com.example.aris4autism_project.model.ResponseHandler
import com.google.gson.Gson
import okhttp3.internal.http2.ConnectionShutdownException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseRepository {

    suspend fun <T : Any> makeAPICall(call: suspend () -> Response<ResponseData<T>>): ResponseHandler<ResponseData<T>?> {
        try {
            val response = call.invoke()
            when {
                response.code() == 200 -> return ResponseHandler.OnSuccessResponse(response.body())
                response.code() == 401 -> {
                    val body = response.errorBody()
                    var message: String = ""
                    val bodyString = body?.string()
                    val responseWrapper =
                        Gson().fromJson<ErrorWrapper>(bodyString, ErrorWrapper::class.java)
                    message = if (responseWrapper.meta?.status_code == 401) {
                        if (responseWrapper.errors != null) {
                            HttpCommonMethod.getErrorMessage(responseWrapper.errors)
                        } else {
                            responseWrapper.meta?.message.toString()
                        }
                    } else {
                        responseWrapper.meta?.message.toString()
                    }
                    return ResponseHandler.OnFailed(
                        HttpErrorCode.UNAUTHORIZED.code,
                        message,
                        responseWrapper.meta?.message_code.toString()
                    )
                }
                response.code() == 422 -> {
                    val body = response.errorBody()
                    var message: String = ""
                    val bodyString = body?.string()
                    val responseWrapper =
                        Gson().fromJson<ErrorWrapper>(bodyString, ErrorWrapper::class.java)
                    message = if (responseWrapper.meta?.status_code == 422) {
                        if (responseWrapper.errors != null) {
                            HttpCommonMethod.getErrorMessage(responseWrapper.errors)
                        } else {
                            responseWrapper.meta?.message.toString()
                        }
                    } else {
                        responseWrapper.meta?.message.toString()
                    }
                    return ResponseHandler.OnFailed(
                        HttpErrorCode.EMPTY_RESPONSE.code,
                        message,
                        responseWrapper.meta?.message_code.toString()
                    )
                }
                else -> {
                    val body = response.errorBody()
                    var message = ""
                    val bodyString = body?.string()
                    val responseWrapper =
                        Gson().fromJson<ErrorWrapper>(bodyString, ErrorWrapper::class.java)
                    message = if (responseWrapper.meta?.status_code == 422) {
                        if (responseWrapper.errors != null) {
                            HttpCommonMethod.getErrorMessage(responseWrapper.errors)
                        } else {
                            responseWrapper.meta?.message.toString()
                        }
                    } else {
                        responseWrapper.meta?.message.toString()
                    }
                    if (message.isNullOrEmpty()) {
                        return ResponseHandler.OnFailed(
                            HttpErrorCode.EMPTY_RESPONSE.code,
                            message,
                            responseWrapper.meta?.message_code.toString()
                        )
                    } else {
                        return ResponseHandler.OnFailed(
                            HttpErrorCode.NOT_DEFINED.code,
                            message,
                            responseWrapper.meta?.message_code.toString()
                        )
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("Error = ", e.toString())
            return if (e is SocketTimeoutException ||
                e is UnknownHostException ||
                e is IOException ||
                e is NetworkErrorException ||
                e is ConnectionShutdownException
            ) {
                ResponseHandler.OnFailed(HttpErrorCode.NO_CONNECTION.code, "", "")
            }
            else
            {
                ResponseHandler.OnFailed(HttpErrorCode.NOT_DEFINED.code, "", "")
            }
            /* if (e is CancellationException) {
                 return ResponseHandler.OnFailed(HttpErrorCode.NO_CONNECTION.code, "")
             } else {
                 return if (e is SocketTimeoutException ||
                     e is UnknownHostException ||
                     e is IOException ||
                     e is NetworkErrorException ||
                     e is ConnectionShutdownException
                 ) {
                     ResponseHandler.OnFailed(HttpErrorCode.NO_CONNECTION.code, "")
                 } else {
                     ResponseHandler.OnFailed(HttpErrorCode.NOT_DEFINED.code, "")
                 }
             }*/
        }
    }

    suspend fun <T : Any> makeAPICallForList(call: suspend () -> Response<ResponseListData<T>>): ResponseHandler<ResponseListData<T>?> {
        try {
            val response = call.invoke()
            when {
                response.code() == 200 -> return ResponseHandler.OnSuccessResponse(response.body())
                response.code() == 401 -> {
                    val body = response.errorBody()
                    var message: String = ""
                    val bodyString = body?.string()
                    val responseWrapper =
                        Gson().fromJson<ErrorWrapper>(bodyString, ErrorWrapper::class.java)
                    message = if (responseWrapper.meta?.status_code == 401) {
                        if (responseWrapper.errors != null) {
                            HttpCommonMethod.getErrorMessage(responseWrapper.errors)
                        } else {
                            responseWrapper.meta?.message.toString()
                        }
                    } else {
                        responseWrapper.meta?.message.toString()
                    }
                    return ResponseHandler.OnFailed(
                        HttpErrorCode.UNAUTHORIZED.code,
                        message,
                        responseWrapper.meta?.message_code.toString()
                    )
                }
                response.code() == 422 -> {
                    val body = response.errorBody()
                    var message: String = ""
                    val bodyString = body?.string()
                    val responseWrapper =
                        Gson().fromJson<ErrorWrapper>(bodyString, ErrorWrapper::class.java)
                    message = if (responseWrapper.meta?.status_code == 422) {
                        if (responseWrapper.errors != null) {
                            HttpCommonMethod.getErrorMessage(responseWrapper.errors)
                        } else {
                            responseWrapper.meta?.message.toString()
                        }
                    } else {
                        responseWrapper.meta?.message.toString()
                    }
                    return ResponseHandler.OnFailed(
                        HttpErrorCode.EMPTY_RESPONSE.code,
                        message,
                        responseWrapper.meta?.message_code.toString()
                    )
                }
                else -> {
                    val body = response.errorBody()
                    var message = ""
                    val bodyString = body?.string()
                    val responseWrapper =
                        Gson().fromJson<ErrorWrapper>(bodyString, ErrorWrapper::class.java)
                    message = if (responseWrapper.meta?.status_code == 422) {
                        if (responseWrapper.errors != null) {
                            HttpCommonMethod.getErrorMessage(responseWrapper.errors)
                        } else {
                            responseWrapper.meta?.message.toString()
                        }
                    } else {
                        responseWrapper.meta?.message.toString()
                    }
                    if (message.isNullOrEmpty()) {
                        return ResponseHandler.OnFailed(
                            HttpErrorCode.EMPTY_RESPONSE.code,
                            message,
                            responseWrapper.meta?.message_code.toString()
                        )
                    } else {
                        return ResponseHandler.OnFailed(
                            HttpErrorCode.NOT_DEFINED.code,
                            message,
                            responseWrapper.meta?.message_code.toString()
                        )
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("Error = ", e.toString())
            return if (e is SocketTimeoutException ||
                e is UnknownHostException ||
                e is IOException ||
                e is NetworkErrorException ||
                e is ConnectionShutdownException
            ) {
                ResponseHandler.OnFailed(HttpErrorCode.NO_CONNECTION.code, "", "")
            } else {
                ResponseHandler.OnFailed(HttpErrorCode.NOT_DEFINED.code, "", "")
            }
            /* if (e is CancellationException) {
                 return ResponseHandler.OnFailed(HttpErrorCode.NO_CONNECTION.code, "")
             } else {
                 return if (e is SocketTimeoutException ||
                     e is UnknownHostException ||
                     e is IOException ||
                     e is NetworkErrorException ||
                     e is ConnectionShutdownException
                 ) {
                     ResponseHandler.OnFailed(HttpErrorCode.NO_CONNECTION.code, "")
                 } else {
                     ResponseHandler.OnFailed(HttpErrorCode.NOT_DEFINED.code, "")
                 }
             }*/
        }


    }

}