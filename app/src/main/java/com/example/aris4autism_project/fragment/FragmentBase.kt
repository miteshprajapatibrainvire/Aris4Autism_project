package com.example.aris4autism_project.fragment

import androidx.databinding.ViewDataBinding
import com.example.aris4autism_project.viewmodel.ViewModelBase

abstract class FragmentBase<V : ViewModelBase,DataBinding : ViewDataBinding>:FragmentBaseWrapper() {

    private lateinit var viewModel:V
    private lateinit var dataBinding: DataBinding
    private var MY_REQUEST_CODE=1111

    abstract fun getLayoutId():Int

    abstract fun setupToolbar()

    abstract fun initializeScreenVariables()




}