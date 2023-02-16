package com.example.aris4autism_project.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.adapter.ProfileAdapter
import com.example.aris4autism_project.adapter.TabAdapter
import com.example.aris4autism_project.databinding.FragmentSingInBinding
import com.example.aris4autism_project.databinding.FragmentUserMainBinding
import com.example.aris4autism_project.model.ProfileModel
import com.google.android.material.tabs.TabLayout
import java.util.ArrayList

class UserMainFragment : Fragment() {

    lateinit var binding:FragmentUserMainBinding
    lateinit var includeData:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentUserMainBinding.inflate(inflater)

        binding.logoutToolbar.txIdMainLabel.text =resources.getString(R.string.editprofile)

        val sharedData =
            requireActivity().getSharedPreferences(Constant.TokenData, Context.MODE_PRIVATE)

        binding.logoutToolbar.idLogoutTx.setOnClickListener {

            var edit=sharedData.edit()
            edit.clear()
            edit.apply()
//                findNavController().popBackStack(R.id.userMainFragment,true)
//                findNavController().popBackStack(R.id.learnersFragment2,true)
//                  findNavController().popBackStack()

         findNavController().navigate(R.id.action_userMainFragment_to_singInFragment)

         Toast.makeText(requireActivity(), "Logout Successfully", Toast.LENGTH_SHORT).show()

        }

        binding.viewPager.setUserInputEnabled(false)

        includeData= activity?.findViewById(R.id.idDataLayout)!!
        includeData.visibility=View.VISIBLE

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Profile Details"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Address Details"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Change Password"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Manage Notification"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Support"))

        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = TabAdapter(requireActivity())
        adapter.addFragment(ProfileDetailsFragment(),"Profile Details")
        adapter.addFragment(AddressDetailsFragment(),"Address Details")
        adapter.addFragment(ChangePasswordeFragment(),"Change Password")
        adapter.addFragment(ManageNotificationFragment(),"Manage Notification")
        adapter.addFragment(SupportFragment(),"Support")

        binding.viewPager.adapter = adapter

//        viewPager.currentItem = 0

       binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab)
            {
                binding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        return binding.root
    }

}