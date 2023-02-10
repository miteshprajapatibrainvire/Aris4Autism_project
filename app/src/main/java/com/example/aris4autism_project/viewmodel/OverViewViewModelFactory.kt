package com.example.aris4autism_project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aris4autism_project.model.OverViewModel
import android.content.Context

class OverViewViewModelFactory(val context:Context): ViewModelProvider.Factory
{

    public override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OverViewViewModel(context) as T
    }

}