package com.zedneypfe.loadenpfe.fragments

import android.content.AsyncQueryHandler
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
import com.zedneypfe.loadenpfe.storage.SharedPrefManager
import kotlinx.android.synthetic.main.fragment_signin.*
import kotlinx.android.synthetic.main.fragment_verif_sign_in.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay as delay1


class VerifSignInFragment : Fragment() {

    private lateinit var viewModel: VerifSignInViewModel


    var code_passed: String? = ""
    var phone_passed: String? = ""

    var phon:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_verif_sign_in, container, false)

        phone_passed = arguments?.getString("phone")
        code_passed = arguments?.getString("code")
        //to check the format of the phone recived as argument
        //println(phone_passed)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(VerifSignInViewModel::class.java)


        viewModel.getaccountinfo(phone_passed.toString())

        viewModel.phone_getted_toSave.observe(viewLifecycleOwner, Observer {

            phon=it
            println(it)
        })


        veri_btn?.setOnClickListener {


            if (code_verif.text.toString() == code_passed && code_verif.text.isNotEmpty() && code_verif.text.length == 4) {


                SharedPrefManager.getInstance(requireContext().applicationContext)
                    .save_phone(phon.toString())


                //change the phone in the shared prefrences for the phone getted with getContactApi(Key,phone_passed)
                //   SharedPrefManager.getInstance(requireContext().applicationContext).save_phone(phone_passed.toString())

                /*viewModel.getaccountinfo(phone_passed.toString())

              viewModel.phone_getted_toSave.observe(viewLifecycleOwner, Observer {
                  SharedPrefManager.getInstance(requireContext().applicationContext)
                      .save_phone(it!!)

                  println(it)
              })*/


                val intent = Intent(getActivity(), MainActivity::class.java)
                requireActivity().startActivity(intent)


            } else {
                code_verif?.error = getString(R.string.code_check)
            }


            /*   viewModel.res.observe(viewLifecycleOwner, Observer {
                if (it == code_verif.text.toString()) {


                    //save the user
                   /* viewModel.au.observe(viewLifecycleOwner, Observer {
                         SharedPrefManager.getInstance(requireActivity().applicationContext)
                             .saveUser(it)
                    })*/

                    val intent = Intent(getActivity(), MainActivity::class.java)
                    requireActivity().startActivity(intent)

                    Toast.makeText(
                        context, "Your Loged In",
                        Toast.LENGTH_LONG
                    ).show()

                    println("yes")
                } else {
                    Toast.makeText(
                        context, "Wrong code",
                        Toast.LENGTH_LONG
                    ).show()

                    println("NO")
                }
            })*/
        }//veri_btn.setOnClickListener


    }//onViewCreated

    //requireFragmentManager -> for fragments
    private fun setFragment(fragment: Fragment) {
        val ft = requireFragmentManager().beginTransaction()
        ft.replace(R.id.container_fragm, fragment)
        ft.commit()
    }


}