package com.example.aris4autism_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.VideoPlayerLayoutBinding
import com.example.aris4autism_project.model.LearnerVideoLink
import com.example.aris4autism_project.model.LearnerXXXXXX
import com.google.android.youtube.player.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener

class VideoDataAdapter(val lifecycle: Lifecycle,var slist: ArrayList<LearnerVideoLink>):RecyclerView.Adapter<VideoDataAdapter.viewBindData>() {


    var youtubePlayer: com.google.android.youtube.player.YouTubePlayer?= null
    var youtubePlayerVw : YouTubePlayerView?= null
    val VIDEO_ID="L0WGZSiOZsM"
    val YOUTUBE_API_KEY="AIzaSyAUWttMR2-p0T1Lr7zj1mxYAupLzxWKJ6k"

    class viewBindData(val binding:VideoPlayerLayoutBinding):RecyclerView.ViewHolder(binding.root)
    {
//       fun bind(item:LearnerXXXXXX)
//       {
//           binding.bindVideo=item
//       }
        var youtubePlayer=binding.youtubePlayer
        var txTitle=binding.idtxTitleVideo

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewBindData {
        return viewBindData(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.video_player_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: viewBindData, position: Int)
    {
//        holder.bind(slist.get(position))
        lifecycle.addObserver(holder.youtubePlayer)

        holder.youtubePlayer.initialize(object : YouTubePlayerListener
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

                    val videoId = "L0WGZSiOZsM"
                    youTubePlayer.loadVideo(videoId,0f)
                    holder.youtubePlayer.enableAutomaticInitialization=false


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

//        binding.youtubePlayer.initialize(YOUTUBE_API_KEY,object : com.google.android.youtube.player.YouTubePlayer.OnInitializedListener{
//            override fun onInitializationSuccess(
//                p0: YouTubePlayer.Provider?,
//                p1: YouTubePlayer?,
//                p2: Boolean
//            ) {
//                youtubePlayer=p1
//              youtubePlayer?.loadVideo(VIDEO_ID)
//                youtubePlayer?.play()
//            }
//
//            override fun onInitializationFailure(
//                p0: YouTubePlayer.Provider?,
//                p1: YouTubeInitializationResult?
//            ) {
//                Toast.makeText(activity, "Failed to initialize video", Toast.LENGTH_SHORT).show()
//            }
//        })

//            youtubePlayerInit=object : YouTubePlayer.OnInitializedListener{
//                override fun onInitializationSuccess(
//                    p0: YouTubePlayer.Provider?,
//                    p1: YouTubePlayer?,
//                    p2: Boolean
//                ) {
//                        p1?.loadVideo(VIDEO_ID)
//                }
//
//                override fun onInitializationFailure(
//                    p0: YouTubePlayer.Provider?,
//                    p1: YouTubeInitializationResult?
//                ) {
//                    Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show()
//                }
//
//            }
//


      holder.txTitle.text=slist.get(position).title.toString()

    }

    override fun getItemCount(): Int {
       return slist.size
    }


}