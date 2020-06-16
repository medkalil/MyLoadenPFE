package com.zedneypfe.loadenpfe.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zedneypfe.loadenpfe.Communicator
import com.zedneypfe.loadenpfe.Model.authModel
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.network.ApiService
import com.zedneypfe.loadenpfe.network.KEY
import com.zedneypfe.loadenpfe.network.retrofit
import com.zedneypfe.loadenpfe.storage.SharedPrefManager
import kotlinx.android.synthetic.main.fragment_signin.*
import retrofit2.Call
import retrofit2.Response


class SignInFragment : Fragment() {

    lateinit var comm: Communicator

    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_signin, container, false)



        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Declaring the viewmodel
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)

        comm = activity as Communicator

        //will not take me to the verifFragment intel it not empty
        sign_in_btn?.setOnClickListener {
            if (sign_in_number.text!!.isNotEmpty() && sign_in_number.text.length == 10) {

                //format the phone to this format: (966) 555555555
                //when sending it
                val phone_formated: String = "(966) " + sign_in_number.text.toString()

                //  SharedPrefManager.getInstance(requireActivity().applicationContext).save_phone(phone_formated)

                viewModel.getresp(phone_formated)

                viewModel.phone_existed.observe(viewLifecycleOwner, Observer {
                    println(it)
                   // println("phone doesn't exist")
                    if (it == true) {

                        viewModel.res.observe(viewLifecycleOwner, Observer {
                            comm.passDataCom(it, phone_formated)
                        })

                    } else if( it ==false) {
                        sign_in_number?.error = getString(R.string.phone_not_existed)
                    }

                })//viewModel.phone_existed.observe


                // setFragment(VerifSignInFragment())
            } else {
                sign_in_number?.error = getString(R.string.phone_check)
            }
        }//sign_in_btn?.setOnClickListener

    }


    //requireFragmentManager -> for fragments
    private fun setFragment(fragment: Fragment) {
        val ft = requireFragmentManager().beginTransaction()
        ft.replace(R.id.container_fragm, fragment)
        ft.commit()
    }


}