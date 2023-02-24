package com.example.aris4autism_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aris4autism_project.databinding.FragmentHowSubUserWorksBinding
import com.example.aris4autism_project.model.DataXXXXXXXXXXXXXXXXXXXXXXXX

class HowSubUserWorksFragment() : Fragment() {

    lateinit var binding:FragmentHowSubUserWorksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHowSubUserWorksBinding.inflate(inflater)


        return binding.root
    }


}