package com.example.aris4autism_project.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.VideoView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.VideoDataAdapter
import com.example.aris4autism_project.databinding.FragmentHowLearnerWorksBinding
import com.example.aris4autism_project.model.howitworkmodel.YoutubeVideoResponse
import com.example.aris4autism_project.model.responsemodel.ResponseData
import com.example.aris4autism_project.model.responsemodel.ResponseHandler
import com.example.aris4autism_project.viewmodel.HowItWorksViewModel
import com.example.aris4autism_project.viewmodel.HowItWorksViewModelFactory
import com.google.android.youtube.player.*


class HowLearnerWorksFragment(val strSelect: String) : Fragment() {

    lateinit var binding: FragmentHowLearnerWorksBinding
    val VIDEO_ID = "L0WGZSiOZsM"
    val YOUTUBE_API_KEY = "AIzaSyAUWttMR2-p0T1Lr7zj1mxYAupLzxWKJ6k"

    var youtubePlayer: YouTubePlayer? = null
    var youtubePlayerVw: YouTubePlayerView? = null
    private lateinit var btn: Button
    lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener
     lateinit var viewmodel: HowItWorksViewModel
    lateinit var videoView: VideoView

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
//
        if (Utils.isOnline(requireContext())) {
            viewmodel.getYoutubeVideosResponse()
        } else {
            Utils.InternetNotAvailableToast(requireContext())
        }

        //call how it work viewmodel
        viewmodel.resultHowItWork.observe(viewLifecycleOwner,{state->
            when(state)
            {
                is ResponseHandler.Loading->{

                }
                is ResponseHandler.OnFailed->{

                }
                is ResponseHandler.OnSuccessResponse<ResponseData<YoutubeVideoResponse>?>->{
                    binding.recyIdLearner.layoutManager = LinearLayoutManager(requireActivity())
                    binding.recyIdLearner.adapter = VideoDataAdapter(lifecycle, state.response?.data!!.learners,strSelect)
                }

            }
        })

//        viewmodel.resultHowItWork.observe(requireActivity(), {
//            when (it) {
//                is BaseResponse.Success -> {
//                    Log.e("response = ", it.data!!.data.learners.toString())
//                    binding.recyIdLearner.layoutManager = LinearLayoutManager(requireActivity())
//                    binding.recyIdLearner.adapter = VideoDataAdapter(lifecycle, it.data!!.data,strSelect)
//                }
//
//                is BaseResponse.Loading -> {
//
//                }
//
//                is BaseResponse.Error -> {
//                    Toast.makeText(requireContext(), it.msg.toString(), Toast.LENGTH_SHORT).show()
//                }
//
//            }
//        })

        return binding.root
    }


}