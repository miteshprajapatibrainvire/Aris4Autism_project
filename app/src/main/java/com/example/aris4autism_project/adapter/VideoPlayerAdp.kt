package com.example.aris4autism_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.VideoPlayerLayoutBinding
import com.example.aris4autism_project.model.howitworkmodel.VideoLearners

class VideoPlayerAdp(var slist: ArrayList<VideoLearners>?):RecyclerView.Adapter<VideoPlayerAdp.videoViewHolder>() {

    class videoViewHolder(val binding:VideoPlayerLayoutBinding):RecyclerView.ViewHolder(binding.root)
    {
//        fun binding(itemBind:LearnerXXXXXX)
//        {
//            binding.bindVideo=itemBind
//        }

//        val playerVideo=itemView.findViewById<YouTubePlayerView>(R.id.youtubePlayer)
          var titletx=binding.idtxTitleVideo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): videoViewHolder {

        return videoViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.video_player_layout,parent,false))
//     return videoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.video_player_layout,parent,false))
    }

    override fun onBindViewHolder(holder: videoViewHolder, position: Int)
    {
        holder.titletx.text=slist!!.get(position).title
    }

    override fun getItemCount(): Int {
        return 2
    }

}