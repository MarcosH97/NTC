package com.mahostudios.ntc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class Usage : Fragment() {

    lateinit var info_btn : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v : View = inflater.inflate(R.layout.fragment_usage2, container, false)
//        info_btn = v.findViewById(R.id.info_btn)

        return v
    }


}