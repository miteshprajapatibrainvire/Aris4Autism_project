package com.example.aris4autism_project

import android.app.Application
import com.example.aris4autism_project.api.MyPreference
import com.example.aris4autism_project.network.ApiClient

class BaseStructureApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        ApiClient.initRetrofit()
//        initAppCenter()
       MyPreference.init(this)
        // printHashKey();
    }
}