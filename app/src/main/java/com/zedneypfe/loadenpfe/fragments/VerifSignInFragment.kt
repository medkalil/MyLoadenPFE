package com.zedneypfe.loadenpfe.fragments

import android.R.attr.data
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zedneypfe.loadenpfe.MainActivity
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.fragments.client.EnvoyerDemandeFragment
import com.zedneypfe.loadenpfe.storage.SharedPrefManager
import kotlinx.android.synthetic.main.fragment_verif_sign_in.*


class VerifSignInFragment : Fragment() {

    private lateinit var viewModel: VerifSignInViewModel


    var phone_passed: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_verif_sign_in, container, false)

        phone_passed = arguments?.getString("input_txt")
        //  println(phone_passed)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(VerifSignInViewModel::class.java)

        viewModel.getresp(phone_passed.toString())


        veri_btn.setOnClickListener {
            viewModel.res.observe(viewLifecycleOwner, Observer {
                if (it == code_verif.text.toString()) {


                    //save the user
                    viewModel.au.observe(viewLifecycleOwner, Observer {
                        SharedPrefManager.getInstance(this.requireContext()).saveUser(it)
                        SharedPrefManager.getInstance(this.requireContext()).isLoggedIn=true
                    println(SharedPrefManager.getInstance(this.requireContext()).isLoggedIn)
                    })


                    //set the value of the boolean isLoggedIn to true
                   // SharedPrefManager.getInstance(requireContext()).isLoggedIn = true

                    val intent = Intent (getActivity(), MainActivity::class.java)
                    requireActivity().startActivity(intent)

                    Toast.makeText(
                        context, "Your Loged In",
                        Toast.LENGTH_LONG
                    ).show()

                    println("yes")
                } else {
                    SharedPrefManager.getInstance(requireContext()).isLoggedIn = false

                    Toast.makeText(
                        context, "Wrong code",
                        Toast.LENGTH_LONG
                    ).show()

                    println("NO")
                }
            })
        }


    }//onViewCreated

    //requireFragmentManager -> for fragments
    private fun setFragment(fragment: Fragment) {
        val ft = requireFragmentManager().beginTransaction()
        ft.replace(R.id.container_fragm, fragment)
        ft.commit()
    }


}