package com.zedneypfe.loadenpfe.fragments.client

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zedneypfe.loadenpfe.Model.mesDemandes.MesDemande
import com.zedneypfe.loadenpfe.Model.mesDemandes.Result
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.adapters.AdapterDemandes
import com.zedneypfe.loadenpfe.storage.SharedPrefManager
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_mesdemandes.*
import kotlinx.android.synthetic.main.fragment_my_account.*
import kotlinx.android.synthetic.main.one_demande_inlist.*


class MesDemandesFragment : Fragment() {

    private lateinit var viewModel: MesDemandesViewModel


    private lateinit var demandeadapter: AdapterDemandes


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    /* private fun initAdapter() {
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
     }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_mesdemandes, container, false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MesDemandesViewModel::class.java)


        //phone from sharedPref
        val phone: String =
            SharedPrefManager.getInstance(requireActivity().applicationContext).phone


        fun partItemClicked(res: Result) {

            //passing to the  detailFragment
            setFragment(DetailsDemandeFragment.DetailsDemandeFragmentInstance(res.ID))
        }

        progress_bar_demandes.visibility=View.VISIBLE

        viewModel.getDemandes(phone)

        viewModel.list_getted.observe(viewLifecycleOwner, Observer {

            //initialise the adapter her with the list_getted List+ notifysetDataChanges()

            val llm = LinearLayoutManager(requireContext())
            llm.orientation = LinearLayoutManager.VERTICAL
            list_demandes.layoutManager = llm
            demandeadapter = AdapterDemandes(it, { res: Result -> partItemClicked(res) })
            list_demandes.adapter = demandeadapter
            demandeadapter.notifyDataSetChanged()

            viewModel.process_mesdemandes.observe(viewLifecycleOwner, Observer {
                if (it==true){
                    progress_bar_demandes.visibility=View.GONE
                }
            })

           // statut_dem.setTextColor(resources.getColor())
            //println(demandeadapter.stat)

           /*  when (demandeadapter.stat) {
                 "تم الإتصال والتأكد من الطلب" -> {
                     statut_dem?.setTextColor(Color.parseColor("#FFFF00"))
                     couleur_status?.setBackgroundColor(ContextCompat.getColor(requireContext().applicationContext,R.color.talab_jadid))
                     }
             }*/


        })



    }

    //requireFragmentManager -> for fragments
    private fun setFragment(fragment: Fragment) {
        val ft = requireFragmentManager().beginTransaction()
        ft.replace(R.id.container_fragm, fragment).addToBackStack(null)
        ft.commit()
    }



}
