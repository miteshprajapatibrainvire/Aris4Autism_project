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
import com.example.aris4autism_project.model.howitworkmodel.VideoLearners


class VideoDataAdapter(
    val lifecycle: Lifecycle,
    var slist: ArrayList<VideoLearners>,
    val strSelect: String
):RecyclerView.Adapter<VideoDataAdapter.viewBindData>() {



    class viewBindData(val binding:VideoPlayerLayoutBinding):RecyclerView.ViewHolder(binding.root)
    {
//       fun bind(item:LearnerXXXXXX)
//       {
//           binding.bindVideo=item
//       }
//        var youtubePlayer=binding.youtubePlayer

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

        if(slist.get(position).videoUrl==null)
        {
            holder.idConstraintClass.setTag(R.id.tagVideoUrl,slist.get(position).videoLink)
        }

        holder.imgThumbnail.setOnClickListener {view->

            val bundle= Bundle()
            if(slist.get(position).videoLink==null)
            {
//                var uri:Uri=Uri.parse(holder.idConstraintClass.getTag(R.id.tagVideoUrl).toString())
//                var intent = Intent(Intent.ACTION_VIEW, uri)
//                intent.setDataAndType(uri, "video/mp4")
//                holder.itemView.context.startActivity(intent)
                bundle.putString("videouri", slist.get(position).videoUrl)
                bundle.putString("howWork",strSelect)
                view.findNavController().navigate(R.id.action_howItWorksMainFragment_to_learnersFragment2,bundle)

            }
           else
            {
//                if (slist.get(position).videoLink.contains("youtube", true))
//                {
//                    var uri: Uri = Uri.parse(slist.get(position).videoLink.toString())
//                    var intent = Intent(Intent.ACTION_VIEW, uri)
//                    intent.setPackage("com.google.android.youtube")
//                    holder.itemView.context.startActivity(intent)
                    bundle.putString("videouri", slist.get(position).videoLink)
                    view.findNavController().navigate(R.id.videoPlayerFragment,bundle)
//                }
            }
        }
            holder.txTitle.text = slist.get(position).title
    }

    override fun getItemCount(): Int {
       return slist.size
    }


}