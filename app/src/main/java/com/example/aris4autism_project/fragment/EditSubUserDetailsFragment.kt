package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.AssignLearnerSubUserAdapter
import com.example.aris4autism_project.databinding.FragmentEditSubUserDetailsBinding
import com.example.aris4autism_project.viewmodel.SubUserViewModel
import com.example.aris4autism_project.viewmodel.SubUserViewModelFactory

class EditSubUserDetailsFragment : Fragment() {

    lateinit var binding:FragmentEditSubUserDetailsBinding
    lateinit var viewModel:SubUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentEditSubUserDetailsBinding.inflate(inflater)

        binding.idtopEditSubUserToolbar.idDetailPerson.visibility=View.GONE
        binding.idtopEditSubUserToolbar.txIdMainLabel.text=resources.getString(R.string.editsubuserdetail)
        binding.idtopEditSubUserToolbar.imgMainBack.setImageResource(R.drawable.close)

        viewModel=ViewModelProvider(requireActivity(),SubUserViewModelFactory(requireActivity())).get(SubUserViewModel::class.java)

        val subUserID=requireArguments().getString(getString(R.string.subuserid))
        Log.e("subuserId=",subUserID.toString())
        binding.idtopEditSubUserToolbar.imgMainBack.setOnClickListener {
            val bundle=Bundle()
            bundle.putString(getString(R.string.subuserid),subUserID)
            findNavController().navigate(R.id.action_editSubUserDetailsFragment_to_subuserDetailsFragment,bundle)
        }
        viewModel.getSubUserEditDetailResult(subUserID.toString(),"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE","Android","1")
        viewModel.subUserDetailResult.observe(requireActivity()) {

            when (it) {
                is BaseResponse.Success ->
                {
                    Log.e("response=", it.data!!.data.toString())
                    binding.idEdName.setText(it.data!!.data.name)
                    binding.idEdPhoneNo.setText(it.data!!.data.phoneNumber)
                    binding.idEdEmail.setText(it.data!!.data.email)
                    binding.idRecyEditUser.layoutManager = LinearLayoutManager(requireActivity())
                    binding.idRecyEditUser.adapter=AssignLearnerSubUserAdapter(it.data.data.learnerIds)
                }
                is BaseResponse.Error -> {

                }
                is BaseResponse.Loading -> {

                }
            }

        }


        return binding.root
    }


}