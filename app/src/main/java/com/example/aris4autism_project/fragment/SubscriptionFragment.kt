package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.SubscriptionAdapter
import com.example.aris4autism_project.databinding.FragmentSubscriptionBinding
import com.example.aris4autism_project.databinding.FragmentSubuserBinding
import com.example.aris4autism_project.model.OverViewModel
import com.example.aris4autism_project.model.SubscriptionModel
import com.example.aris4autism_project.viewmodel.SubScriptionViewModel
import com.example.aris4autism_project.viewmodel.SubScriptionViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class SubscriptionFragment : Fragment() {


    lateinit var binding:FragmentSubscriptionBinding
    lateinit var buttonView: BottomNavigationView
    lateinit var includeData:View
    lateinit var viewModel:SubScriptionViewModel
    val arrayLearner= java.util.ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentSubscriptionBinding.inflate(inflater,container,false)
        buttonView=requireActivity().findViewById(R.id.bottom_navigation)
        buttonView.visibility=View.VISIBLE

        includeData= activity?.findViewById(R.id.idDataLayout)!!
        includeData.visibility=View.VISIBLE

        viewModel=ViewModelProvider(requireActivity(),SubScriptionViewModelFactory(requireContext())).get(SubScriptionViewModel::class.java)

        viewModel.getSubUserDetails("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE","Android","1")

        viewModel.resultSubscription.observe(requireActivity(),{
            when(it)
            {
                is BaseResponse.Success->{
                    Log.e("SubScription=",it.data!!.data.original.data.toString())
                }
                is BaseResponse.Error->{
                }
                is BaseResponse.Loading->{
                }
            }
        })



        /*
        var modelSubscription=ArrayList<SubscriptionModel>()

        modelSubscription.add(SubscriptionModel(231,234565,R.drawable.profileimg3,"Arlo","Male","27 Years 7 Months","DOB: 06-30-1995","#2130 - 6 Months Plan","09 Dec 2022 to 09 Jun 2023",true,"09 Dec 2022","09 Jun 2023"))
        modelSubscription.add(SubscriptionModel(232,241565,R.drawable.profileimggirl4,"Jin","Female","2 Years 1 Months","DOB: 12-07-1995","#2131 - 3 Months Plan","09 Dec 2022 to 09 Mar 2023",true,"09 Dec 2022","09 Mar 2023"))
        modelSubscription.add(SubscriptionModel(233,251232,R.drawable.profileimg4,"jack","Male","7 Years","DOB: 03-31-1995","#2132 - 3 Months Plan","09 Dec 2022 to 09 Mar 2023",false,"09 Dec 2022","09 Mar 2023"))
        modelSubscription.add(SubscriptionModel(233,261345,R.drawable.profile2img,"Jimmy","Male","27 Years 10 Months","DOB: 03-31-1995","#2132 - 4 Months Plan","09 Dec 2022 to 09 Mar 2023",true,"09 Dec 2022","09 Mar 2023"))

        for(i in modelSubscription.indices)
        {
            arrayLearner.add(modelSubscription[i].name)
        }
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, arrayLearner
        )

        binding.spGender.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, arg1: View?, position: Int, arg3: Long) {
                val item = parent.getItemAtPosition(position)
            }
        })
         */

        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity!!.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)


//        binding.spGender.setAdapter(adapter)
//        binding.recySubscription.layoutManager=LinearLayoutManager(requireActivity())
//        binding.recySubscription.adapter=SubscriptionAdapter(modelSubscription)

        return binding.root
    }


}