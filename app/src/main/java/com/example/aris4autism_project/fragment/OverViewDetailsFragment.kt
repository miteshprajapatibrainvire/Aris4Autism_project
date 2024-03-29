package com.example.aris4autism_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.adapter.TabAdapter
import com.example.aris4autism_project.databinding.FragmentOverViewDetailsBinding
import com.example.aris4autism_project.model.overviewmodel.OverViewListData

import com.google.android.material.tabs.TabLayout


class OverViewDetailsFragment : Fragment() {

    lateinit var binding: FragmentOverViewDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverViewDetailsBinding.inflate(layoutInflater, container, false)

        //get overview detail data learner fragment
        val overViewModelDetails = requireArguments().getSerializable(resources.getString(R.string.overvw)) as OverViewListData
        Constant.editUserId=overViewModelDetails.uuid
        //set main label toolbar in text
        binding.mainLayoutId.txIdMainLabel.text = resources.getString(R.string.learneroverview)

        //navigate overviewdetailsfragment to overviewfragment
        binding.mainLayoutId.imgMainBack.setOnClickListener {
            findNavController().navigate(R.id.action_overViewDetailsFragment_to_overviewFragment2)
        }

        //maintoolbar image visibility gone
        binding.mainLayoutId.idDetailPerson.visibility = View.GONE

        //maintoolbar visibility gone
        val view = requireActivity().findViewById<View>(R.id.idDataLayout)
        view.visibility = View.GONE

        binding.viewPager.setUserInputEnabled(false)

        //set tab layout visibility
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(resources.getString(R.string.info)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(resources.getString(R.string.diagnosis)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(resources.getString(R.string.overView)))

        //set backpress overviewdetailsfragment to overviewfragment
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_overViewDetailsFragment_to_overviewFragment2)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = TabAdapter(requireActivity())
        //add fragments in tablayout details
        adapter.addFragment(InfoOverViewFragment(),resources.getString(R.string.info))
        adapter.addFragment(DiagnosisOverViewFragment(),resources.getString(R.string.diagnosis))
        adapter.addFragment(LearnerOverViewFragment(),resources.getString(R.string.overView))

        binding.viewPager.adapter = adapter

        binding.viewPager.setOffscreenPageLimit(2)

        //set tabselectedlistener for swap tablayouts in fragment
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        return binding.root
    }
}