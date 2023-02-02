package com.example.aris4autism_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.model.DiagnosisModel

class DiagnosAdapter(var slist:ArrayList<DiagnosisModel>):RecyclerView.Adapter<DiagnosAdapter.DiagnosView>() {


    class DiagnosView(itemVIew:View):RecyclerView.ViewHolder(itemVIew)
    {
        var txName:TextView=itemVIew.findViewById(R.id.txItemDiag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiagnosView {
        return DiagnosView(LayoutInflater.from(parent.context).inflate(R.layout.dignosis_layout_item,parent,false))
    }

    override fun onBindViewHolder(holder: DiagnosView, position: Int) {
            holder.txName.text=slist.get(position).name

    }

    override fun getItemCount(): Int {
        return slist.size
    }


}