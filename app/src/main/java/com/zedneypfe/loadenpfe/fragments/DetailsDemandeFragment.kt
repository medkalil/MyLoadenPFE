package com.zedneypfe.loadenpfe.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.databinding.FragmentDetailsdemandeBinding

class DetailsDemandeFragment:Fragment() {
    lateinit var binding: FragmentDetailsdemandeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailsdemande, container, false)
    }
}