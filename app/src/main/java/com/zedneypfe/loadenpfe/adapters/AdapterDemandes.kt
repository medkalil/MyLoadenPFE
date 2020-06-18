package com.zedneypfe.loadenpfe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zedneypfe.loadenpfe.Model.mesDemandes.Result
import com.zedneypfe.loadenpfe.R
import kotlinx.android.synthetic.main.one_demande_inlist.view.*

class AdapterDemandes(var mylist: List<Result> , val clickListener: (Result) -> Unit) :
    RecyclerView.Adapter<AdapterDemandes.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


            //change the id of statu_demande in the item_demande :one_demande_inlist
            val statu_demande = itemView.statut_dem
            val id_demande = itemView.id_dem
            val date_demande = itemView.date_dem
            val type_demande = itemView.type_dem
        fun bind(res: Result, clickListener: (Result) -> Unit) {
            itemView.setOnClickListener { clickListener(res)}
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.one_demande_inlist, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val demande = mylist.get(position)
        holder.statu_demande.text = demande.STAGE_NAME
        holder.id_demande.text = demande.ID
        holder.date_demande.text = demande.BEGINDATE
        holder.type_demande.text = demande.TYPE_NAME

        holder.bind(demande, clickListener)

    }
}