package com.zedneypfe.loadenpfe.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.adapters.AdapterDemandes
import com.zedneypfe.loadenpfe.adapters.AdapterOffre
import com.zedneypfe.loadenpfe.data.Demande
import com.zedneypfe.loadenpfe.data.Fournisseur
import com.zedneypfe.loadenpfe.data.Offre
import com.zedneypfe.loadenpfe.databinding.FragmentMesoffresBinding
import kotlinx.android.synthetic.main.fragment_mesdemandes.*
import kotlinx.android.synthetic.main.fragment_mesoffres.*
import java.util.ArrayList

class MesOffresFragment : Fragment() {
    private lateinit var offreAdapter: AdapterOffre


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_mesoffres, container, false)
    }

    private fun initAdapter() {
        var offreArray =ArrayList<Offre>()
        offreArray.add(Offre(1584, Fournisseur("salah"), 140.5, "12/08/2020",Demande(1585, "12/05/2020", "نقل عفش", "تم ارسال الطلب لامزودين")))
        offreArray.add(Offre(158, Fournisseur("salah"), 140.5, "12/08/2020",Demande(155, "12/05/2020", "نقل عفش", "تم ارسال الطلب لامزودين")))
        offreArray.add(Offre(158, Fournisseur("salah"), 140.5, "12/08/2020",Demande(155, "12/05/2020", "نقل عفش", "تم ارسال الطلب لامزودين")))
        offreArray.add(Offre(158, Fournisseur("salah"), 140.5, "12/08/2020",Demande(155, "12/05/2020", "نقل عفش", "تم ارسال الطلب لامزودين")))
                offreArray.add(Offre(158, Fournisseur("salah"), 140.5, "12/08/2020",Demande(155, "12/05/2020", "نقل عفش", "تم ارسال الطلب لامزودين")))
            val lm = LinearLayoutManager(requireContext())
        lm.orientation = LinearLayoutManager.VERTICAL
        list_offres.layoutManager = lm
        offreAdapter = AdapterOffre(offreArray)
        list_offres.adapter = offreAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

    }
}