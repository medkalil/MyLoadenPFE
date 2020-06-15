package com.zedneypfe.loadenpfe.fragments

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


class VerifSignInFragment : Fragment() {

    private lateinit var viewModel: VerifSignInViewModel


    var code_passed: String? = ""
    var phone_formated:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_verif_sign_in, container, false)

        //pass the phone with argument between 2 Fragment

        /* phone_passed = arguments?.getString("input_txt")
         //to check the format of the phone recived as argument
          println(phone_passed)*/

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* val code_from_shared:String=SharedPrefManager.getInstance(requireContext().applicationContext).user.verif_code
        println(code_from_shared)*/

        //code passed on argument
        code_passed=arguments?.getString("code")
        phone_formated=arguments?.getString("phone")

        println(code_passed+" code passed")
        viewModel = ViewModelProvider(this).get(VerifSignInViewModel::class.java)



        veri_btn?.setOnClickListener {


            if (code_verif?.text!!.isNotEmpty() && code_verif?.text!!.length == 4 && code_verif?.text.toString()==code_passed) {


                //save the phone to make isLoggedIn=true
                SharedPrefManager.getInstance(requireContext().applicationContext).save_phone(phone_formated.toString())

               // println(SharedPrefManager.getInstance(requireContext().applicationContext).isLoggedIn)

                val intent = Intent(getActivity(), MainActivity::class.java)
                requireActivity().startActivity(intent)

                Toast.makeText(
                    context, "You've Logged In",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                code_verif?.error = getString(R.string.code_check)
            }

        }//veri_btn?.setOnClickListener














        //   viewModel.getresp(phone_passed.toString())


        /*    veri_btn.setOnClickListener {
                viewModel.res.observe(viewLifecycleOwner, Observer {
                    if (it == code_verif.text.toString()) {


                        //save the user
                        viewModel.au.observe(viewLifecycleOwner, Observer {
                             SharedPrefManager.getInstance(requireActivity().applicationContext)
                                 .saveUser(it)
                        })


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
                })
            }*/


    }//onViewCreated

    //requireFragmentManager -> for fragments
    private fun setFragment(fragment: Fragment) {
        val ft = requireFragmentManager().beginTransaction()
        ft.replace(R.id.container_fragm, fragment)
        ft.commit()
    }


}