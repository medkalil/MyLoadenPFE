package com.zedneypfe.loadenpfe.fragments.client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.adapters.AdapterDemandes
import com.zedneypfe.loadenpfe.data.Demande
import com.zedneypfe.loadenpfe.databinding.FragmentMesdemandesBinding
import kotlinx.android.synthetic.main.fragment_mesdemandes.*
import java.lang.Exception
import java.util.ArrayList


class MesDemandesFragment : Fragment() {
    private lateinit var demandeadapter: AdapterDemandes


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun initAdapter() {
        val demandeArray = ArrayList<Demande>()
        demandeArray.add(Demande(1584, "12/05/2020", "نقل عفش", "تم ارسال الطلب لامزودين"))
        demandeArray.add(Demande(1584, "12/05/2020", "نقل عفش", "تم ارسال الطلب لامزودين"))
        demandeArray.add(Demande(1584, "12/05/2020", "نقل عفش", "تم ارسال الطلب لامزودين"))
        demandeArray.add(Demande(1584, "12/05/2020", "نقل عفش", "تم ارسال الطلب لامزودين"))
        demandeArray.add(Demande(1584, "12/05/2020", "نقل عفش", "تم ارسال الطلب لامزودين"))
        demandeArray.add(Demande(1584, "12/05/2020", "نقل عفش", "تم ارسال الطلب لامزودين"))

        val llm = LinearLayoutManager(requireContext())
        llm.orientation = LinearLayoutManager.VERTICAL
        list_demandes.layoutManager = llm
        demandeadapter = AdapterDemandes(demandeArray)
        list_demandes.adapter = demandeadapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mesdemandes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

    }

}
