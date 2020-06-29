package com.zedneypfe.loadenpfe.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zedneypfe.loadenpfe.Model.mesDemandes.Result
import com.zedneypfe.loadenpfe.R
import kotlinx.android.synthetic.main.one_demande_inlist.view.*
import kotlinx.android.synthetic.main.one_lesdemandes_inlist.view.*

class AdapterLesDemandes(var mylist: List<Result>, val clickListener: (Result) -> Unit) :
    RecyclerView.Adapter<AdapterLesDemandes.ViewHolder>() {

    var stat: String? = ""

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        //change the id of statu_demande in the item_demande :one_demande_inlist
        val statu_demande = itemView.statut_dem_prov
        val id_demande = itemView.id_dem_prov
        val date_demande = itemView.date_dem_prov
        val type_demande = itemView.type_dem_prov

        val couleur_status=itemView.couleur_status_prov

        fun bind(res: Result, clickListener: (Result) -> Unit) {
            itemView.setOnClickListener { clickListener(res) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.one_lesdemandes_inlist, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val demande = mylist.sortedByDescending { it.ID }.get(position)


        //Formating the date
        val dem = demande.BEGINDATE.subSequence(0, 10)

        //test Color
        stat = demande.STAGE_NAME
        println(stat)


        holder.statu_demande.text = demande.STAGE_NAME
        holder.id_demande.text = demande.ID

        //holder.date_demande.text = demande.BEGINDATE
        holder.date_demande.text = dem

        holder.type_demande.text = demande.TYPE_NAME

        holder.bind(demande, clickListener)

        //set statut TextColor
        when (stat) {
            "طلب جديد" -> {
                holder.statu_demande.setTextColor(Color.parseColor("#3bc8f5"))
                holder.couleur_status.setBackgroundColor(Color.parseColor("#3bc8f5"))
            }
            "تم الإتصال والتأكد من الطلب" -> {
                holder.statu_demande.setTextColor(Color.parseColor("#ffed9a"))
                holder.couleur_status.setBackgroundColor(Color.parseColor("#ffed9a"))
            }
            "التأكد من قبول العرض" -> {
                holder.statu_demande.setTextColor(Color.parseColor("#daa187"))
                holder.couleur_status.setBackgroundColor(Color.parseColor("#daa187"))
            }
            "تم الإتفاق" -> {
                holder.statu_demande.setTextColor(Color.parseColor("#47e4c2"))
                holder.couleur_status.setBackgroundColor(Color.parseColor("#47e4c2"))
            }
            "تم النقل" -> {
                holder.statu_demande.setTextColor(Color.parseColor("#ff00ff"))
                holder.couleur_status.setBackgroundColor(Color.parseColor("#ff00ff"))
            }
            "متابعة إستلام العمولة من المزود" -> {
                holder.statu_demande.setTextColor(Color.parseColor("#ffa900"))
                holder.couleur_status.setBackgroundColor(Color.parseColor("#ffa900"))
            }
            "تم إستلام العمولة من المزود" -> {
                holder.statu_demande.setTextColor(Color.parseColor("#7bd500"))
                holder.couleur_status.setBackgroundColor(Color.parseColor("#7bd500"))
            }


        }
    }





}