package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.SubUserAdapter
import com.example.aris4autism_project.databinding.FragmentSubuserDetailsBinding
import com.example.aris4autism_project.model.SubUserModel

class SubuserDetailsFragment : Fragment() {

    lateinit var binding:FragmentSubuserDetailsBinding
    lateinit var includeData:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSubuserDetailsBinding.inflate(layoutInflater,container,false)

        includeData= activity?.findViewById(R.id.idDataLayout)!!
        includeData.visibility=View.GONE

        val assignLearner: SubUserModel = requireArguments().getSerializable("assignLearner") as SubUserModel
        Log.e("assignLearner=",assignLearner.toString())
        binding.imgIdIconSub.setImageResource(assignLearner.idImgSubUser)
        binding.txIdNumber.text=assignLearner.NameSubUser

        binding.innerRecyId.layoutManager=LinearLayoutManager(requireActivity())
//        binding.innerRecyId.adapter=SubUserAdapter(assignLearner.assignModel)

        return binding.root
    }


}