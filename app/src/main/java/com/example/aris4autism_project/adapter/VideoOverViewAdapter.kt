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
import com.example.aris4autism_project.model.howitworkmodel.VideoOverview

class VideoOverViewAdapter(var lifecy: Lifecycle, var slist:ArrayList<VideoOverview>):RecyclerView.Adapter<VideoOverViewAdapter.videOverViewBind>() {

    class videOverViewBind(var binding:VideoPlayerLayoutBinding):RecyclerView.ViewHolder(binding.root)
    {
        val idtxTitle=binding.idtxTitleVideo
        val idimgView=binding.idimgUrlVideo
        val idConstraintClass=binding.idconstarintClass
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): videOverViewBind {
        return videOverViewBind(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.video_player_layout,parent,false))
    }

    override fun onBindViewHolder(holder: videOverViewBind, position: Int) {


        Glide.with(holder.itemView.context)
            .load(slist.get(position).thumbnailUrl)
            .into(holder.idimgView)

        var videoUrlPass:String = " "

        holder.idConstraintClass.setTag("tagVideoUrl")
        if(slist.get(position).videoLink==null)
        {
            holder.idConstraintClass.setTag(R.id.tagVideoUrl,slist.get(position).videoUrl)
        }

        if(slist.get(position).videoUrl==null)
        {
            holder.idConstraintClass.setTag(R.id.tagVideoUrl,slist.get(position).videoLink)
        }

        holder.idimgView.setOnClickListener {view->

            var bundle= Bundle()
            if(slist.get(position).videoLink==null)
            {
//                var uri:Uri=Uri.parse(holder.idConstraintClass.getTag(R.id.tagVideoUrl).toString())
//                var intent = Intent(Intent.ACTION_VIEW, uri)
//                intent.setDataAndType(uri, "video/mp4")
//                holder.itemView.context.startActivity(intent)
                bundle.putString("videouri",slist.get(position).videoUrl.toString())
                view.findNavController().navigate(R.id.videoPlayerFragment,bundle)

            }
            else
            {
//                if (slist.get(position).videoLink.contains("youtube", true))
//                {
//                    var uri: Uri = Uri.parse(slist.get(position).videoLink.toString())
//                    var intent = Intent(Intent.ACTION_VIEW, uri)
//                    intent.setPackage("com.google.android.youtube")
//                    holder.itemView.context.startActivity(intent)
                bundle.putString("videouri",slist.get(position).videoLink.toString())
                view.findNavController().navigate(R.id.videoPlayerFragment,bundle)

//                }
            }
        }

        holder.idtxTitle.text = slist.get(position).title.toString()




    }

    override fun getItemCount(): Int {
        return slist.size
    }
}