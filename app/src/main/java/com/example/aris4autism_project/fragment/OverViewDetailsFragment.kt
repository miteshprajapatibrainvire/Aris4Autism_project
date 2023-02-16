package com.example.aris4autism_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.TabAdapter
import com.example.aris4autism_project.databinding.FragmentOverViewDetailsBinding
import com.example.aris4autism_project.model.DataXXXXXXXXXXX

import com.google.android.material.tabs.TabLayout


class OverViewDetailsFragment : Fragment() {

    lateinit var binding:FragmentOverViewDetailsBinding
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentOverViewDetailsBinding.inflate(layoutInflater,container,false)

        val overViewModelDetails=requireArguments().getSerializable(resources.getString(R.string.overvw)) as DataXXXXXXXXXXX

        binding.mainLayoutId.txIdMainLabel.text = resources.getString(R.string.learneroverview)

        binding.mainLayoutId.imgMainBack.setOnClickListener {
            findNavController().navigate(R.id.action_overViewDetailsFragment_to_overviewFragment2)
        }

        binding.mainLayoutId.idDetailPerson.visibility=View.GONE

        val view=requireActivity().findViewById<View>(R.id.idDataLayout)
        view.visibility=View.GONE

        tabLayout=binding.tabLayout
        viewPager=binding.viewPager
        binding.viewPager.setUserInputEnabled(false)

        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.info)))
        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.diagnosis)))
        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.overView)))

        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_overViewDetailsFragment_to_overviewFragment2)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = TabAdapter(requireActivity())
        adapter.addFragment(InfoOverViewFragment(overViewModelDetails),"Info")
        adapter.addFragment(DiagnosisOverViewFragment(overViewModelDetails),"Diagnosis")
        adapter.addFragment(LearnerOverViewFragment(),"Overview")

        viewPager.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab)
            {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}

        })

        return binding.root
    }
}