package com.example.aris4autism_project.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HowItWorksViewModelFactory(val context: Context):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HowItWorksViewModel(context) as T

    }

}