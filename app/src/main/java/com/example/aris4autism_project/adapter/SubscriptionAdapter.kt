package com.example.aris4autism_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.SubscriptionItemLayoutBinding
import com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse.SubscriptionData

class SubscriptionAdapter(val subList: List<SubscriptionData>):RecyclerView.Adapter<SubscriptionAdapter.subViewModel>() {

    class subViewModel(val binding:SubscriptionItemLayoutBinding):RecyclerView.ViewHolder(binding.root)
    {

        fun bind(subItem: SubscriptionData)
        {
            binding.bindSubscription=subItem
        }

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

          val bindSubscriptionItem=subList[position]
          holder.bind(bindSubscriptionItem)

    }

    override fun getItemCount(): Int {
        return subList.size
    }


}