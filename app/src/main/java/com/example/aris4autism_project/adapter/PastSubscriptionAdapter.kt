package com.example.aris4autism_project.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.PastSubscriptionItemLayoutBinding
import com.example.aris4autism_project.model.PastSubscriptionModel

class PastSubscriptionAdapter(val itemList:ArrayList<PastSubscriptionModel>):RecyclerView.Adapter<PastSubscriptionAdapter.SubViewModel>() {

   class SubViewModel(binding:PastSubscriptionItemLayoutBinding):RecyclerView.ViewHolder(binding.root)
   {
       val monthPlan:TextView=binding.txMonthPlan
       val subId:TextView=binding.txidSubDetail
       val startDob:TextView=binding.txidStartData
       val endDob:TextView=binding.txidEndData
       val txActive:TextView=binding.idActive
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubViewModel {
        return SubViewModel(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.past_subscription_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: SubViewModel, position: Int) {
        holder.monthPlan.text=itemList.get(position).monthPlan
        holder.subId.text=itemList.get(position).subscriptionId
        holder.startDob.text=itemList.get(position).startDate
        holder.endDob.text=itemList.get(position).endDate

        if(!itemList.get(position).status)
        {
            holder.txActive.text="Expired"
            holder.txActive.setTextColor(Color.RED)
        }
        else
        {
            holder.txActive.text="Active"
            holder.txActive.setTextColor(Color.GREEN)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}