package com.zedneypfe.loadenpfe.fragments

import android.app.AlertDialog
import android.app.Application
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zedneypfe.loadenpfe.MainActivity
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.storage.SharedPrefManager
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_my_account.*

class MyAccountFragment() : Fragment() {


    //val progressbar=Dialog(requireActivity(),android.R.style.Theme_Translucent_NoTitleBar)
   //lateinit var dialog: AlertDialog

    private lateinit var viewModel: MyAccountViewModel

    var phone_formated:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v=inflater.inflate(R.layout.fragment_my_account, container, false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        viewModel = ViewModelProvider(this).get(MyAccountViewModel::class.java)

        val phone: String =
            SharedPrefManager.getInstance(requireContext().applicationContext).phone


    /*   val view=layoutInflater.inflate(R.layout.progressbar_loading,null)
        progressbar.setContentView(view)
        progressbar.setCancelable(false)
        progressbar.show()*/

        //dialog = SpotsDialog.Builder().setCancelable(false).setContext(requireActivity()).build()


       // dialog.show()

        progress_bar_myaccount.visibility=View.VISIBLE

        viewModel.getaccountinfo(phone)
        println(phone)

        //name
        viewModel.name.observe(viewLifecycleOwner, Observer {
            accouount_name.setText(it)
        })
        //last name
        viewModel.last_name.observe(viewLifecycleOwner, Observer {
            accouount_last_name.setText(it)
        })
        //phone
        viewModel.phone_getted.observe(viewLifecycleOwner, Observer {

            phone_formated=it.removePrefix("(966) ")
            accouount_phone.setText(phone_formated)
        })
        //email
        viewModel.email_getted.observe(viewLifecycleOwner, Observer {
            accouount_email.setText(it)
        })

        viewModel.proccess_myaccount.observe(viewLifecycleOwner, Observer {
            if (it==true)
                progress_bar_myaccount.visibility=View.GONE
        })


    }//onViewCreated


}