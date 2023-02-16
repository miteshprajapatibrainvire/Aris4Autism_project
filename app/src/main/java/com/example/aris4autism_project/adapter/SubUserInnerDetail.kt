package com.example.aris4autism_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.InnerSubDetailsBinding
import com.example.aris4autism_project.model.LearnerIdX
import com.example.aris4autism_project.model.LearnerIdXX
import com.example.aris4autism_project.model.LearnerModel

class SubUserInnerDetail(val list:ArrayList<LearnerIdXX>):RecyclerView.Adapter<SubUserInnerDetail.viewHolder>() {


   lateinit var  binding:InnerSubDetailsBinding

    class viewHolder(val binding: InnerSubDetailsBinding): RecyclerView.ViewHolder(binding.root)
    {

        fun bind(itemSubUser:LearnerIdXX)
        {
            binding.bindSubUser=itemSubUser
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        binding= DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.inner_sub_details,
            parent,
            false)
        return viewHolder(binding)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

          holder.bind(list[position])


    }

    override fun getItemCount(): Int {
        return list.size
    }

}