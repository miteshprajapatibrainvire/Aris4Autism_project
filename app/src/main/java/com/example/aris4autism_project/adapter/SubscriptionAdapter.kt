package com.example.aris4autism_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.SubscriptionItemLayoutBinding
import com.example.aris4autism_project.model.SubscriptionModel

class SubscriptionAdapter(val subList:ArrayList<SubscriptionModel>):RecyclerView.Adapter<SubscriptionAdapter.subViewModel>() {

    class subViewModel(binding:SubscriptionItemLayoutBinding):RecyclerView.ViewHolder(binding.root)
    {
        val imgSUb:ImageView=binding.imgIdIconSub
        val txName:TextView=binding.txIdNameSubscription
        val txGen:TextView=binding.txIdGenderSub
        val txYear:TextView=binding.IdYearlySub
        val txInvoice:TextView=binding.txIdInvoice
        val txMonthPlan:TextView=binding.txIdMonthPlanSub
        val txFullDate:TextView=binding.txFullDateSub
        val txActive:TextView=binding.idActiveSub
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): subViewModel {

        return subViewModel(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.subscription_item_layout,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: subViewModel, position: Int) {
        holder.imgSUb.setImageResource(subList.get(position).imgId)
        holder.txName.text=subList.get(position).name
        holder.txGen.text=subList.get(position).gen
        holder.txYear.text=subList.get(position).yearDetails
        holder.txInvoice.text=subList.get(position).invoiceId.toString()
        holder.txMonthPlan.text=subList.get(position).monthPlan
        holder.txFullDate.text=subList.get(position).startToEndDob
        if(!subList.get(position).activeStatus)
        {
            holder.txActive.setBackgroundResource(R.drawable.status_expired_tag)
            holder.txActive.text="Expired"
        }

    }

    override fun getItemCount(): Int {
        return subList.size
    }


}