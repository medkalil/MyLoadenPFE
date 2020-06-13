package com.zedneypfe.loadenpfe.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
        viewModel=ViewModelProvider(this).get(SignInViewModel::class.java)

        comm = activity as Communicator

        //will not take me to the verifFragment intel it not empty
        sign_in_btn?.setOnClickListener {
            if (sign_in_number.text!!.isNotEmpty()){

                //format the phone to this format: (966) 555555555
                //when sending it
                comm.passDataCom("(966) "+sign_in_number.text.toString())

               // setFragment(VerifSignInFragment())
            }else{
                sign_in_number?.error="enter a valide phone number"
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
