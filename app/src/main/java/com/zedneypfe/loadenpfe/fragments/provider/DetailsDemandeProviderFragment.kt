package com.zedneypfe.loadenpfe.fragments.provider

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zedneypfe.loadenpfe.MainActivity
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.fragments.client.DetailsDemandeFragment
import com.zedneypfe.loadenpfe.fragments.client.DetailsDemandeViewModel
import kotlinx.android.synthetic.main.details_demande_forprovider.*
import kotlinx.android.synthetic.main.fragment_detailsdemande.*

class DetailsDemandeProviderFragment : Fragment() {

    private lateinit var alertDialog: AlertDialog


    private lateinit var viewModel: DetailsDemandeProviderViewModel

    var id_passed: String? = ""
    var beg: String? = ""

    var tahmil: String? = ""
    var taslim: String? = ""


    companion object {
        fun DetailsDemandeProviderFragmentInstance(id: String): DetailsDemandeProviderFragment {
            val instance = DetailsDemandeProviderFragment()
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
        return inflater.inflate(R.layout.details_demande_forprovider, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        id_passed = arguments?.getString("id")

        viewModel = ViewModelProvider(this).get(DetailsDemandeProviderViewModel::class.java)

        viewModel.getDetailsDemande(id_passed.toString())

        viewModel.detail_getted.observe(viewLifecycleOwner, Observer {


            beg = it.BEGINDATE.subSequence(0, 10).toString()

            id_dem_forprovider.text = it.ID

            type_dem_forprovider.text = it.TYPE_NAME

            date_dem_forprovider.text = beg

            distance_dem_forprovider.text = it.UF_CRM_1589924369
            duree_dem_forprvider.text = it.UF_CRM_1589924392
            statut_dem_forprovider.text = it.STAGE_NAME


            tahmil = it.UF_CRM_1589924259.split("|").get(0)
            taslim = it.UF_CRM_1589924283.split("|").get(0)

            place_lieu_ta7mil_forprovider.text = tahmil
            place_lieu_tanzil_forprovider.text = taslim


            //........................................IF there is a BUG it from HERE OR in the same in DetailDemandeFragment .....................
            when (it.STAGE_NAME) {
                "طلب جديد" -> {
                    statut_dem_forprovider.setTextColor(Color.parseColor("#3bc8f5"))
                    linea_2_forprovider.setBackgroundColor(Color.parseColor("#3bc8f5"))
                }
                "تم الإتصال والتأكد من الطلب" -> {
                    statut_dem_forprovider.setTextColor(Color.parseColor("#ffed9a"))
                    linea_2_forprovider.setBackgroundColor(Color.parseColor("#ffed9a"))
                }
                "التأكد من قبول العرض" -> {
                    statut_dem_forprovider.setTextColor(Color.parseColor("#daa187"))
                    linea_2_forprovider.setBackgroundColor(Color.parseColor("#daa187"))
                }
                "تم الإتفاق" -> {
                    statut_dem_forprovider.setTextColor(Color.parseColor("#47e4c2"))
                    linea_2_forprovider.setBackgroundColor(Color.parseColor("#47e4c2"))
                }
                "تم النقل" -> {
                    statut_dem_forprovider.setTextColor(Color.parseColor("#ff00ff"))
                    linea_2_forprovider.setBackgroundColor(Color.parseColor("#ff00ff"))
                }
                "متابعة إستلام العمولة من المزود" -> {
                    statut_dem_forprovider.setTextColor(Color.parseColor("#ffa900"))
                    linea_2_forprovider.setBackgroundColor(Color.parseColor("#ffa900"))
                }
                "تم إستلام العمولة من المزود" -> {
                    statut_dem_forprovider.setTextColor(Color.parseColor("#7bd500"))
                    linea_2_forprovider.setBackgroundColor(Color.parseColor("#7bd500"))
                }

                else -> { //dont set the text and backgroun color
                }

            }//when


        })


        send_offre_btn.setOnClickListener {
            //showDialog()
            showCustomDialog()





        }//send_offre_btn.setOnClickListener

    }//OnViewCreated


  /*  fun showDialog() {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setMessage("The message here")
        dialogBuilder.setPositiveButton("Done",
            DialogInterface.OnClickListener { dialog, whichButton -> })
        val b = dialogBuilder.create()
        b.show()
    }*/

    fun showCustomDialog() {
        val inflater: LayoutInflater = this.getLayoutInflater()
        val dialogView: View = inflater.inflate(R.layout.dialog_send_offre, null)


        val custom_button: Button = dialogView.findViewById(R.id.send_dialog_btn)
        custom_button.setOnClickListener {
            //perform custom action
            alertDialog.dismiss()
        }
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        dialogBuilder.setOnDismissListener(object : DialogInterface.OnDismissListener {
            override fun onDismiss(arg0: DialogInterface) {


            }
        })
        dialogBuilder.setView(dialogView)

        alertDialog = dialogBuilder.create()
        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.show()
    }



    /*   val view =
       View.inflate(requireContext().applicationContext, R.layout.dialog_send_offre, null)
   val builder=AlertDialog.Builder(requireContext().applicationContext)
   builder.setView(view)

   val dialog=builder.create()
   dialog.show()
  dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)*/

}