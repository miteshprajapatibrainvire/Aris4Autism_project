package com.example.aris4autism_project.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.adapter.LearnerAdapter
import com.example.aris4autism_project.databinding.FragmentLearnersBinding
import com.example.aris4autism_project.model.learnermodel.LearnerReponseModel
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse.SubscriptionData
import com.example.aris4autism_project.viewmodel.LearnerViewModel
import com.example.aris4autism_project.viewmodel.LearnerViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class LearnersFragment : Fragment() {

    lateinit var binding: FragmentLearnersBinding
    lateinit var buttonView: BottomNavigationView
    lateinit var includeData: View
    lateinit var viewModel: LearnerViewModel
    lateinit var subScriptionArray: ArrayList<SubscriptionData>

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLearnersBinding.inflate(layoutInflater, container, false)

        subScriptionArray = ArrayList()
        val constDialog=Constant.getDialogCustom(requireContext())

        binding.idbtnFloatAddnewLearn.setOnClickListener {
            var bundle = Bundle()
            bundle.putString(resources.getString(R.string.bundlestate), resources.getString(R.string.activeStateBundle))
            findNavController().navigate(
                R.id.action_learnersFragment2_to_addNewLearnerFragment,
                bundle
            )
        }

        binding.constLayoutId.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(resources.getString(R.string.howitworkstr), resources.getString(R.string.lovercaselearner))
            findNavController().navigate(
                R.id.action_learnersFragment2_to_howItWorksMainFragment,
                bundle
            )
        }
        //set visibility bottom nagigation
        buttonView = requireActivity().findViewById(R.id.bottom_navigation)
        buttonView.visibility = View.VISIBLE
        includeData = activity?.findViewById(R.id.idDataLayout)!!
        includeData.visibility = View.VISIBLE

        //get loading dialogbox
        val const = Constant.getDialogCustom(requireContext())

        //viewmodel initialize
        viewModel =
            ViewModelProvider(requireActivity(), LearnerViewModelFactory(requireActivity())).get(
                LearnerViewModel::class.java
            )

        viewModel.getLearnerList()

        viewModel.responseLiveLearnerList.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseHandler.Loading -> {
                    constDialog.show()
                }

                is ResponseHandler.OnFailed -> {
                    constDialog.cancel()
                }
                is ResponseHandler.OnSuccessResponse<ResponseData<LearnerReponseModel>?> -> {

                       Log.e("LearnerresponseData = ",state.response!!.data?.original?.data.toString())
                       state.response!!.data?.original?.data.let {
                         binding.recyLearnId.layoutManager=LinearLayoutManager(requireContext())
                         binding.recyLearnId.adapter=LearnerAdapter(it!!)
                           constDialog.cancel()
                       }
                }
            }
        }


        return binding.root
    }

    fun getDataBinding() = binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {


        }
    }

}