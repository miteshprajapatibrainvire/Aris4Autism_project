package com.example.aris4autism_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.DignosisLayoutItemBinding
import com.example.aris4autism_project.model.GetDiagnosisData


class DiagnosAdapter(var slist: ArrayList<GetDiagnosisData>?):RecyclerView.Adapter<DiagnosAdapter.DiagnosView>() {


    class DiagnosView(binding:DignosisLayoutItemBinding):RecyclerView.ViewHolder(binding.root)
    {
        var txName:TextView=binding.txItemDiag
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiagnosView {
        return DiagnosView(DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.dignosis_layout_item,parent,false))
    }

    override fun onBindViewHolder(holder: DiagnosView, position: Int) {
            holder.txName.text=slist!!.get(position).diagnosisTitle
    }

    override fun getItemCount(): Int {
        return slist!!.size
    }

}