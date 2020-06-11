package com.zedneypfe.loadenpfe.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zedneypfe.loadenpfe.Model.authModel
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.network.ApiService
import com.zedneypfe.loadenpfe.network.KEY
import com.zedneypfe.loadenpfe.network.retrofit
import kotlinx.android.synthetic.main.fragment_signin.*
import retrofit2.Call
import retrofit2.Response


class SignInFragment : Fragment() {

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

        /*   if (sign_in_number.text.isEmpty()) {
               sign_in_number?.error = "entre le phone number"
           }*/
        sign_in_btn?.setOnClickListener {
            if (sign_in_number.text!!.isNotEmpty()){
                viewModel.getresp(sign_in_number.text.toString())
                setFragment(VerifSignInFragment())
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


    //check responce of the key after login
    /*internal fun getresp() {

        //KEY is a cont from
        val service = retrofit.create(ApiService::class.java)

        //TODO  : pass sign_in_number.text
        //  to this fragment(VerifSigninFragment)
        val call = service.getcode(KEY, sign_in_number.text.toString())

        call.enqueue(object : retrofit2.Callback<authModel> {

            override fun onFailure(call: Call<authModel>, t: Throwable) {
                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<authModel>, response: Response<authModel>) {
                //SharedPrefManager.getInstance(context!!).saveUser(response.body()!!)
                //SharedPrefManager.getInstance(context!!).clear()


                println("ok")
                println(response.body()!!)
                //  Toast.makeText(getCp, "success", Toast.LENGTH_SHORT).show()
            }
        })//enqueue

    }*/

}
