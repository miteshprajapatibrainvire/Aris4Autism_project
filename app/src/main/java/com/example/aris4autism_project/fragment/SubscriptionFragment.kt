package com.example.aris4autism_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.FragmentSubscriptionBinding
import com.example.aris4autism_project.databinding.FragmentSubuserBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class SubscriptionFragment : Fragment() {


    lateinit var binding:FragmentSubscriptionBinding
    lateinit var buttonView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSubscriptionBinding.inflate(inflater,container,false)
        buttonView=requireActivity().findViewById(R.id.bottom_navigation)
        buttonView.visibility=View.VISIBLE

        return binding.root
    }


}