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
import com.example.aris4autism_project.model.responsemodel.ResponseData
import com.example.aris4autism_project.model.responsemodel.ResponseHandler
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
            bundle.putString("BundleState", "activeStateBundle")
            findNavController().navigate(
                R.id.action_learnersFragment2_to_addNewLearnerFragment,
                bundle
            )
        }

        binding.constLayoutId.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("howWork", "learner")
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
                    Log.i("TAG", "onCreateView: Loading...")
                    constDialog.show()
                }

                is ResponseHandler.OnFailed -> {
                    Log.i("TAG", "onCreateView: OnFailed")
                    constDialog.cancel()
                }
                is ResponseHandler.OnSuccessResponse<ResponseData<LearnerReponseModel>?> -> {

                       Log.e("responseData = ",state.response!!.data?.original?.data.toString())
                       state.response!!.data?.original?.data.let {
                         binding.recyLearnId.layoutManager=LinearLayoutManager(requireContext())
                         binding.recyLearnId.adapter=LearnerAdapter(it!!)
                           constDialog.cancel()
                       }
                      /*  learnerList.meta?.let {
                            Log.i("TAG", "onCreateViewLEARNER_SIZE: ${it.message}")
                        }*/

                }
            }
        }

        /*
               //call api
               if(Utils.isOnline(requireContext())) {
                   viewModel.getLearnerList(
                       "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE",
                       "Android",
                       "1"
                   )
               }
               else
               {
                  Utils.InternetNotAvailableToast(requireContext())
               }

               //get api data from server
               viewModel.resultLearner.observe(requireActivity()) {
                   when (it) {
                       is BaseResponse.Success -> {
                           binding.recyLearnId.adapter = it.data?.data?.original?.data?.let { it1 ->
                               LearnerAdapter(
                                   it1
                               )
                           }
                           binding.recyLearnId.layoutManager = LinearLayoutManager(requireActivity())
                           const.cancel()
                       }

                       is BaseResponse.Loading -> {
                           const.show()
                       }
                       is BaseResponse.Error -> {
                           const.cancel()
                       }
                       else -> {

                       }

                   }
               }

                */

        return binding.root
    }

    fun getDataBinding() = binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {


        }
    }

}