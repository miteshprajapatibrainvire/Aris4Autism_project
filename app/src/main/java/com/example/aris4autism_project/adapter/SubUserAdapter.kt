package com.example.aris4autism_project.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.SubuserLayoutItemBinding
import com.example.aris4autism_project.model.SubUserModel

class SubUserAdapter(val item: ArrayList<SubUserModel>):RecyclerView.Adapter<SubUserAdapter.viewHolderSubUser>() {

   lateinit var  binding:SubuserLayoutItemBinding

    class viewHolderSubUser(binding: SubuserLayoutItemBinding):RecyclerView.ViewHolder(binding.root)
    {
        val imgIdSubUser:ImageView=binding.imgIdSubuser
        val txName:TextView=binding.txIdImgSub
        val txEmail:TextView=binding.txIdEmail
        val recyChips:RecyclerView=binding.chipsRecycler
        val constLayout:ConstraintLayout=binding.constraintId
        val txactive:TextView=binding.idActive
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderSubUser {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.subuser_layout_item,
            parent,
            false
        )

        return viewHolderSubUser(binding)

    }

    override fun onBindViewHolder(holder: viewHolderSubUser, position: Int) {

        holder.imgIdSubUser.setImageResource(item.get(position).idImgSubUser)
        holder.txactive.text=item.get(position).status

        if(holder.txactive.text.toString().equals("Pending"))
        {
            holder.txactive.setBackgroundResource(R.drawable.bg_darkgray)
        }

        holder.txName.text=item.get(position).NameSubUser
        holder.txEmail.text=item.get(position).EmailSubUser
        holder.recyChips.layoutManager=GridLayoutManager(holder.itemView.context,5)
        holder.recyChips.adapter=SubUserChipsAdapter(item.get(position).assignLearnerName)
        holder.constLayout.setOnClickListener {view->

            val bundle=Bundle()
            bundle.putSerializable("assignLearner",item.get(position))
            view.findNavController().navigate(R.id.action_subuserFragment2_to_subuserDetailsFragment,bundle)

        }


    }

    override fun getItemCount(): Int {
       return item.size
    }

}