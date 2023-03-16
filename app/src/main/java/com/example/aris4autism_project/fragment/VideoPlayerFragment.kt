package com.example.aris4autism_project.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.FragmentVideoPlayerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener

class VideoPlayerFragment : Fragment() {

    lateinit var binding:FragmentVideoPlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentVideoPlayerBinding.inflate(inflater)

        val videoStringUrl=requireArguments().getString(getString(R.string.videouril))
        var howWorkSelection=requireArguments().getString(getString(R.string.howworkstr))

        var callback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                var bundle=Bundle()
                bundle.putString(getString(R.string.howworkstr),howWorkSelection)
                findNavController().navigate(R.id.howItWorksMainFragment,bundle)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        if(!videoStringUrl!!.contains(getString(R.string.youtubestr)))
        {
            binding.videoView.visibility=View.VISIBLE
            binding.youtubeplayer.visibility=View.GONE
            val uri: Uri = Uri.parse(videoStringUrl)
            binding.videoView.setVideoURI(uri)
            val mediaController = MediaController(requireContext())
            mediaController.setAnchorView(binding.videoView)
            mediaController.setMediaPlayer(binding.videoView)
            binding.videoView.setMediaController(mediaController)
            binding.videoView.start()
        }
        else
        {
            binding.videoView.visibility=View.GONE
            binding.youtubeplayer.visibility=View.VISIBLE
            lifecycle.addObserver(binding.youtubeplayer)
            var splitUrl=videoStringUrl.split("=")

            binding.youtubeplayer.initialize(object : YouTubePlayerListener
            {
                override fun onApiChange(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {

                }

                override fun onCurrentSecond(
                    youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
                    second: Float
                ) {

                }

                override fun onError(
                    youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
                    error: PlayerConstants.PlayerError
                ) {
                }

                override fun onPlaybackQualityChange(
                    youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
                    playbackQuality: PlayerConstants.PlaybackQuality
                ) {

                }

                override fun onPlaybackRateChange(
                    youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
                    playbackRate: PlayerConstants.PlaybackRate
                ) {

                }

                override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {

                    youTubePlayer.loadVideo(splitUrl[1],0f)
                    binding.youtubeplayer.enableAutomaticInitialization=false

                }

                override fun onStateChange(
                    youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
                    state: PlayerConstants.PlayerState
                ) {

                }

                override fun onVideoDuration(
                    youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
                    duration: Float
                ) {

                }

                override fun onVideoId(
                    youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
                    videoId: String
                ) {

                }

                override fun onVideoLoadedFraction(
                    youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
                    loadedFraction: Float
                ) {

                }
            })

        }

        val callBack=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_videoPlayerFragment_to_howItWorksMainFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)

        return binding.root
    }
}