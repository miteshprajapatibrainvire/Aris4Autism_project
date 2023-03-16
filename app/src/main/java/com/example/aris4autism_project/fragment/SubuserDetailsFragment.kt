package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.adapter.SubUserInnerDetail
import com.example.aris4autism_project.databinding.FragmentSubuserDetailsBinding
import com.example.aris4autism_project.model.subuserinnermodel.SubUserModelInnerResponse
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.model.subusermodel.SubUserData
import com.example.aris4autism_project.viewmodel.SubUserInnerViewModel
import com.example.aris4autism_project.viewmodel.SubUserInnverViewModelFactory

class SubuserDetailsFragment : Fragment() {

    lateinit var binding: FragmentSubuserDetailsBinding
    lateinit var includeData: View
    lateinit var viewModel: SubUserInnerViewModel
    lateinit var   assignLearner: SubUserData

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
        assignLearner =requireArguments().getSerializable(resources.getString(R.string.assignLearner)) as SubUserData

        //navigate subuserdetailfragment to editsubuserdetailsfragment
        binding.mainLayoutId.idDetailPerson.setOnClickListener {
            val bundle=Bundle()
            bundle.putString(resources.getString(R.string.subUserId), assignLearner.uuid)
            bundle.putSerializable(resources.getString(R.string.assignLearner),assignLearner)
            findNavController().navigate(R.id.action_subuserDetailsFragment_to_editSubUserDetailsFragment,bundle)
        }

        //call subuserinnerdetails api
        viewModel.getSubUserInnerDetails(
            assignLearner.uuid
        )

        //get subuserinnerresult api data
        viewModel.subUserInnerResult.observe(viewLifecycleOwner,{state->
            when(state)
            {
                is ResponseHandler.Loading->{
                    const.show()
                }
                is ResponseHandler.OnFailed->{
                    const.cancel()
                }
                is ResponseHandler.OnSuccessResponse<ResponseData<SubUserModelInnerResponse>?>->
                {
                    const.cancel()
                    Log.e("responseSubUserDetails=", state.response?.data?.email.toString())
                    binding.txidSubDetail.text = assignLearner.name
                    binding.txIdNumber.text = state.response?.data!!.phone_number
                    binding.txidEmail.text = assignLearner.email
                    Glide.with(requireActivity())
                        .load(state.response?.data!!.get_profile_icon.icon_url)
                        .into(binding.imgIdIconSub)
                    binding.innerRecyId.layoutManager = LinearLayoutManager(requireActivity())
                    binding.innerRecyId.adapter = SubUserInnerDetail(state.response?.data!!.learner_ids)
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