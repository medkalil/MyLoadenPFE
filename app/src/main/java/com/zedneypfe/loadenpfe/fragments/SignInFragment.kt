package com.zedneypfe.loadenpfe.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.zedneypfe.loadenpfe.R
import kotlinx.android.synthetic.main.fragment_signin.*
import java.lang.Exception


class SignInFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_signin, container, false)

     /*   sign_in_btn.setOnClickListener {
            setFragment(SignInFragment())
        }*/

        return v
    }


    private fun setFragment(fragment: Fragment) {
        val transaction = requireFragmentManager().beginTransaction()
        transaction.replace(R.id.container_fragm, fragment)
        transaction.commit()
    }

}
