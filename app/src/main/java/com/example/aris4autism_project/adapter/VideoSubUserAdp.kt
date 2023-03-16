package com.example.aris4autism_project.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.VideoPlayerLayoutBinding
import com.example.aris4autism_project.model.howitworkmodel.VideoSubuser

class VideoSubUserAd(var lifecycle: Lifecycle,var slist: ArrayList<VideoSubuser>) :RecyclerView.Adapter<VideoSubUserAd.viewBindData>()
{


    class viewBindData(val binding:VideoPlayerLayoutBinding):RecyclerView.ViewHolder(binding.root)
    {

        var idConstraintClass=binding.idconstarintClass
        var txTitle=binding.idtxTitleVideo
        var imgThumbnail=binding.idimgUrlVideo

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

        Glide.with(holder.itemView.context)
            .load(slist.get(position).thumbnailUrl)
            .into(holder.imgThumbnail)

        holder.idConstraintClass.setTag("tagVideoUrl")
        if(slist.get(position).videoLink==null)
        {
            holder.idConstraintClass.setTag(R.id.tagVideoUrl,slist.get(position).videoUrl)
        }

        if(slist.get(position).videoUrl==null)
        {
            holder.idConstraintClass.setTag(R.id.tagVideoUrl,slist.get(position).videoLink)
        }

        holder.imgThumbnail.setOnClickListener {view->

            val bundle= Bundle()
            if(slist.get(position).videoLink==null)
            {
                bundle.putString("videouri",slist.get(position).videoUrl.toString())
                view.findNavController().navigate(R.id.videoPlayerFragment,bundle)

            }
            else
            {

                bundle.putString("videouri", slist.get(position).videoLink)
                view.findNavController().navigate(R.id.videoPlayerFragment,bundle)

            }
        }

        holder.txTitle.text = slist.get(position).title

    }

    override fun getItemCount(): Int {
        return slist.size
    }

}