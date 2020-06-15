package com.zedneypfe.loadenpfe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.data.Demande
import kotlinx.android.synthetic.main.details_demande_forprovider.view.statu_dem
import kotlinx.android.synthetic.main.one_demande_inlist.view.*
import java.util.ArrayList

class AdapterDemandes(var mylist: ArrayList<Demande>) :
    RecyclerView.Adapter<AdapterDemandes.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val statu_demande = itemView.statu_dem
        val id_demande = itemView.id_dem
        val date_demande = itemView.findViewById<TextView>(R.id.date_dem)
        val type_demande = itemView.findViewById<TextView>(R.id.type_dem)

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
        holder.statu_demande.text = demande.status
        holder.id_demande.text = demande.id.toString()
        holder.date_demande.text = demande.date
        holder.type_demande.text = demande.type
    }
}