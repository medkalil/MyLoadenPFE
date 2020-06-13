package com.zedneypfe.loadenpfe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zedneypfe.loadenpfe.fragments.client.EnvoyerDemandeFragment
import com.zedneypfe.loadenpfe.storage.SharedPrefManager

class SignoutFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SharedPrefManager.getInstance(requireActivity().applicationContext).clear()

        setFragment(EnvoyerDemandeFragment())
        println(SharedPrefManager.getInstance(requireActivity().applicationContext).user)


    }//onViewCreated

    //requireFragmentManager -> for fragments
    private fun setFragment(fragment: Fragment) {
        val ft = requireFragmentManager().beginTransaction()
        ft.replace(R.id.container_fragm, fragment)
        ft.commit()
    }

}