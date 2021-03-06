package com.zedneypfe.loadenpfe.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
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

    var phone_formated:String?=""

    var user_type: String? = ""



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


        sign_in_number.requestFocus()
       // getActivity()?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

        (activity as MainActivity).supportActionBar?.title=getString(R.string.label_sign_in)


        //Declaring the viewmodel
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)

        comm = activity as Communicator

        //will not take me to the verifFragment intel it not empty
        sign_in_btn?.setOnClickListener {
            if (sign_in_number.text!!.isNotEmpty() && sign_in_number.text.length == 10) {

                //format the phone to this format: (966) 555555555
                //when sending it
                 phone_formated = "(966) " + sign_in_number.text.toString()

                println(phone_formated)
                //  SharedPrefManager.getInstance(requireActivity().applicationContext).save_phone(phone_formated)


                viewModel.getresp(phone_formated!!)

                viewModel.phone_existed.observe(viewLifecycleOwner, Observer {
                    println(it)
                    // println("phone doesn't exist")
                    if (it == true) {
                        //user type
                      /*  viewModel.user_type.observe(viewLifecycleOwner, Observer {
                            it
                            user_type = it.toString()
                            println(user_type)

                        })*/

                        //code + send
                        viewModel.code.observe(viewLifecycleOwner, Observer {
                            //  comm.passDataCom(it, phone_formated)
                            viewModel.user_type.observe(viewLifecycleOwner, Observer {
                                user_type = it.toString()
                                println(user_type)

                            })


                            setFragment(VerifSignInFragment.VerifSignInFragmentInstance(it,
                                phone_formated!!,
                                user_type.toString()
                            ))
                            println(phone_formated)

                        })

                    } else {
                        sign_in_number?.error = getString(R.string.phone_not_existed)
                    }

                })//viewModel.phone_existed.observe


                //TODO HERE :else if (sign_in_number.text.length == 9 for provider){même traitement}
                //we have 1 only provider with the phone 966555555554 (9 number)
                //soo in the test of 9 :we reformate the code before sending it in argument
            }else if(sign_in_number.text!!.isNotEmpty() && sign_in_number.text.length == 9){

                //format the phone to this format: (966) 555555555
                //when sending it
                phone_formated = "966" + sign_in_number.text.toString()

                println(phone_formated)
                //  SharedPrefManager.getInstance(requireActivity().applicationContext).save_phone(phone_formated)


                viewModel.getresp(phone_formated!!)

                viewModel.phone_existed.observe(viewLifecycleOwner, Observer {
                    println(it)
                    // println("phone doesn't exist")
                    if (it == true) {
                        //user type
                        /*  viewModel.user_type.observe(viewLifecycleOwner, Observer {
                              it
                              user_type = it.toString()
                              println(user_type)

                          })*/

                        //code + send
                        viewModel.code.observe(viewLifecycleOwner, Observer {
                            //  comm.passDataCom(it, phone_formated)
                            viewModel.user_type.observe(viewLifecycleOwner, Observer {
                                user_type = it.toString()
                                println(user_type)

                            })


                            setFragment(VerifSignInFragment.VerifSignInFragmentInstance(it,
                                phone_formated!!,
                                user_type.toString()
                            ))
                            println(phone_formated)

                        })

                    } else {
                        sign_in_number?.error = getString(R.string.phone_not_existed)
                    }

                })//viewModel.phone_existed.observe


            }else {
                sign_in_number?.error = getString(R.string.phone_check)
            }
        }//sign_in_btn?.setOnClickListener



    }//onViewCreated


    //requireFragmentManager -> for fragments
    private fun setFragment(fragment: Fragment) {
        val ft = requireFragmentManager().beginTransaction()
        ft.replace(R.id.container_fragm, fragment,"veriff")
        ft.commit()
    }


}