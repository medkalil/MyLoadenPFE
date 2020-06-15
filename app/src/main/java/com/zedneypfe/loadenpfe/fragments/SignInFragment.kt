package com.zedneypfe.loadenpfe.fragments

import android.content.Intent
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
import com.zedneypfe.loadenpfe.MainActivity
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
                /*  val phone_formated:String="(966) "+sign_in_number.text.toString()
                  comm.passDataCom(phone_formated)
                  SharedPrefManager.getInstance(requireActivity().applicationContext).save_phone(phone_formated)*/

                val phone_formated:String="(966) " + sign_in_number.text.toString()

                viewModel.getresp(phone_formated)

                viewModel.phone_exist.observe(viewLifecycleOwner, Observer {
                    if (it == true) {

                        //save the user in the SharedPrefrences
                        viewModel.au.observe(viewLifecycleOwner, Observer {
                            //  SharedPrefManager.getInstance(requireContext().applicationContext).saveUser(it)
                            comm.passDataCom(it.verif_code,phone_formated)

                        })

                        //setFragment(VerifSignInFragment())

                    } else {
                        //if no phone doesin't exist
                        sign_in_number?.error = getString(R.string.phone_entered_check)
                    }
                })

                // setFragment(VerifSignInFragment())

            } else {
                //if the format of the phone is wrong
                sign_in_number?.error = getString(R.string.phone_entered_check)
            }
        }

    }


    //requireFragmentManager -> for fragments
    private fun setFragment(fragment: Fragment) {
        val ft = requireFragmentManager().beginTransaction()
        ft.replace(R.id.container_fragm, fragment)
        ft.commit()
    }


}
