package com.example.aris4autism_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.adapter.SubUserInnerDetail
import com.example.aris4autism_project.databinding.FragmentSubuserDetailsBinding
import com.example.aris4autism_project.model.DataXXXXXXX
import com.example.aris4autism_project.viewmodel.SubUserInnerViewModel
import com.example.aris4autism_project.viewmodel.SubUserInnverViewModelFactory

class SubuserDetailsFragment : Fragment() {

    lateinit var binding: FragmentSubuserDetailsBinding
    lateinit var includeData: View
    lateinit var viewModel: SubUserInnerViewModel
    lateinit var   assignLearner: DataXXXXXXX

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubuserDetailsBinding.inflate(layoutInflater, container, false)

        //set visibility for maintoolbar
        includeData = activity?.findViewById(R.id.idDataLayout)!!
        includeData.visibility = View.GONE

        //call custom preloading dialogbox
        val const = Constant.getDialogCustom(requireContext())

        viewModel = ViewModelProvider(
            requireActivity(),
            SubUserInnverViewModelFactory(requireContext())
        ).get(SubUserInnerViewModel::class.java)

        //set text label for main toolbar
        binding.mainLayoutId.txIdMainLabel.text = "SUBUSER DETAILS"

        //navigate when  subuserfragment
        binding.mainLayoutId.imgMainBack.setOnClickListener {
            findNavController().navigate(R.id.subuserFragment2)
        }

//        binding.mainLayoutId.imgMainBack.setOnClickListener
//        {
//            findNavController().navigate(R.id.action_subuserDetailsFragment_to_subuserFragment2)
//        }

        //navigate subuserdetailsfragment to subuserfragment
        val callback = object : OnBackPressedCallback(true)
        {
            override fun handleOnBackPressed()
            {
                findNavController().navigate(R.id.action_subuserDetailsFragment_to_subuserFragment2)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        //get argument data from subuserfragment to subuserdetailfragment
        assignLearner =requireArguments().getSerializable(resources.getString(R.string.assignLearner)) as DataXXXXXXX

        //navigate subuserdetailfragment to editsubuserdetailsfragment
        binding.mainLayoutId.idDetailPerson.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("subUserId", assignLearner.uuid)
            bundle.putSerializable("assignLearner",assignLearner)
            findNavController().navigate(R.id.action_subuserDetailsFragment_to_editSubUserDetailsFragment,bundle)
        }

        //call subuserinnerdetails api
        viewModel.getSubUserInnerDetails(
            assignLearner.uuid,
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE",
            "Android",
            "1"
        )

        //get subuserinnerresult api data
        viewModel.subUserInnerResult.observe(requireActivity(), {
            when (it) {
                is BaseResponse.Success -> {

                    binding.txidSubDetail.text = assignLearner.name
                    binding.txIdNumber.text = it.data!!.data.phone_number
                    binding.txidEmail.text = assignLearner.email
                    Glide.with(requireActivity())
                        .load(it.data.data.get_profile_icon.icon_url)
                        .into(binding.imgIdIconSub)
                    binding.innerRecyId.layoutManager = LinearLayoutManager(requireActivity())
                    binding.innerRecyId.adapter = SubUserInnerDetail(it.data.data.learner_ids)
                    const.cancel()

                }

                is BaseResponse.Loading ->
                {
                    const.show()
                }

                is BaseResponse.Error ->
                {
                    Toast.makeText(requireContext(), it.msg.toString(), Toast.LENGTH_SHORT).show()
                    const.cancel()
                }

            }
        })

        //load image in glide library
        Glide.with(requireContext())
            .load(assignLearner.getProfileIcon.iconUrl)
            .into(binding.imgIdIconSub)

        return binding.root

    }


}