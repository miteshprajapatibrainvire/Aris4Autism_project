package com.example.aris4autism_project.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.SubUserAdapter
import com.example.aris4autism_project.adapter.SubUserInnerDetail
import com.example.aris4autism_project.databinding.FragmentSubuserDetailsBinding
import com.example.aris4autism_project.model.SubUserModel

class SubuserDetailsFragment : Fragment() {

    lateinit var binding:FragmentSubuserDetailsBinding
    lateinit var includeData:View
    lateinit var mainDetail:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSubuserDetailsBinding.inflate(layoutInflater,container,false)

        includeData= activity?.findViewById(R.id.idDataLayout)!!
        includeData.visibility=View.GONE

        binding.mainLayoutId.txIdMainLabel.text="SUBUSER DETAILS"
        binding.mainLayoutId.imgMainBack.setOnClickListener {
            findNavController().navigate(R.id.action_subuserDetailsFragment_to_subuserFragment2)
        }

        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_subuserDetailsFragment_to_subuserFragment2)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

        val assignLearner: SubUserModel = requireArguments().getSerializable("assignLearner") as SubUserModel
        Log.e("assignLearner=",assignLearner.toString())
        binding.imgIdIconSub.setImageResource(assignLearner.idImgSubUser)
        binding.txIdNumber.text=assignLearner.mobileNo
        binding.txidSubDetail.text=assignLearner.NameSubUser

        binding.innerRecyId.layoutManager=LinearLayoutManager(requireActivity())
        binding.innerRecyId.adapter=SubUserInnerDetail(assignLearner.assignModel)

        return binding.root

    }
}