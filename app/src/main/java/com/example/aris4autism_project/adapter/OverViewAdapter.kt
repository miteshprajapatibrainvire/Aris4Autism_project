package com.example.aris4autism_project.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.OverviewItemLayoutBinding
import com.example.aris4autism_project.model.overviewmodel.OverViewListData

class OverViewAdapter(val overViewItem: ArrayList<OverViewListData>):RecyclerView.Adapter<OverViewAdapter.OverViewHolder>() {

    val bundle=Bundle()

    class OverViewHolder(val binding:OverviewItemLayoutBinding):RecyclerView.ViewHolder(binding.root)
    {
        val cardView:CardView=binding.mtvCardView
        fun bind(itemData: OverViewListData)
        {
            binding.bindOverView=itemData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverViewHolder {

        return OverViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.overview_item_layout,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: OverViewHolder, position: Int) {

        val items=overViewItem.get(position)
        holder.bind(items)

        holder.cardView.setOnClickListener {view->
            bundle.putSerializable("overviewModel",overViewItem.get(position))
            view.findNavController().navigate(R.id.action_overviewFragment2_to_overViewDetailsFragment,bundle)
        }

    }

    override fun getItemCount(): Int {
        return overViewItem.size
    }

}