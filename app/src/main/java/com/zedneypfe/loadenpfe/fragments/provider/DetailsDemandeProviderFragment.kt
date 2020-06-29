package com.zedneypfe.loadenpfe.fragments.provider

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.zedneypfe.loadenpfe.MainActivity
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.storage.SharedPrefManager
import kotlinx.android.synthetic.main.details_demande_forprovider.*
import kotlinx.android.synthetic.main.dialog_send_offre.*
import kotlinx.android.synthetic.main.dialog_send_offre.view.*
import kotlinx.android.synthetic.main.fragment_detailsdemande.*


class DetailsDemandeProviderFragment : Fragment() {

    private lateinit var alertDialog: AlertDialog

    private lateinit var mMapView_tahmil: MapView

    private lateinit var mMapView_tanzil: MapView


    var phone: String = ""
     lateinit var cordoner:LatLng

    // lateinit var price_formated:String


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
        val v = inflater.inflate(R.layout.details_demande_forprovider, container, false)


        return v
    }

    private fun initGoogleMap(savedInstanceState: Bundle?) {
        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.

        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(getString(R.string.map_key))
        }

        mMapView_tahmil.onCreate(mapViewBundle)
        mMapView_tahmil.getMapAsync(onMapReadyCallback1())

        mMapView_tanzil.onCreate(mapViewBundle)
        mMapView_tanzil.getMapAsync(onMapReadyCallback2())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle(getString(R.string.map_key))
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(getString(R.string.map_key), mapViewBundle)
        }
        mMapView_tahmil.onSaveInstanceState(mapViewBundle)
        mMapView_tanzil.onSaveInstanceState(mapViewBundle)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.title=getString(R.string.detail_demande_title)


        mMapView_tahmil = mapView_tahmil_forprovider

        mMapView_tanzil = mapView_tanzil_forprovider



        initGoogleMap(savedInstanceState)




        id_passed = arguments?.getString("id")

        viewModel = ViewModelProvider(this).get(DetailsDemandeProviderViewModel::class.java)

        //show progress bar + diasble the touch event
        progress_bar_detail_provider.visibility = View.VISIBLE
        getActivity()?.getWindow()?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )


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

         /*   tahmil_lat = it.UF_CRM_1589924259.split("|").get(1).split(";").get(0).toDouble()

            tahmil_lang = it.UF_CRM_1589924259.split("|").get(1).split(";").get(1).toDouble()

            cordoner= LatLng(tahmil_lat!!.toDouble(),tahmil_lang!!.toDouble())*/

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


        viewModel.proccess_detail_provider.observe(viewLifecycleOwner, Observer {
            if (it==true)
                progress_bar_detail_provider.visibility = View.GONE
            getActivity()?.getWindow()?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

        })

        phone = SharedPrefManager.getInstance(requireContext().applicationContext).phone


        // price_formated=price?.text.toString()


        send_offre_btn.setOnClickListener {
            showCustomDialog()


        }//send_offre_btn.setOnClickListener

    }//OnViewCreated


    fun showCustomDialog() {
        val inflater: LayoutInflater = this.getLayoutInflater()
        val dialogView: View = inflater.inflate(R.layout.dialog_send_offre, null)


        val custom_button: Button = dialogView.findViewById(R.id.send_dialog_btn)
        custom_button.setOnClickListener {
            //perform custom action
            println(dialogView.price?.text)

            if (dialogView.price?.text.toString() == "") {

                price?.error = "Ivalide Value Entred"
                Toast.makeText(
                    requireContext().applicationContext,
                    "رقم غير صحيح",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (dialogView.price?.text.toString().toInt() <= 0) {
                price?.error = "Ivalide Value Entred"
                Toast.makeText(
                    requireContext().applicationContext,
                    "رقم غير صحيح",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (dialogView.price?.text.toString().toInt() >= 0) {
                viewModel.addQuote(
                    id_dem_forprovider.text.toString(),
                    phone,
                    dialogView.price?.text.toString().toInt()
                )

                alertDialog.dismiss()
            } else {
                price?.error = "Ivalide Value Entred"
                Toast.makeText(
                    requireContext().applicationContext,
                    "رقم غير صحيح",
                    Toast.LENGTH_SHORT
                ).show()
            }


        }
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        dialogBuilder.setOnDismissListener(object : DialogInterface.OnDismissListener {
            override fun onDismiss(arg0: DialogInterface) {


            }
        })
        dialogBuilder.setView(dialogView)

        alertDialog = dialogBuilder.create()



        alertDialog.setCancelable(true)
        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.show()
    }


    //MAP1 : tahmil
    fun onMapReadyCallback1(): OnMapReadyCallback? {
        return OnMapReadyCallback { googleMap ->

            viewModel = ViewModelProvider(this).get(DetailsDemandeProviderViewModel::class.java)

            viewModel.cordoner_tahmil.observe(viewLifecycleOwner, Observer {

                //formating "36.84910;10.19690" and getting the 2 double
                val location= LatLng(it.split(";").get(0).toDouble(),it.split(";").get(1).toDouble())
                googleMap.addMarker(MarkerOptions().position(location)).title = "من"
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
                // Add a marker in Sydney and move the camera
                /*  val sydney = LatLng(-34.0, 151.0)
                 mMapView.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
                 mMapView.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/

            })
        }
    }

    fun onMapReadyCallback2(): OnMapReadyCallback? {
        return OnMapReadyCallback { googleMap ->

            viewModel = ViewModelProvider(this).get(DetailsDemandeProviderViewModel::class.java)

            viewModel.cordoner_tanzil.observe(viewLifecycleOwner, Observer {

                //formating "36.84910;10.19690" and getting the 2 double
                val location= LatLng(it.split(";").get(0).toDouble(),it.split(";").get(1).toDouble())
                googleMap.addMarker(MarkerOptions().position(location)).title = "الى"
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
                // Add a marker in Sydney and move the camera
                /*  val sydney = LatLng(-34.0, 151.0)
                 mMapView.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
                 mMapView.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/

            })
        }
    }



    override fun onResume() {
        mMapView_tahmil.onResume()
        mMapView_tanzil.onResume()
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView_tahmil.onPause()
        mMapView_tanzil.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView_tahmil.onDestroy()
        mMapView_tanzil.onDestroy()

        getActivity()?.getWindow()?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView_tahmil.onLowMemory()
        mMapView_tanzil.onLowMemory()
    }


}

