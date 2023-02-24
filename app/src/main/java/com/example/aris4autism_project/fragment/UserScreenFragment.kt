package com.example.aris4autism_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.FragmentUserScreenBinding
import com.google.android.material.tabs.TabLayout

class UserScreenFragment : Fragment() {

    lateinit var binding:FragmentUserScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        binding=FragmentUserScreenBinding.inflate(inflater,container,false)


        binding.viewPager.setUserInputEnabled(false)

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Profile"))


        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_overViewDetailsFragment_to_overviewFragment2)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        return binding.root
    }


}