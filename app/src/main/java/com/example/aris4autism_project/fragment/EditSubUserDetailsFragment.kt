package com.example.aris4autism_project.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
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
import com.example.aris4autism_project.model.responsemodel.ResponseData
import com.example.aris4autism_project.model.responsemodel.ResponseHandler
import com.example.aris4autism_project.viewmodel.SubUserViewModel
import com.example.aris4autism_project.viewmodel.SubUserViewModelFactory

class EditSubUserDetailsFragment : Fragment() {

    lateinit var binding: FragmentEditSubUserDetailsBinding
    lateinit var viewModel: SubUserViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditSubUserDetailsBinding.inflate(inflater)

        val uuid: String = requireArguments().getString("uuid").toString()
        val assignLearner = requireArguments().getSerializable("assignLearner")
        val bundle = Bundle()
        bundle.putSerializable("assignLearner", assignLearner)
        bundle.putSerializable("uuid", uuid)

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
        Log.e("subuserId=", subUserID.toString())

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

        viewModel.subUserDetailResult.observe(viewLifecycleOwner,{state->
            when(state)
            {
                is ResponseHandler.Loading->{

                }
                is ResponseHandler.OnFailed->{

                }
                is ResponseHandler.OnSuccessResponse<ResponseData<EditSubUserDetailsModel>?>->{
                    Log.e("editResponseData=",state.response?.data!!.toString())
                    binding.idEdName.setText(state.response.data!!.name)
                    binding.idEdPhoneNo.setText(state.response.data!!.phoneNumber)
                    binding.idEdEmail.setText(state.response.data!!.email)
                    binding.idRecyEditUser.layoutManager = LinearLayoutManager(requireActivity())
                    AssignLearnerSubUserAdapter(state.response.data!!.learnerIds).also { binding.idRecyEditUser.adapter = it }
                    constDialog.cancel()
                }
            }
        })

       /* viewModel.subUserDetailResult.observe(requireActivity()) {

            when (it) {

                is BaseResponse.Success -> {
                    Log.e("response=", it.data!!.data.toString())
                    binding.idEdName.setText(it.data.data.name)
                    binding.idEdPhoneNo.setText(it.data.data.phoneNumber)
                    binding.idEdEmail.setText(it.data.data.email)
                    binding.idRecyEditUser.layoutManager = LinearLayoutManager(requireActivity())
                    binding.idRecyEditUser.adapter =
                    AssignLearnerSubUserAdapter(it.data.data.learnerIds)
                    constDialog.cancel()
                }

                is BaseResponse.Error -> {
                    constDialog.cancel()
                    Toast.makeText(requireContext(), it.msg.toString(), Toast.LENGTH_SHORT).show()
                }

                is BaseResponse.Loading -> {
                    constDialog.show()
                }
            }
        }*/

        return binding.root
    }

}