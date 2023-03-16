package com.example.aris4autism_project.api

import com.example.aris4autism_project.BuildConfig
import com.example.aris4autism_project.Utils.Constant
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiCall {

    var goHttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
//    private var okHttpClient: OkHttpClient? = null

    var okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(goHttpLoggingInterceptor)
        .build()

    var oneRetrofit: Retrofit? = null

    val client: Retrofit?
        get() {
            if (oneRetrofit == null) {
                oneRetrofit = Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return oneRetrofit
        }

//    private fun getOkHttpClient(): OkHttpClient {
//        if (okHttpClient == null) {
//            val logging = HttpLoggingInterceptor()
//            logging.level = HttpLoggingInterceptor.Level.BODY
//            val builder = OkHttpClient.Builder()
//            if (BuildConfig.DEBUG) {
//                builder.addInterceptor(logging)
//            }
//            builder.readTimeout(60, TimeUnit.SECONDS)
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .addInterceptor(HttpHandleIntercept())
//                .build()
//            okHttpClient = builder.build()
//
//        }
//        return okHttpClient!!
//    }


}