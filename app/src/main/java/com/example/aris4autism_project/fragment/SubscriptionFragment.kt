package com.example.aris4autism_project.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.SubscriptionAdapter
import com.example.aris4autism_project.databinding.FragmentSubscriptionBinding
import com.example.aris4autism_project.viewmodel.SubScriptionViewModel
import com.example.aris4autism_project.viewmodel.SubScriptionViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class SubscriptionFragment : Fragment() {

    lateinit var binding:FragmentSubscriptionBinding
    lateinit var buttonView: BottomNavigationView
    lateinit var includeData:View
    lateinit var viewModel:SubScriptionViewModel
    lateinit var arrayLearner:ArrayList<String>

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentSubscriptionBinding.inflate(inflater,container,false)

        //set visibility from bottom navigation
        buttonView=requireActivity().findViewById(R.id.bottom_navigation)
        buttonView.visibility=View.VISIBLE

        arrayLearner = ArrayList()

        //set current top navigation visibility
        includeData= activity?.findViewById(R.id.idDataLayout)!!
        includeData.visibility=View.VISIBLE

        binding.btnFloatingIcon.setOnClickListener {

            findNavController().navigate(R.id.action_subscriptionFragment2_to_subscriptionPlanFragment)
        }

        //initialize alert custom dialog box
        val const=Constant.getDialogCustom(requireContext())

        viewModel=ViewModelProvider(requireActivity(),SubScriptionViewModelFactory(requireContext())).get(SubScriptionViewModel::class.java)

        if(Utils.isOnline(requireContext())) {
            //call subuserdetail api
            viewModel.getSubUserDetails(
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE",
                "Android",
                "1"
            )
        }
        else
        {
            Utils.InternetNotAvailableToast(requireContext())
        }

        //fetch subscription details from server
        viewModel.resultSubscription.observe(requireActivity(),{
            when(it)
            {
                is BaseResponse.Success->
                {
                    binding.recySubscription.layoutManager=LinearLayoutManager(requireContext())
                    binding.recySubscription.adapter=SubscriptionAdapter(it.data!!.data.original.data)
                    const.cancel()
                }

                is BaseResponse.Error->{
                    const.cancel()
                }

                is BaseResponse.Loading->{
                    const.show()
                }
            }
        })

        //finish activity when user press back button
        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity!!.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        return binding.root
    }
}