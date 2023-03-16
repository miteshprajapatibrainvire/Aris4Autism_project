package com.example.aris4autism_project.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.adapter.VideoDataAdapter
import com.example.aris4autism_project.databinding.FragmentHowLearnerWorksBinding
import com.example.aris4autism_project.model.howitworkmodel.VideoLearners
import com.example.aris4autism_project.viewmodel.HowItWorksViewModel
import com.example.aris4autism_project.viewmodel.HowItWorksViewModelFactory

class HowLearnerWorksFragment(val strSelect: String) : Fragment() {

    lateinit var binding: FragmentHowLearnerWorksBinding
     lateinit var viewmodel: HowItWorksViewModel
     companion object{
         var learnerWorkArray = ArrayList<VideoLearners>()
     }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHowLearnerWorksBinding.inflate(inflater)

        viewmodel =
            ViewModelProvider(requireActivity(), HowItWorksViewModelFactory(requireActivity())).get(
                HowItWorksViewModel::class.java
            )

//        if (Utils.isOnline(requireContext())) {
//            viewmodel.getYoutubeVideosResponse()
//        } else {
//            Utils.InternetNotAvailableToast(requireContext())
//        }

        binding.recyIdLearner.layoutManager = LinearLayoutManager(requireActivity())
        Log.e("learnerPass=",learnerWorkArray.toString())
        binding.recyIdLearner.adapter = VideoDataAdapter(lifecycle, learnerWorkArray,strSelect)
        //call how it work viewmodel
//        viewmodel.resultHowItWork.observe(viewLifecycleOwner,{state->
//            when(state)
//            {
//                is ResponseHandler.Loading->{
//
//                }
//                is ResponseHandler.OnFailed->{
//
//                }
//                is ResponseHandler.OnSuccessResponse<ResponseData<YoutubeVideoResponseModel>?>->{
//                    binding.recyIdLearner.layoutManager = LinearLayoutManager(requireActivity())
//                    binding.recyIdLearner.adapter = VideoDataAdapter(lifecycle, state.response?.data!!.learners,strSelect)
//                }
//
//            }
//        })

        return binding.root
    }


}