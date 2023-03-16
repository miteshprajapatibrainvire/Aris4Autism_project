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
import com.example.aris4autism_project.adapter.OverViewAdapter
import com.example.aris4autism_project.databinding.FragmentOverviewBinding
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.model.overviewmodel.OverViewResponseModel
import com.example.aris4autism_project.viewmodel.OverViewViewModel
import com.example.aris4autism_project.viewmodel.OverViewViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class OverviewFragment : Fragment() {

    lateinit var binding:FragmentOverviewBinding
    lateinit var buttonView: BottomNavigationView
    lateinit var includeData:View
    lateinit var viewModel: OverViewViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(requireActivity(), OverViewViewModelFactory(requireActivity())).get(OverViewViewModel::class.java)

        viewModel.getOverViewDetails()
        var constDialog=Constant.getDialogCustom(requireActivity())

        binding.constLayoutId.setOnClickListener {
            var bundle=Bundle()
            bundle.putString("howWork","overview")
            findNavController().navigate(R.id.action_overviewFragment2_to_howItWorksMainFragment2,bundle)
        }

        //set visibility bottom navigation
        buttonView=requireActivity().findViewById(R.id.bottom_navigation)
        buttonView.visibility=View.VISIBLE

        //call alert dialogbox
        val const=Constant.getDialogCustom(requireActivity())

        //set visibility main toolbar
        includeData= activity?.findViewById(R.id.idDataLayout)!!
        includeData.visibility=View.VISIBLE

        //call overviewdetail

        /*if(Utils.isOnline(requireContext())) {
        }
        else
        {
            Utils.InternetNotAvailableToast(requireContext())
        }*/

        //get api response overviewdetails
        viewModel.resultOverView.observe(viewLifecycleOwner){state->
            when(state)
            {
                is ResponseHandler.Loading->{
                    constDialog.show()
                }
                is ResponseHandler.OnFailed->{
                    constDialog.cancel()
                }
                is ResponseHandler.OnSuccessResponse<ResponseData<OverViewResponseModel>?> ->{
                    Log.e("data=",state.response!!.data!!.toString())
                    constDialog.cancel()
                    state.response!!.data!!.let{
                       binding.recyOverView.layoutManager= LinearLayoutManager(requireContext())
                        //Log.e("overviewResponse=",it.toString())
                        OverViewAdapter(it.original.data).also { binding.recyOverView.adapter = it }
                    }
                }
                else ->
                {

                }
            }
        }

        //set callback overview fragment
        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity!!.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}