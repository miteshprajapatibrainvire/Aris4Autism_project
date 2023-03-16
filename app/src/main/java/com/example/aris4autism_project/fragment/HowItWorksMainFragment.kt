package com.example.aris4autism_project.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.TabAdapter
import com.example.aris4autism_project.databinding.FragmentHowItWorksMainBinding
import com.example.aris4autism_project.model.howitworkmodel.VideoLearners
import com.example.aris4autism_project.model.howitworkmodel.VideoOverview
import com.example.aris4autism_project.model.howitworkmodel.VideoSubuser
import com.example.aris4autism_project.model.howitworkmodel.YoutubeVideoResponseModel
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.viewmodel.HowItWorksViewModel
import com.example.aris4autism_project.viewmodel.HowItWorksViewModelFactory
import com.google.android.material.tabs.TabLayout

class HowItWorksMainFragment : Fragment() {

    lateinit var binding:FragmentHowItWorksMainBinding
    var mainLearnerWork:ArrayList<VideoLearners> ?= null
    var learnerWorkArray:ArrayList<VideoLearners> = ArrayList<VideoLearners>()
    var subuserWorkArray:ArrayList<VideoSubuser> = ArrayList<VideoSubuser>()
    var overviewWorkArray:ArrayList<VideoOverview> = ArrayList<VideoOverview>()
    var bundle=Bundle()
    lateinit var viewmodel: HowItWorksViewModel
    var booleanArrayAttemp:Boolean=true

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        binding=FragmentHowItWorksMainBinding.inflate(inflater)
        mainLearnerWork= ArrayList()

        binding.topToolbarHowitWork.txIdMainLabel.text=resources.getString(R.string.howitwork)
        binding.topToolbarHowitWork.idDetailPerson.visibility=View.GONE
        binding.topToolbarHowitWork.imgMainBack.setOnClickListener {
            findNavController().navigate(R.id.action_howItWorksMainFragment_to_learnersFragment2)
        }

        viewmodel =
            ViewModelProvider(requireActivity(), HowItWorksViewModelFactory(requireActivity())).get(
                HowItWorksViewModel::class.java
            )

        if (Utils.isOnline(requireContext())) {
            viewmodel.getYoutubeVideosResponse()
        } else {
            Utils.InternetNotAvailableToast(requireContext())
        }
        binding.tabLayoutHowitWork.addTab(binding.tabLayoutHowitWork.newTab().setText(resources.getString(R.string.learner)))
        binding.tabLayoutHowitWork.addTab(binding.tabLayoutHowitWork.newTab().setText(resources.getString(R.string.subuser)))
        binding.tabLayoutHowitWork.addTab(binding.tabLayoutHowitWork.newTab().setText(resources.getString(R.string.overview)))
        binding.tabViewpager.setUserInputEnabled(false)
        binding.tabLayoutHowitWork.tabGravity = TabLayout.GRAVITY_FILL
        val tabAdapter=TabAdapter(requireActivity())
        viewmodel.resultHowItWork.observe(viewLifecycleOwner,{state->
            when(state)
            {
                is ResponseHandler.Loading->{

                }
                is ResponseHandler.OnFailed->{

                }
                is ResponseHandler.OnSuccessResponse<ResponseData<YoutubeVideoResponseModel>?>->{
                    if(booleanArrayAttemp) {
                        Log.e("howitworkmainResponse=", state.response?.data.toString())
                        HowLearnerWorksFragment.learnerWorkArray.addAll(state.response?.data!!.learners)
                        HowSubUserWorksFragment.subuserWorkArray.addAll(state.response?.data!!.subusers)
                        HowOverviewWorksFragment.overviewWorkArray.addAll(state.response?.data!!.overview)
                        tabAdapter.addFragment(HowLearnerWorksFragment(resources.getString(R.string.lovercaselearner)),resources.getString(R.string.learner))
                        tabAdapter.addFragment(HowSubUserWorksFragment(resources.getString(R.string.lovercasesubuser)),resources.getString(R.string.subuser))
                        tabAdapter.addFragment(HowOverviewWorksFragment(resources.getString(R.string.lovercaseoverview)),resources.getString(R.string.overview))
                        binding.tabViewpager.adapter=tabAdapter
                        binding.tabViewpager.offscreenPageLimit = 2
                        booleanArrayAttemp=false
                    }
                }
            }
        })



        try {

            val dataString = requireArguments().getString(resources.getString(R.string.howitworkstr))

            if (dataString.toString().equals(resources.getString(R.string.lovercaselearner), true)) {
                binding.tabViewpager.currentItem = 0
                binding.tabLayoutHowitWork.getTabAt(0)?.select()
            }

            if (dataString.toString().equals(resources.getString(R.string.lovercasesubuser), true)) {
                binding.tabViewpager.currentItem = 1
                binding.tabLayoutHowitWork.getTabAt(1)?.select()
            }

            if (dataString.toString().equals(resources.getString(R.string.lovercaseoverview), true)) {
                binding.tabViewpager.currentItem = 2
                binding.tabLayoutHowitWork.getTabAt(2)?.select()
            }
        }
        catch(e:Exception)
        {
            Log.e("Exception=",e.toString())
        }

        binding.tabLayoutHowitWork.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab)
            {
                binding.tabViewpager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}

        })

        return binding.root
    }
}