package com.example.aris4autism_project.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.LayoutImgdataBinding
import com.example.aris4autism_project.model.userprofilemodel.ProfileData

class ProfileAdapter(
    var slist: ArrayList<ProfileData>,
    var getItemSeleted:(id :Boolean)->Unit,
    var getImgSelected:(id:Int,imgIcon:String)->Unit
) :
    RecyclerView.Adapter<ProfileAdapter.viewHolderData>() {

     lateinit var binding: LayoutImgdataBinding
     var checkArray:ArrayList<CheckBox> = ArrayList()

    class viewHolderData(var binding: LayoutImgdataBinding) :
        RecyclerView.ViewHolder(binding.root)
    {

        fun bindItems(item: ProfileData)
        {
            binding.modelProfile=item
        }

//        val imgView = binding.imgData
        val cardData = binding.cardData
        val chIdCheckBox = binding.chId
        val constraint = binding.contraintLayout

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderData {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_imgdata,
            parent,
            false
        )
        return viewHolderData(binding)
    }

    override fun onBindViewHolder(holder: viewHolderData, position: Int) {

        val positionCurrent=position
        val previoursePosition=position-1
        val nextPosition=position+1
        val profilebind = slist[position]
        holder.bindItems(slist[position])

//        holder.imgView.setImageResource(profilebind.idDrawable)
        holder.chIdCheckBox.visibility = View.GONE
        checkArray.add(holder.chIdCheckBox)

        if (slist[position].isSelected)
        {
            holder.chIdCheckBox.isChecked = true
            holder.chIdCheckBox.setBackgroundColor(Color.parseColor("#1E4884"))
            holder.cardData.setBackgroundResource(R.drawable.bg_cornercheckbox)
            holder.chIdCheckBox.visibility = View.VISIBLE
        }
        else
        {
            holder.chIdCheckBox.isChecked = false
            holder.chIdCheckBox.setBackgroundColor(Color.parseColor("#FF03DAC5"))
            holder.cardData.setBackgroundResource(R.drawable.bg_cornercheckboxwhite)
            holder.chIdCheckBox.visibility = View.GONE
        }

        holder.cardData.setOnClickListener {

            holder.cardData.setBackgroundResource(R.drawable.bg_cornercheckboxwhite)

            getItemSeleted(true)
            getImgSelected(slist.get(position).id,slist.get(position).iconUrl)

            for(i in slist.indices)
            {
                holder.chIdCheckBox.isChecked = false
                slist[i].isSelected=false
                checkArray[i].visibility=View.GONE
                holder.cardData.setBackgroundResource(R.drawable.bg_cornercheckboxwhite)
            }

            holder.chIdCheckBox.isChecked=true
            holder.cardData.setBackgroundResource(R.drawable.bg_cornercheckbox)
            holder.chIdCheckBox.visibility = View.VISIBLE
            slist[position].isSelected = true

          notifyDataSetChanged()

        }

    }



    override fun getItemCount(): Int {
        return slist.size
    }

}




