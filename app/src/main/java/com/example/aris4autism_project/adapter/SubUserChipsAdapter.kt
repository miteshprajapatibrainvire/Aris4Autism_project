package com.example.aris4autism_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.LayoutChipsBinding
import com.example.aris4autism_project.model.LearnerId

class SubUserChipsAdapter(val arrayChips:ArrayList<LearnerId>):RecyclerView.Adapter<SubUserChipsAdapter.subUserViewHolder>() {


    lateinit var binding:LayoutChipsBinding

    class subUserViewHolder(val binding:LayoutChipsBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(subChipsBind:LearnerId)
        {
            binding.bindLearner=subChipsBind
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): subUserViewHolder {

        binding= DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_chips,
            parent,
            false)
        return subUserViewHolder(binding)

    }

    override fun onBindViewHolder(holder: subUserViewHolder, position: Int) {
        val subItem=arrayChips[position]
        holder.bind(subItem)
    }

    override fun getItemCount(): Int {
       return arrayChips.size
    }

}