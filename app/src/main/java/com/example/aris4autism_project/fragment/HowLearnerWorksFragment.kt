package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.adapter.VideoDataAdapter
import com.example.aris4autism_project.databinding.FragmentHowLearnerWorksBinding
import com.example.aris4autism_project.model.LearnerVideoLink
import com.example.aris4autism_project.model.LearnerXXXXXX
import com.google.android.youtube.player.*
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions


class HowLearnerWorksFragment(var mainLearnerWork: ArrayList<LearnerXXXXXX>) : Fragment()
{

    lateinit var binding:FragmentHowLearnerWorksBinding
     val VIDEO_ID="L0WGZSiOZsM"
    val YOUTUBE_API_KEY="AIzaSyAUWttMR2-p0T1Lr7zj1mxYAupLzxWKJ6k"

     var youtubePlayer:YouTubePlayer ?= null
    var youtubePlayerVw :YouTubePlayerView ?= null
    private lateinit var btn:Button
    lateinit var youtubePlayerInit:YouTubePlayer.OnInitializedListener


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHowLearnerWorksBinding.inflate(inflater)

//        var mainLearnerWork:ArrayList<LearnerXXXXXX> = ArrayList()
//        Log.e("LearnerPassData=", mainLearnerWork.toString())
        var videoList:ArrayList<LearnerVideoLink> = ArrayList()
        videoList.add(LearnerVideoLink("https://www.youtube.com/watch?v=4mh75_u0uR8","Overview-6"))
        videoList.add(LearnerVideoLink("https://www.youtube.com/watch?v=YtvP5A5OHpU","What is Autism?"))

        binding.recyIdLearner.layoutManager= LinearLayoutManager(requireActivity())
        binding.recyIdLearner.adapter= VideoDataAdapter(lifecycle,videoList!!)

//       lifecycle.addObserver(binding.youtubePlayer)
        // below method will provides us the youtube player ui controller such
        // as to play and pause a video to forward a video and many more features.

//        binding.youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//            fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
//                val videoId = "L0WGZSiOZsM"
//                youTubePlayer.loadVideo(videoId, 0)
////                youtubePlayer?.play()
//            }
//        })

//        binding.youtubePlayer.setOnClickListener {
//            youtubePlayer?.play()
//        }

        val iFramePlayerOptions: IFramePlayerOptions = IFramePlayerOptions.Builder()
            .controls(1)
            .build()

//        binding.youtubePlayer.initialize(object : YouTubePlayerListener
//            {
//            override fun onApiChange(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
//
//            }
//
//            override fun onCurrentSecond(
//                youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
//                second: Float
//            ) {
//
//            }
//
//            override fun onError(
//                youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
//                error: PlayerConstants.PlayerError
//            ) {
//
//            }
//
//            override fun onPlaybackQualityChange(
//                youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
//                playbackQuality: PlayerConstants.PlaybackQuality
//            ) {
//
//            }
//
//            override fun onPlaybackRateChange(
//                youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
//                playbackRate: PlayerConstants.PlaybackRate
//            ) {
//
//            }
//
//            override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
//
//                val videoId = "L0WGZSiOZsM"
//                youTubePlayer.loadVideo(videoId,0f)
//                binding.youtubePlayer.enableAutomaticInitialization=false
//
//            }
//
//            override fun onStateChange(
//                youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
//                state: PlayerConstants.PlayerState
//            ) {
//
//            }
//
//            override fun onVideoDuration(
//                youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
//                duration: Float
//            ) {
//
//            }
//
//            override fun onVideoId(
//                youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
//                videoId: String
//            ) {
//
//            }
//
//            override fun onVideoLoadedFraction(
//                youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
//                loadedFraction: Float
//            ) {
//
//            }
//
//        })




        return binding.root
    }


}