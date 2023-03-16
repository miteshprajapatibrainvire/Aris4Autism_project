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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.SubUserAdapter
import com.example.aris4autism_project.databinding.FragmentSubuserBinding
import com.example.aris4autism_project.model.responsemodel.ResponseData
import com.example.aris4autism_project.model.responsemodel.ResponseHandler
import com.example.aris4autism_project.model.subusermodel.SubUserResponseModel
import com.example.aris4autism_project.viewmodel.SubUserViewModel
import com.example.aris4autism_project.viewmodel.SubUserViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class SubuserFragment : Fragment() {

    lateinit var binding: FragmentSubuserBinding
    lateinit var buttonView: BottomNavigationView
    lateinit var includeData: View
    lateinit var viewModel: SubUserViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSubuserBinding.inflate(inflater, container, false)

        binding.constLayoutId.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("howWork","subuser")
            findNavController().navigate(R.id.action_subuserFragment2_to_howItWorksMainFragment,bundle)
        }

        //set visibility from bottom navigation
        buttonView = requireActivity().findViewById(R.id.bottom_navigation)
        buttonView.visibility = View.VISIBLE

        //set visibility from toptoolbar
        includeData = activity?.findViewById(R.id.idDataLayout)!!
        includeData.visibility = View.VISIBLE

        //set custom preloader dialogbox
        val constDialog = Constant.getDialogCustom(requireActivity())

        viewModel =
            ViewModelProvider(requireActivity(), SubUserViewModelFactory(requireActivity())).get(
                SubUserViewModel::class.java
            )


        if(Utils.isOnline(requireContext())) {
            //call subuserdetail data
            viewModel.getSubUserDetailsModel(

            )
        }
        else
        {
            Utils.InternetNotAvailableToast(requireContext())
        }

        //fetch subuser detail
        viewModel.subUserResult.observe(viewLifecycleOwner, Observer {state->

            when (state) {
                is ResponseHandler.Loading-> {

//                    Log.e("responseSubuser=", it.data!!.data.toString())
//                    binding.recySubUser.adapter = it.data?.data?.original?.let { it1 ->
//                        SubUserAdapter(
//                            it1.data)
//                    }
//                    binding.recySubUser.layoutManager = LinearLayoutManager(requireContext())
                    constDialog.show()
                }
                is ResponseHandler.OnFailed ->
                {
//                    Toast.makeText(requireContext(), it.msg.toString(), Toast.LENGTH_SHORT).show()
                   constDialog.cancel()
                }
                is ResponseHandler.OnSuccessResponse<ResponseData<SubUserResponseModel>?> ->
                {
                    binding.recySubUser.layoutManager=LinearLayoutManager(requireContext())
                    SubUserAdapter(state.response!!.data!!.original.data).also { binding.recySubUser.adapter = it }
                    Log.e("responseSubuser=",state.response!!.data!!.toString())
                    constDialog.cancel()
                }
            }
        })

        //when user backpress it will finish activity
        var callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity!!.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        return binding.root
    }


}