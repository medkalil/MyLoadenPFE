package com.zedneypfe.loadenpfe.fragments.client

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.databinding.FragmentDetailsdemandeBinding
import com.zedneypfe.loadenpfe.fragments.VerifSignInFragment
import com.zedneypfe.loadenpfe.fragments.provider.DetailsDemandeProviderViewModel
import kotlinx.android.synthetic.main.fragment_detailsdemande.*

class DetailsDemandeFragment : Fragment() {

    private lateinit var viewModel: DetailsDemandeViewModel

    private lateinit var mMapView_tahmil_client: MapView

    private lateinit var mMapView_tanzil_client: MapView


    var id_passed: String? = ""
    var beg: String? = ""


    var tahmil: String? = ""
    var taslim: String? = ""

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

        mMapView_tahmil_client.onCreate(mapViewBundle)
        mMapView_tahmil_client.getMapAsync(onMapReadyCallback1())

        mMapView_tanzil_client.onCreate(mapViewBundle)
        mMapView_tanzil_client.getMapAsync(onMapReadyCallback2())
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle(getString(R.string.map_key))
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(getString(R.string.map_key), mapViewBundle)
        }
        mMapView_tahmil_client.onSaveInstanceState(mapViewBundle)
        mMapView_tanzil_client.onSaveInstanceState(mapViewBundle)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailsdemande, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mMapView_tahmil_client = mapView_tahmil_forclient
        mMapView_tanzil_client = mapView_tanzil_forclient



        initGoogleMap(savedInstanceState)


        //passing the id to meke the call
        id_passed = arguments?.getString("id")

        viewModel = ViewModelProvider(this).get(DetailsDemandeViewModel::class.java)

        viewModel.getDetailsDemande(id_passed.toString())

        viewModel.detail_getted.observe(viewLifecycleOwner, Observer {

            beg = it.BEGINDATE.subSequence(0, 10).toString()

            id_dem_forcleint.text = it.ID
            type_dem_forcleint.text = it.TYPE_NAME

            date_dem_forcleint.text = beg

            distance_dem_forcleint.text = it.UF_CRM_1589924369
            duree_dem_forcleint.text = it.UF_CRM_1589924392
            statut_dem_forcleint.text = it.STAGE_NAME


            // tahmil=it.UF_CRM_1589924259.subSequence(0,64).toString()
            tahmil = it.UF_CRM_1589924259.split("|").get(0)
            taslim = it.UF_CRM_1589924283.split("|").get(0)

            println(tahmil)

            place_lieu_ta7mil_forclient.text = tahmil
            place_lieu_tanzil_forclient.text = taslim


            when (it.STAGE_NAME) {
                "طلب جديد" -> {
                    statut_dem_forcleint.setTextColor(Color.parseColor("#3bc8f5"))
                    linea_2_forclient.setBackgroundColor(Color.parseColor("#3bc8f5"))
                }
                "تم الإتصال والتأكد من الطلب" -> {
                    statut_dem_forcleint.setTextColor(Color.parseColor("#ffed9a"))
                    linea_2_forclient.setBackgroundColor(Color.parseColor("#ffed9a"))
                }
                "التأكد من قبول العرض" -> {
                    statut_dem_forcleint.setTextColor(Color.parseColor("#daa187"))
                    linea_2_forclient.setBackgroundColor(Color.parseColor("#daa187"))
                }
                "تم الإتفاق" -> {
                    statut_dem_forcleint.setTextColor(Color.parseColor("#47e4c2"))
                    linea_2_forclient.setBackgroundColor(Color.parseColor("#47e4c2"))
                }
                "تم النقل" -> {
                    statut_dem_forcleint.setTextColor(Color.parseColor("#ff00ff"))
                    linea_2_forclient.setBackgroundColor(Color.parseColor("#ff00ff"))
                }
                "متابعة إستلام العمولة من المزود" -> {
                    statut_dem_forcleint.setTextColor(Color.parseColor("#ffa900"))
                    linea_2_forclient.setBackgroundColor(Color.parseColor("#ffa900"))
                }
                "تم إستلام العمولة من المزود" -> {
                    statut_dem_forcleint.setTextColor(Color.parseColor("#7bd500"))
                    linea_2_forclient.setBackgroundColor(Color.parseColor("#7bd500"))
                }


            }


        })



        show_offres.setOnClickListener {
            //setFragment(DetailsDemandeFragment.DetailsDemandeFragmentInstance(res.ID))

            setFragment(MesOffresFragment.MesOffresFragmentInstance(id_passed.toString()))
        }

    }


    private fun setFragment(fragment: Fragment) {
        val ft = requireFragmentManager().beginTransaction()
        ft.replace(R.id.container_fragm, fragment).addToBackStack(null)
        ft.commit()
    }//setFragment



    //MAP1 : tahmil
    fun onMapReadyCallback1(): OnMapReadyCallback? {
        return OnMapReadyCallback { googleMap ->

            viewModel = ViewModelProvider(this).get(DetailsDemandeViewModel::class.java)

            viewModel.cordoner_tahmil_client.observe(viewLifecycleOwner, Observer {

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

            viewModel = ViewModelProvider(this).get(DetailsDemandeViewModel::class.java)

            viewModel.cordoner_tanzil_client.observe(viewLifecycleOwner, Observer {

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
        mMapView_tahmil_client.onResume()
        mMapView_tanzil_client.onResume()
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView_tahmil_client.onPause()
        mMapView_tanzil_client.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView_tahmil_client.onDestroy()
        mMapView_tanzil_client.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView_tahmil_client.onLowMemory()
        mMapView_tanzil_client.onLowMemory()
    }



}