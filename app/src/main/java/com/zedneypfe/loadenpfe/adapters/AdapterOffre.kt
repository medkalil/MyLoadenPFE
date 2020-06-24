package com.zedneypfe.loadenpfe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zedneypfe.loadenpfe.Model.getoffres.QUOTES
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.data.Offre
import java.util.ArrayList

class AdapterOffre(var listeOffre: List<QUOTES>) :
    RecyclerView.Adapter<AdapterOffre.Viewholder>() {

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title_offre = itemView.findViewById<TextView>(R.id.title_offre)
        val cout_offre = itemView.findViewById<TextView>(R.id.cout_offre)
        val id_offre = itemView.findViewById<TextView>(R.id.id_offre)
        val date_offre = itemView.findViewById<TextView>(R.id.date_offre)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.one_offre_inlist, parent, false)
        return Viewholder(v)
    }

    override fun getItemCount(): Int {
        return listeOffre.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val offre = listeOffre[position]

        //change to title ligne  : holder.title_offre?.text = offre.donneur.name
        holder.title_offre?.text = offre.TITLE
        holder.cout_offre?.text = offre.OPPORTUNITY
        holder.id_offre?.text = offre.ID
        holder.date_offre?.text = offre.BEGINDATE
    }
}