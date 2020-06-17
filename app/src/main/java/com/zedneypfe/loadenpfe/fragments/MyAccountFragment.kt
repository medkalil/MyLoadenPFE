package com.zedneypfe.loadenpfe.fragments

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zedneypfe.loadenpfe.MainActivity
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.storage.SharedPrefManager
import kotlinx.android.synthetic.main.fragment_my_account.*

class MyAccountFragment : Fragment() {

    private lateinit var viewModel: MyAccountViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MyAccountViewModel::class.java)

        val phone: String =
            SharedPrefManager.getInstance(requireActivity().applicationContext).phone


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
            accouount_phone.setText(it)
        })
        //email
        viewModel.email_getted.observe(viewLifecycleOwner, Observer {
            accouount_email.setText(it)
        })

    }//onViewCreated


}