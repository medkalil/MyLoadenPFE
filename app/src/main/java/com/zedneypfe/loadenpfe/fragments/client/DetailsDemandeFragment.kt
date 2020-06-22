package com.zedneypfe.loadenpfe.fragments.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.databinding.FragmentDetailsdemandeBinding
import com.zedneypfe.loadenpfe.fragments.VerifSignInFragment

class DetailsDemandeFragment:Fragment() {

    var id_passed: String? = ""

    companion object {
        fun DetailsDemandeFragmentInstance(id:String): DetailsDemandeFragment {
            val instance = DetailsDemandeFragment()
            val bd = Bundle()
            bd.putString("id", id)
            instance.arguments = bd
            return instance
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailsdemande, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id_passed=arguments?.getString("id")

    }
}