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
import com.example.aris4autism_project.adapter.SubscriptionAdapter
import com.example.aris4autism_project.databinding.FragmentSubscriptionBinding
import com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse.SubScriptionResponseModel
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse.SubscriptionData
import com.example.aris4autism_project.viewmodel.SubScriptionViewModel
import com.example.aris4autism_project.viewmodel.SubScriptionViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class SubscriptionFragment : Fragment() {

    lateinit var binding:FragmentSubscriptionBinding
    lateinit var buttonView: BottomNavigationView
    lateinit var includeData:View
    lateinit var viewModel:SubScriptionViewModel
    lateinit var arrayLearner:ArrayList<String>

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentSubscriptionBinding.inflate(inflater,container,false)

        //set visibility from bottom navigation
        buttonView=requireActivity().findViewById(R.id.bottom_navigation)
        buttonView.visibility=View.VISIBLE

        arrayLearner = ArrayList()
        val constDialog=Constant.getDialogCustom(requireContext())

        //set current top navigation visibility
        includeData= activity?.findViewById(R.id.idDataLayout)!!
        includeData.visibility=View.VISIBLE

        binding.btnFloatingIcon.setOnClickListener {
            findNavController().navigate(R.id.action_subscriptionFragment2_to_subscriptionPlanFragment)
        }

        //initialize alert custom dialog box
        viewModel=ViewModelProvider(requireActivity(),SubScriptionViewModelFactory(requireContext())).get(SubScriptionViewModel::class.java)

        if(Utils.isOnline(requireContext())) {
            //call subuserdetail api
            viewModel.getSubUserDetails()

        }
        else
        {
            Utils.InternetNotAvailableToast(requireContext())
        }
        //fetch subscription details from server
        viewModel.resultSubscription.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseHandler.Loading -> {
                    constDialog.show()
                }

                is ResponseHandler.OnFailed -> {
                    constDialog.cancel()
                }
                is ResponseHandler.OnSuccessResponse<ResponseData<SubScriptionResponseModel>?> -> {
                    constDialog.cancel()
                    state.response?.let{
                        it.data.let{
                            it?.original.let {
                                it?.data.let{
                                        binding.recySubscription.layoutManager=LinearLayoutManager(requireContext())
                                        binding.recySubscription.adapter=SubscriptionAdapter(it as ArrayList<SubscriptionData>)
                                }
                            }
                        }
                    }
                }
            }
        }

        //finish activity when user press back button
        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity!!.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        return binding.root
    }
}