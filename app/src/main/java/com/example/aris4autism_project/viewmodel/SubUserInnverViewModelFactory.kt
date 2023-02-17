package com.example.aris4autism_project.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SubUserInnverViewModelFactory
    (val context: Context): ViewModelProvider.Factory {

    public override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SubUserInnerViewModel(context) as T
    }


}