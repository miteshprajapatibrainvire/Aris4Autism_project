package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.TabAdapter
import com.example.aris4autism_project.databinding.FragmentHowItWorksMainBinding
import com.example.aris4autism_project.model.DataXXXXXXXXXXXXXXXXXXXXXXXX
import com.example.aris4autism_project.model.LearnerXXXXXX
import com.example.aris4autism_project.viewmodel.HowItWorksViewModel
import com.example.aris4autism_project.viewmodel.HowItWorksViewModelFactory
import com.google.android.material.tabs.TabLayout

class HowItWorksMainFragment : Fragment() {

    lateinit var binding:FragmentHowItWorksMainBinding
    lateinit var viewmodel:HowItWorksViewModel
    var mainArrayList:ArrayList<DataXXXXXXXXXXXXXXXXXXXXXXXX> ?=null
    var mainLearnerWork:ArrayList<LearnerXXXXXX> ?= null
    var bundle=Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        binding=FragmentHowItWorksMainBinding.inflate(inflater)
        mainArrayList=ArrayList()
        mainLearnerWork= ArrayList()

        binding.topToolbarHowitWork.txIdMainLabel.text=resources.getString(R.string.howitwork)
        binding.topToolbarHowitWork.idDetailPerson.visibility=View.GONE
        binding.topToolbarHowitWork.imgMainBack.setOnClickListener {
            findNavController().navigate(R.id.action_howItWorksMainFragment_to_learnersFragment2)
        }

        viewmodel=ViewModelProvider(requireActivity(),HowItWorksViewModelFactory(requireActivity())).get(HowItWorksViewModel::class.java)
        viewmodel.getYoutubeVideosResponse(
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE",
            "Android",
            "1"
        )

        viewmodel.resultHowItWork.observe(requireActivity(),{
            when(it)
            {
                is BaseResponse.Success->
                {
                    mainArrayList!!.add(it.data!!.data)
                    mainLearnerWork!!.addAll(it.data.data.learners)
//                    bundle.putString("passData","jsonData")
//                    bundle.putSerializable("bundleArrayLearner",it.data.data.learners)
                }

                is BaseResponse.Loading->{

                }

                is BaseResponse.Error->{

                }
            }
        })

        binding.tabLayoutHowitWork.addTab(binding.tabLayoutHowitWork.newTab().setText("Learners"))
        binding.tabLayoutHowitWork.addTab(binding.tabLayoutHowitWork.newTab().setText("Subuser"))
        binding.tabLayoutHowitWork.addTab(binding.tabLayoutHowitWork.newTab().setText("Overview"))
        binding.tabViewpager.setUserInputEnabled(false)
        binding.tabLayoutHowitWork.tabGravity = TabLayout.GRAVITY_FILL
        val tabAdapter=TabAdapter(requireActivity())
        tabAdapter.addFragment(HowLearnerWorksFragment(),"Learners")
        tabAdapter.addFragment(HowSubUserWorksFragment(),"Subuser")
        tabAdapter.addFragment(HowOverviewWorksFragment(),"Overview")

        binding.tabViewpager.adapter=tabAdapter

        val dataString=requireArguments().getString("howWork")


        binding.tabViewpager.offscreenPageLimit = 2
//        Log.e("StringRecord=",dataString.toString())
        if(dataString.toString().equals("learner",true))
        {
             binding.tabViewpager.currentItem=0
             binding.tabLayoutHowitWork.getTabAt(0)
            Log.e("learner=",dataString.toString())
        }

        if(dataString.toString().equals("subuser",true))
        {
            binding.tabViewpager.currentItem=1
            binding.tabLayoutHowitWork.getTabAt(1)
            Log.e("subser=",dataString.toString())
        }

        if(dataString.toString().equals("overview",true))
        {
            binding.tabViewpager.currentItem=2
            binding.tabLayoutHowitWork.getTabAt(2)
            Log.e("overview=",dataString.toString())
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