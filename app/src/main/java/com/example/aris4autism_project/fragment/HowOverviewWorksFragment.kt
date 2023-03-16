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
import com.example.aris4autism_project.adapter.VideoOverViewAdapter
import com.example.aris4autism_project.databinding.FragmentHowOverviewWorksBinding
import com.example.aris4autism_project.model.howitworkmodel.YoutubeVideoResponse
import com.example.aris4autism_project.model.responsemodel.ResponseData
import com.example.aris4autism_project.model.responsemodel.ResponseHandler
import com.example.aris4autism_project.viewmodel.HowItWorksViewModel
import com.example.aris4autism_project.viewmodel.HowItWorksViewModelFactory

class HowOverviewWorksFragment(s: String) : Fragment() {

    lateinit var binding:FragmentHowOverviewWorksBinding
    lateinit var viewmodel:HowItWorksViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHowOverviewWorksBinding.inflate(inflater)
      viewmodel=ViewModelProvider(requireActivity(),HowItWorksViewModelFactory(requireActivity())).get(HowItWorksViewModel::class.java)

        viewmodel.getYoutubeVideosResponse()
        viewmodel.resultHowItWork.observe(viewLifecycleOwner,{state->
            when(state)
            {
                is ResponseHandler.Loading->{

                }
                is ResponseHandler.OnFailed->{

                }
                is ResponseHandler.OnSuccessResponse<ResponseData<YoutubeVideoResponse>?>->{
                    binding.recyIdOverview.layoutManager=LinearLayoutManager(requireActivity())
                    binding.recyIdOverview.adapter=VideoOverViewAdapter(lifecycle,
                        state.response?.data!!.overview)
                }
            }
        })
//        viewmodel.resultHowItWork.observe(requireActivity(),{
//            when(it)
//            {
//                is BaseResponse.Success->
//                {
//                    binding.recyIdOverview.layoutManager=LinearLayoutManager(requireActivity())
//                    binding.recyIdOverview.adapter=VideoOverViewAdapter(lifecycle,it.data!!.data)
//                }
//
//                is BaseResponse.Loading->{
//
//                }
//
//                is BaseResponse.Error->{
//                    Toast.makeText(requireContext(), it.msg.toString(), Toast.LENGTH_SHORT).show()
//
//                }
//            }
//        })

        return binding.root
    }


}