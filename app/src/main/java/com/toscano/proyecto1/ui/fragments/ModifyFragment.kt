package com.toscano.proyecto1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.toscano.proyecto1.R
import com.toscano.proyecto1.databinding.FragmentModifyBinding


class ModifyFragment : Fragment() {

    private lateinit var binding: FragmentModifyBinding
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentModifyBinding.bind(inflater.inflate(R.layout.fragment_modify, container, false))
        return binding.root
    }
}