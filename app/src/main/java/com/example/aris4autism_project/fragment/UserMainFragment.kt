package com.example.aris4autism_project.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.adapter.TabAdapter
import com.example.aris4autism_project.databinding.FragmentUserMainBinding
import com.google.android.material.tabs.TabLayout

class UserMainFragment : Fragment() {

    lateinit var binding:FragmentUserMainBinding
    lateinit var includeData:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentUserMainBinding.inflate(inflater)

        //set mainlabel text in maintoolbar
        binding.logoutToolbar.txIdMainLabel.text =resources.getString(R.string.editprofile)

        //navigate learnerfragment
       binding.logoutToolbar.imgMainBack.setOnClickListener {
           findNavController().navigate(R.id.learnersFragment2)
       }

        //get token sharedpreferences
        val sharedData =
            requireActivity().getSharedPreferences(Constant.TokenData, Context.MODE_PRIVATE)

        //clear shared preferences
        binding.logoutToolbar.idLogoutTx.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedData.edit()
            editor.clear()
            editor.apply()

            //navigate usermainfragment to signinfragment
         findNavController().navigate(R.id.action_userMainFragment_to_singInFragment)

         Toast.makeText(requireActivity(), resources.getString(R.string.logoutsuccess), Toast.LENGTH_SHORT).show()

        }

        //disable viewpager swap false
        binding.viewPager.setUserInputEnabled(false)


        //set topmaintoolbar visibility
        includeData= activity?.findViewById(R.id.idDataLayout)!!
        includeData.visibility=View.VISIBLE

        //add tab label in tablayout
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.profiledetails))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.addressdetails))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.changepassword))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.managenotification))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.support))

        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = TabAdapter(requireActivity())
        //add fragments in tablayout details
        adapter.addFragment(ProfileDetailsFragment(),R.string.profiledetails.toString())
        adapter.addFragment(AddressDetailsFragment(),R.string.addressdetails.toString())
        adapter.addFragment(ChangePasswordeFragment(),R.string.changepassword.toString())
        adapter.addFragment(ManageNotificationFragment(),R.string.managenotification.toString())
        adapter.addFragment(SupportFragment(),R.string.support.toString())

        binding.viewPager.adapter = adapter
        binding.viewPager.offscreenPageLimit=4

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