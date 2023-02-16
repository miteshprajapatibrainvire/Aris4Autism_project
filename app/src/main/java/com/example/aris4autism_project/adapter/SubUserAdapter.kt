package com.example.aris4autism_project.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.SubuserLayoutItemBinding
import com.example.aris4autism_project.model.DataXXXXXXX

class SubUserAdapter(val item: List<DataXXXXXXX>):RecyclerView.Adapter<SubUserAdapter.viewHolderSubUser>() {

   lateinit var  binding:SubuserLayoutItemBinding

    class viewHolderSubUser(val binding: SubuserLayoutItemBinding):RecyclerView.ViewHolder(binding.root)
    {

        fun bind(learnerModel: DataXXXXXXX) {
            binding.bindSubUser = learnerModel
        }

        val recyChips:RecyclerView=binding.chipsRecycler
        val constLayout:ConstraintLayout=binding.constraintId

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderSubUser
    {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.subuser_layout_item,
            parent,
            false
        )
        return viewHolderSubUser(binding)

    }

    override fun onBindViewHolder(holder: viewHolderSubUser, position: Int) {

        val subUser=item[position]
        holder.bind(subUser)

        holder.recyChips.layoutManager= GridLayoutManager(holder.itemView.context,5)
        holder.recyChips.adapter=SubUserChipsAdapter(item.get(position).learnerIds)

        holder.constLayout.setOnClickListener {view->
            val bundle= Bundle()
            bundle.putSerializable("assignLearner",item.get(position))
            view.findNavController().navigate(R.id.action_subuserFragment2_to_subuserDetailsFragment,bundle)
        }

    }

    override fun getItemCount(): Int {
       return item.size
    }

}