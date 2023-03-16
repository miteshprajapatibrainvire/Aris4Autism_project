package com.example.aris4autism_project.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.BuySubscriptionAdapter
import com.example.aris4autism_project.databinding.FragmentSubscriptionPlanBinding
import com.example.aris4autism_project.model.subscriptionplanmodel.SubscriptionPlanListModelResponse
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.viewmodel.ScriptionDataViewmodelFactory
import com.example.aris4autism_project.viewmodel.SubscriptionViewModelData

class SubscriptionPlanFragment : Fragment()
{

    lateinit var binding:FragmentSubscriptionPlanBinding
    lateinit var viewmodelSubscription:SubscriptionViewModelData

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentSubscriptionPlanBinding.inflate(inflater)

        val constDialog=Constant.getDialogCustom(requireContext())
        viewmodelSubscription=ViewModelProvider(requireActivity(),
            ScriptionDataViewmodelFactory(requireContext())
        ).get(SubscriptionViewModelData::class.java)

        if(Utils.isOnline(requireContext()))
        {
            viewmodelSubscription.getSubscriptionResultDetail()
        }
        else
        {
            Utils.InternetNotAvailableToast(requireContext())
        }

        viewmodelSubscription.subscriptionResult.observe(viewLifecycleOwner,{state->

            when(state)
            {
                is ResponseHandler.Loading->{
                    constDialog.show()
                }
                is ResponseHandler.OnFailed->{
                    constDialog.cancel()
                }
                is ResponseHandler.OnSuccessResponse<ResponseData<SubscriptionPlanListModelResponse>?>->
                {
                    constDialog.cancel()
                    binding.recySubscriptionId.layoutManager=LinearLayoutManager(requireContext())
                    binding.recySubscriptionId.adapter=BuySubscriptionAdapter(state.response!!.data!!.shortDuration)
                }
            }
        })

        return binding.root
    }
}