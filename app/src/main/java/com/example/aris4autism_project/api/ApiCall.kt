package com.example.aris4autism_project.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiCall {

    var goHttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    var okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(goHttpLoggingInterceptor)
        .build()

    var oneRetrofit: Retrofit? = null

    val client: Retrofit?
        get() {
            if(oneRetrofit == null)
            {
                oneRetrofit = Retrofit.Builder()
                    .baseUrl("https://aris4autism.demo.brainvire.dev/api/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return oneRetrofit
        }

}