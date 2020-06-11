package com.zedneypfe.loadenpfe.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.zedneypfe.loadenpfe.Model.authModel
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.network.ApiService
import com.zedneypfe.loadenpfe.network.KEY
import com.zedneypfe.loadenpfe.network.retrofit
import kotlinx.android.synthetic.main.fragment_signin.*
import retrofit2.Call
import retrofit2.Response


class SignInFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signin, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (sign_in_number.text.isEmpty()) {
            sign_in_number?.error = "entre le phone number"

        }
        sign_in_btn?.setOnClickListener {
            getresp()

            setFragment(VerifSignInFragment())
        }

    }


    //requireFragmentManager -> for fragments
    private fun setFragment(fragment: Fragment) {
        val ft = requireFragmentManager().beginTransaction()
        ft.replace(R.id.container_fragm, fragment)
        ft.commit()
    }


    //check responce of the key after login
    internal fun getresp() {

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

    }

}
