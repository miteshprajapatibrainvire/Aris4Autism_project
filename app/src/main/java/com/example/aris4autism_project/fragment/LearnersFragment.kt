package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.LearnerAdapter
import com.example.aris4autism_project.databinding.FragmentLearnersBinding
import com.example.aris4autism_project.viewmodel.LearnerViewModel
import com.example.aris4autism_project.viewmodel.LearnerViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class LearnersFragment : Fragment() {

    lateinit var binding:FragmentLearnersBinding
    lateinit var buttonView: BottomNavigationView
    lateinit var includeData:View
    lateinit var viewModel:LearnerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLearnersBinding.inflate(layoutInflater, container, false)
        buttonView=requireActivity().findViewById(R.id.bottom_navigation)
        buttonView.visibility=View.VISIBLE
        includeData= activity?.findViewById(R.id.idDataLayout)!!
        includeData.visibility=View.VISIBLE

        viewModel=ViewModelProvider(requireActivity(), LearnerViewModelFactory(requireActivity())).get(
            LearnerViewModel::class.java)

        viewModel.getLearnerList("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE","Android","1")

        viewModel.resultLearner.observe(requireActivity(),{
            when (it) {
                is BaseResponse.Success -> {
//                    Toast.makeText(requireContext(), "Login Successfully", Toast.LENGTH_SHORT).show()

                   Log.e("learnerlist=",it.data!!.data.original.data[0].toString())
                    binding.recyLearnId.layoutManager=LinearLayoutManager(requireActivity())
                    binding.recyLearnId.adapter=LearnerAdapter(requireActivity(),it.data!!.data.original.data)

                   stopLoading()

                }
                is BaseResponse.Loading -> {
                   showLoading()
                }
                is BaseResponse.Error -> {
                    stopLoading()
                }
                else -> {

                }
            }
        })

       /*
        var modelLearner=ArrayList<LearnerModel>()

        var dataArr=ArrayList<DiagnosisModel>()
        dataArr.add(DiagnosisModel("Dev"))
        dataArr.add(DiagnosisModel("Backend"))
        dataArr.add(DiagnosisModel("Testing"))

        var dataArr2=ArrayList<DiagnosisModel>()
        dataArr2.add(DiagnosisModel("Dev"))
        dataArr2.add(DiagnosisModel("Backend"))
        dataArr2.add(DiagnosisModel("Testing"))
        dataArr2.add(DiagnosisModel("Anxiety"))

        var dataArr3=ArrayList<DiagnosisModel>()
        dataArr3.add(DiagnosisModel("Dev"))
        dataArr3.add(DiagnosisModel("Backend"))
        dataArr3.add(DiagnosisModel("Testing"))

        modelLearner.add(LearnerModel(231,R.drawable.profileimg3,"Mitesh","Male","27 Years 7 Months","DOB: 06-30-1995","#2130 - 6 Months Plan","09 Dec 2022 to 09 Jun 2023",true,"09 Dec 2022","09 Jun 2023",dataArr))
        modelLearner.add(LearnerModel(232,R.drawable.profileimggirl4,"Jin","Male","2 Years 1 Months","DOB: 12-07-1995","#2131 - 3 Months Plan","09 Dec 2022 to 09 Mar 2023",true,"09 Dec 2022","09 Mar 2023",dataArr2))
        modelLearner.add(LearnerModel(233,R.drawable.profileimg4,"jack","Male","27 Years 10 Months","DOB: 03-31-1995","#2132 - 3 Months Plan","09 Dec 2022 to 09 Mar 2023",false,"09 Dec 2022","09 Mar 2023",dataArr3))

        binding.recyLearnId.layoutManager=LinearLayoutManager(requireActivity())
        binding.recyLearnId.adapter=LearnerAdapter(requireActivity(),modelLearner)
    */

        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity!!.finish()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

        return binding.root
    }

    fun showLoading()
    {
        binding.prgbarLearner.visibility=View.VISIBLE
    }

    fun stopLoading()
    {
        binding.prgbarLearner.visibility=View.GONE
    }

}