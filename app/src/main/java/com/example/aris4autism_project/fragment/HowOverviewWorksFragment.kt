package com.example.aris4autism_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aris4autism_project.databinding.FragmentHowOverviewWorksBinding
import com.example.aris4autism_project.model.DataXXXXXXXXXXXXXXXXXXXXXXXX

class HowOverviewWorksFragment() : Fragment() {

    lateinit var binding:FragmentHowOverviewWorksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentHowOverviewWorksBinding.inflate(inflater)



        return binding.root
    }


}