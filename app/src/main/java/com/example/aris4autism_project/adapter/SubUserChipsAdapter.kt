package com.example.aris4autism_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.LayoutChipsBinding

class SubUserChipsAdapter(val arrayChips:ArrayList<String>):RecyclerView.Adapter<SubUserChipsAdapter.subUserViewHolder>() {


    lateinit var binding:LayoutChipsBinding

    class subUserViewHolder(binding:LayoutChipsBinding):RecyclerView.ViewHolder(binding.root)
    {
        val txName:TextView=binding.chipsId
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
      holder.txName.text=arrayChips[position]
    }

    override fun getItemCount(): Int {
       return arrayChips.size
    }

}