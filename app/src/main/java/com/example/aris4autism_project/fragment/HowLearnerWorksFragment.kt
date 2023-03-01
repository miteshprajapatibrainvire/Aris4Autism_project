package com.example.aris4autism_project.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.VideoDataAdapter
import com.example.aris4autism_project.databinding.FragmentHowLearnerWorksBinding
import com.example.aris4autism_project.model.LearnerVideoLink
import com.example.aris4autism_project.viewmodel.HowItWorksViewModel
import com.example.aris4autism_project.viewmodel.HowItWorksViewModelFactory
import com.google.android.youtube.player.*
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions


class HowLearnerWorksFragment() : Fragment() {

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

        if (Utils.isOnline(requireContext())) {
            viewmodel.getYoutubeVideosResponse(
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE",
                "Android",
                "1"
            )
        } else {
            Utils.InternetNotAvailableToast(requireContext())
        }

        viewmodel.resultHowItWork.observe(requireActivity(), {
            when (it) {
                is BaseResponse.Success -> {
                    Log.e("response = ", it.data!!.data.learners.toString())
                    binding.recyIdLearner.layoutManager = LinearLayoutManager(requireActivity())
                    binding.recyIdLearner.adapter = VideoDataAdapter(lifecycle, it.data!!.data)
                }

                is BaseResponse.Loading -> {

                }

                is BaseResponse.Error -> {
                    Toast.makeText(requireContext(), it.msg.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })



        return binding.root
    }


}