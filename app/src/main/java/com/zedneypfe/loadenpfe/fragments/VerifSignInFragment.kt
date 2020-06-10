package com.zedneypfe.loadenpfe.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zedneypfe.loadenpfe.Model.authModel
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.network.ApiService
import com.zedneypfe.loadenpfe.network.KEY
import com.zedneypfe.loadenpfe.network.retrofit
import kotlinx.android.synthetic.main.fragment_signin.*
import kotlinx.android.synthetic.main.fragment_verif_sign_in.*
import retrofit2.Call
import retrofit2.Response


class VerifSignInFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verif_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        veri_btn.setOnClickListener {
            getresp()
        }

    }//onViewCreated


    //check responce of the key after login
    internal fun getresp() {

        //KEY is a cont from
        val service = retrofit.create(ApiService::class.java)

        //TODO  : pass sign_in_number.text
        //  to this fragment(VerifSigninFragment)
        val call = service.getcode(KEY, "966555555555")

        call.enqueue(object : retrofit2.Callback<authModel> {

            override fun onFailure(call: Call<authModel>, t: Throwable) {
                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<authModel>, response: Response<authModel>) {
                Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
            }
        })//enqueue

    }

}