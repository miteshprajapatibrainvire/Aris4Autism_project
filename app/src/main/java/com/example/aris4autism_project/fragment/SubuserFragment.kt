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
import com.example.aris4autism_project.adapter.SubUserAdapter
import com.example.aris4autism_project.databinding.FragmentSubuserBinding
import com.example.aris4autism_project.model.SubUserModel
import com.example.aris4autism_project.viewmodel.SubUserViewModel
import com.example.aris4autism_project.viewmodel.SubUserViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class SubuserFragment : Fragment() {

    lateinit var binding: FragmentSubuserBinding
    lateinit var buttonView: BottomNavigationView
    lateinit var includeData: View
    lateinit var subUserList: ArrayList<SubUserModel>
    lateinit var viewModel: SubUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubuserBinding.inflate(inflater, container, false)
        buttonView = requireActivity().findViewById(R.id.bottom_navigation)
        buttonView.visibility = View.VISIBLE

        includeData = activity?.findViewById(R.id.idDataLayout)!!
        includeData.visibility = View.VISIBLE

        viewModel =
            ViewModelProvider(requireActivity(), SubUserViewModelFactory(requireActivity())).get(
                SubUserViewModel::class.java
            )

        viewModel.getSubUserDetailsModel("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE","Android","1")
        viewModel.subUserResult.observe(requireActivity(),{

            when(it)
            {
                is BaseResponse.Success->{
                    showLoading()
                    Log.e("responseSubuser=",it.data!!.data.toString())
                    binding.recySubUser.adapter=SubUserAdapter(it.data!!.data.original.data)
                    binding.recySubUser.layoutManager=LinearLayoutManager(requireContext())
                    stopLoading()
                }
                is BaseResponse.Error->{
                    stopLoading()
                }
                is BaseResponse.Loading->{
                    stopLoading()
                }
            }

        })

        /*
       subUserList=ArrayList()
        var assignModel=ArrayList<LearnerModel>()
        var assignModel2=ArrayList<LearnerModel>()
        var assignModel3=ArrayList<LearnerModel>()

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

        assignModel.add(LearnerModel(231,R.drawable.profileimg3,"Mitesh","Male","27 Years 7 Months","DOB: 06-30-1995","6 Months Plan","09 Dec 2022 to 09 Jun 2023",true,"09 Dec 2022","09 Jun 2023",dataArr))
        assignModel.add(LearnerModel(232,R.drawable.profilegirlimg2,"sindy","Female","23 Years 2 Months","DOB: 06-30-1995","4 Months Plan","09 Dec 2022 to 09 Jun 2023",true,"09 Dec 2022","09 Jun 2023",dataArr))

        assignModel2.add(LearnerModel(233,R.drawable.profileimggirl4,"Jin","Female","2 Years 1 Months","DOB: 12-07-1995","3 Months Plan","09 Dec 2022 to 09 Mar 2023",false,"09 Dec 2022","09 Mar 2023",dataArr2))
        assignModel2.add(LearnerModel(234,R.drawable.profileimg1,"Jacky","Male","1 Years 3 Months","DOB: 12-07-1995","4 Months Plan","09 Dec 2022 to 09 Mar 2023",true,"09 Dec 2022","09 Mar 2023",dataArr2))

        assignModel3.add(LearnerModel(235,R.drawable.profileimg4,"jack","Male","27 Years 10 Months","DOB: 03-31-1995","4 Months Plan","09 Dec 2022 to 09 Mar 2023",false,"09 Dec 2022","09 Mar 2023",dataArr3))
        assignModel3.add(LearnerModel(236,R.drawable.profilegirlimg3,"sera","Female","3 Years 3 Months","DOB: 03-31-1995","2 Months Plan","09 Dec 2022 to 09 Mar 2023",true,"09 Dec 2022","09 Mar 2023",dataArr3))


        val subUserModel=SubUserModel(R.drawable.profileimg1,"Jack","jack5@gmail.com","9565545652","Verified","Assigned learner",
            arrayListOf("jack","makro","jin"),
            assignModel
        )
        val subUserModel2=SubUserModel(R.drawable.profilegirlimg2,"Jin","Jin55@gmail.com","9090656568","Pending","Assigned learner",
            arrayListOf("semi","arlo","rina"),
            assignModel2
        )
        val subUserModel3=SubUserModel(R.drawable.profilegirlimg3,"arlo","arlo65@gmail.com","8659645658","Verified","Assigned learner",
            arrayListOf("jen","henk","sindy"),
            assignModel3
        )

        subUserList.add(subUserModel)
        subUserList.add(subUserModel2)
        subUserList.add(subUserModel3)

        binding.recySubUser.adapter=SubUserAdapter(subUserList)
        binding.recySubUser.layoutManager=LinearLayoutManager(requireContext())
        */

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity!!.finish()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

        return binding.root

    }

    fun showLoading()
    {
        binding.prgbarSubuser.visibility=View.VISIBLE
    }

    fun stopLoading()
    {
        binding.prgbarSubuser.visibility=View.GONE
    }
}