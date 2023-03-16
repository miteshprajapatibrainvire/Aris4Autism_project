package com.example.aris4autism_project.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.BuySubscriptionAdapter
import com.example.aris4autism_project.databinding.FragmentSubscriptionPlanBinding
import com.example.aris4autism_project.viewmodel.ScriptionDataViewmodelFactory
import com.example.aris4autism_project.viewmodel.SubscriptionViewModelData

class SubscriptionPlanFragment : Fragment()
{

    lateinit var binding:FragmentSubscriptionPlanBinding
    lateinit var viewmodelSubscription:SubscriptionViewModelData

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentSubscriptionPlanBinding.inflate(inflater)

        viewmodelSubscription=ViewModelProvider(requireActivity(),
            ScriptionDataViewmodelFactory(requireContext())
        ).get(SubscriptionViewModelData::class.java)

        if(Utils.isOnline(requireContext()))
        {
//            viewmodelSubscription.getSubscriptionResultDetail(
//                "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE",
//                "Android",
//                "1"
//            )
        }
        else
        {
            Utils.InternetNotAvailableToast(requireContext())
        }
/*
        viewmodelSubscription.subscriptionResult.observe(requireActivity(),
            {
                when(it)
                {
                    is BaseResponse.Success->
                    {
                        Log.e("=subscription data=",it.data!!.data.shortDuration.toString())
                        binding.recySubscriptionId.layoutManager=LinearLayoutManager(requireContext())
                        binding.recySubscriptionId.adapter=BuySubscriptionAdapter(it.data.data.shortDuration)
                    }
                    is BaseResponse.Error->
                    {
                        Toast.makeText(requireContext(), it.msg.toString(), Toast.LENGTH_SHORT).show()
                    }
                    is BaseResponse.Loading->
                    {
                    }
                }
        })
        */
        return binding.root
    }
}