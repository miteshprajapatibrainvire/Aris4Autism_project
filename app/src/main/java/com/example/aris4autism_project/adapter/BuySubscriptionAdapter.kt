package com.example.aris4autism_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.SubscriptionItemDataBinding
import com.example.aris4autism_project.model.subscriptionplanmodel.SubscriptionPlanShortDuration

class BuySubscriptionAdapter(var slist:ArrayList<SubscriptionPlanShortDuration>):RecyclerView.Adapter<BuySubscriptionAdapter.viewSubscriptonModel>() {

    class viewSubscriptonModel(val binding:SubscriptionItemDataBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(itemData: SubscriptionPlanShortDuration)
        {
            binding.bindModelData=itemData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewSubscriptonModel
    {
        return viewSubscriptonModel(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.subscription_item_data,parent,false))
    }

    override fun onBindViewHolder(holder: viewSubscriptonModel, position: Int) {
        holder.bind(slist[position])
    }

    override fun getItemCount(): Int
    {
            return slist.size
    }

}