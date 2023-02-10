package com.example.aris4autism_project.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.OverviewItemLayoutBinding
import com.example.aris4autism_project.model.DataXXXXXXXXXXX

class OverViewAdapter(val overViewItem: List<DataXXXXXXXXXXX>):RecyclerView.Adapter<OverViewAdapter.OverViewHolder>() {

    val bundle=Bundle()

    class OverViewHolder(val binding:OverviewItemLayoutBinding):RecyclerView.ViewHolder(binding.root)
    {
//        val imgIcon:ImageView=binding.imgIdIconOverView
//        val txName:TextView=binding.txIdNameOverView
//        val txGender:TextView=binding.txIdGenderOverView
//        val txYearly:TextView=binding.IdYearlyOverView
//        val txdob:TextView=binding.dobIdOverView
//        val txMonthly:TextView=binding.txIdMonthPlanOverView
//        val txFullDob:TextView=binding.txFullDateOverView
//        val txStatus:TextView=binding.idActiveOverView
//        val cardView:CardView=binding.mtvCardView
        fun bind(itemData:DataXXXXXXXXXXX)
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
//        holder.imgIcon.setImageResource(overViewItem.get(position).imgId)
//        holder.txName.text=overViewItem.get(position).name
//        holder.txGender.text=overViewItem.get(position).gender
//        holder.txYearly.text=overViewItem.get(position).dateOfBirth
//        holder.txdob.text=overViewItem.get(position).dateOfBirth
//        holder.txMonthly.text=overViewItem.get(position).userSubscriptions.title
//        holder.txFullDob.text=overViewItem.get(position).userSubscriptions.startDate+" to "+overViewItem.get(position).userSubscriptions.endDate
//
//        holder.cardView.setOnClickListener {view->

//            bundle.putSerializable("overviewModel",overViewItem.get(position))
//            view.findNavController().navigate(R.id.action_overviewFragment2_to_overViewDetailsFragment,bundle)

//        }
//
//        if(overViewItem.get(position).userSubscriptions.status=="0")
//        {
//            holder.txStatus.setBackgroundResource(R.drawable.status_expired_tag)
//            holder.txStatus.text="Expired"
//        }
//       else
//        {
//            holder.txStatus.text="Active"
//        }


    }

    override fun getItemCount(): Int {
        return overViewItem.size
    }
}