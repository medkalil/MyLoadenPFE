package com.zedneypfe.loadenpfe.fragments.client

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.databinding.FragmentDetailsdemandeBinding
import com.zedneypfe.loadenpfe.fragments.VerifSignInFragment
import kotlinx.android.synthetic.main.fragment_detailsdemande.*

class DetailsDemandeFragment : Fragment() {

    private lateinit var viewModel: DetailsDemandeViewModel


    var id_passed: String? = ""
    var beg:String?=""

    companion object {
        fun DetailsDemandeFragmentInstance(id: String): DetailsDemandeFragment {
            val instance = DetailsDemandeFragment()
            val bd = Bundle()
            bd.putString("id", id)
            instance.arguments = bd
            return instance
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailsdemande, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //passing the id to meke the call
        id_passed = arguments?.getString("id")

        viewModel = ViewModelProvider(this).get(DetailsDemandeViewModel::class.java)

        viewModel.getDetailsDemande(id_passed.toString())

        viewModel.detail_getted.observe(viewLifecycleOwner, Observer {

            beg= it.BEGINDATE.subSequence(0, 10).toString()

            id_dem_forcleint.text = it.ID
            type_dem_forcleint.text = it.TYPE_NAME

            date_dem_forcleint.text=beg

            distance_dem_forcleint.text=it.UF_CRM_1589924369
            duree_dem_forcleint.text=it.UF_CRM_1589924392
            statut_dem_forcleint.text=it.STAGE_NAME


            when(it.STAGE_NAME){
                "طلب جديد"-> {
                    statut_dem_forcleint.setTextColor(Color.parseColor("#3bc8f5"))
                    linea_2_forclient.setBackgroundColor(Color.parseColor("#3bc8f5"))
                }
                "تم الإتصال والتأكد من الطلب"-> {
                    statut_dem_forcleint.setTextColor(Color.parseColor("#ffed9a"))
                    linea_2_forclient.setBackgroundColor(Color.parseColor("#ffed9a"))
                }
                "التأكد من قبول العرض"-> {
                    statut_dem_forcleint.setTextColor(Color.parseColor("#daa187"))
                    linea_2_forclient.setBackgroundColor(Color.parseColor("#daa187"))
                }
                "تم الإتفاق"-> {
                    statut_dem_forcleint.setTextColor(Color.parseColor("#47e4c2"))
                    linea_2_forclient.setBackgroundColor(Color.parseColor("#47e4c2"))
                }
                "تم النقل"-> {
                    statut_dem_forcleint.setTextColor(Color.parseColor("#ff00ff"))
                    linea_2_forclient.setBackgroundColor(Color.parseColor("#ff00ff"))
                }
                "متابعة إستلام العمولة من المزود"-> {
                    statut_dem_forcleint.setTextColor(Color.parseColor("#ffa900"))
                    linea_2_forclient.setBackgroundColor(Color.parseColor("#ffa900"))
                }
                "تم إستلام العمولة من المزود"-> {
                    statut_dem_forcleint.setTextColor(Color.parseColor("#7bd500"))
                    linea_2_forclient.setBackgroundColor(Color.parseColor("#7bd500"))
                }


            }



        })


    }
}