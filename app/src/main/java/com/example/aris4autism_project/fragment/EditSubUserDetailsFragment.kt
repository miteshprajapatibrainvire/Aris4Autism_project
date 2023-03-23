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
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.AssignLearnerSubUserAdapter
import com.example.aris4autism_project.databinding.FragmentEditSubUserDetailsBinding
import com.example.aris4autism_project.model.editsubuserdetailmodel.EditSubUserDetailsModel
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.viewmodel.SubUserViewModel
import com.example.aris4autism_project.viewmodel.SubUserViewModelFactory

@Suppress("DEPRECATION")
class EditSubUserDetailsFragment : Fragment() {

    lateinit var binding: FragmentEditSubUserDetailsBinding
    lateinit var viewModel: SubUserViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditSubUserDetailsBinding.inflate(inflater)

        val uuid: String = requireArguments().getString(resources.getString(R.string.uuid)).toString()
        val assignLearner = requireArguments().getSerializable(resources.getString(R.string.assignLearnerstr))
        val bundle = Bundle()
        bundle.putSerializable(resources.getString(R.string.assignLearnerstr), assignLearner)
        bundle.putSerializable(resources.getString(R.string.uuid), uuid)

        val constDialog = Constant.getDialogCustom(requireContext())
        //set visibility for deail persion icon
        binding.idtopEditSubUserToolbar.idDetailPerson.visibility = View.GONE
        //set text for main label
        binding.idtopEditSubUserToolbar.txIdMainLabel.text =
            resources.getString(R.string.editsubuserdetail)

        binding.idtopEditSubUserToolbar.idDetailPerson.setOnClickListener {
            findNavController().navigate(
                R.id.action_editSubUserDetailsFragment_to_subuserDetailsFragment,
                bundle
            )
        }

        //backpress  from learnerleatails fragment to learner fragment
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(
                    R.id.action_editSubUserDetailsFragment_to_subuserDetailsFragment,
                    bundle
                )
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        //set image for edit sub user
        binding.idtopEditSubUserToolbar.imgMainBack.setImageResource(R.drawable.close)

        //call viewmodel
        viewModel =
            ViewModelProvider(requireActivity(), SubUserViewModelFactory(requireActivity())).get(
                SubUserViewModel::class.java
            )

        //get userid from subuser fragment
        val subUserID = requireArguments().getString(getString(R.string.subuserid))
        //navigate subuser details fragment
        binding.idtopEditSubUserToolbar.imgMainBack.setOnClickListener {

            findNavController().navigate(
                R.id.action_editSubUserDetailsFragment_to_subuserDetailsFragment,
                bundle
            )

        }

        //fetch subuser details
        if(Utils.isOnline(requireContext())) {
            viewModel.getSubUserEditDetailResult(
                subUserID.toString()
            )
        }
        else
        {
            Utils.InternetNotAvailableToast(requireContext())
        }
        //get api sub edit detail result response

        viewModel.subUserDetailResult.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseHandler.Loading -> {

                }
                is ResponseHandler.OnFailed -> {

                }
                is ResponseHandler.OnSuccessResponse<ResponseData<EditSubUserDetailsModel>?> -> {
                    state.response?.data.let {
                        binding.idEdName.setText(it?.name)
                        binding.idEdPhoneNo.setText(it?.phoneNumber)
                        binding.idEdEmail.setText(it?.email)
                        binding.idRecyEditUser.layoutManager = LinearLayoutManager(requireActivity())

                        AssignLearnerSubUserAdapter(it!!.learnerIds).also {
                            binding.idRecyEditUser.adapter = it
                        }
                        constDialog.cancel()
                    }
                }
            }
        }

        return binding.root
    }

}