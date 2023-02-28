package com.example.aris4autism_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.DignosisSelectLayoutBinding
import com.example.aris4autism_project.model.DataXXXXXXXXXXXXXXXXXXXXXXXXX

class DiagnosisAdapter(var slist:ArrayList<DataXXXXXXXXXXXXXXXXXXXXXXXXX>):RecyclerView.Adapter<DiagnosisAdapter.DiagnosisViewData>() {

    class DiagnosisViewData(val binding: DignosisSelectLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataXXXXXXXXXXXXXXXXXXXXXXXXX) {
            binding.bindDiagnosis = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiagnosisViewData
    {
        return DiagnosisViewData(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.dignosis_select_layout,parent,false))
    }

    override fun onBindViewHolder(holder: DiagnosisViewData, position: Int) {
            holder.bind(slist[position])

        holder.binding.idDiagnosCheckBox.setOnClickListener {
           holder.binding.idConstarintLayoutDiagnosis.setBackgroundResource(R.drawable.bg_light_blue)
            holder.binding.idtxDiagnosisTitle.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.darkblue))
            //Toast.makeText(holder.itemView.context, slist.get(position).title, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
       return slist.size
    }
}