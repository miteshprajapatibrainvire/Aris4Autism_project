package com.example.aris4autism_project.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aris4autism_project.databinding.FragmentProfileDetailsBinding

class ProfileDetailViewModelFactory(val context: Context):ViewModelProvider.Factory {

    public override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        return ProfileDetailViewModel(context) as T
    }

}