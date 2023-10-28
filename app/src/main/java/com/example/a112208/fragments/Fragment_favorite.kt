package com.example.a112208.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a112208.R



class Fragment_favorite : Fragment() {
    companion object {
        fun newInstance(param1: String = "DefaultParam1"): Fragment_favorite {
            val fragment = Fragment_favorite()
            val args = Bundle()
            args.putString("param1", param1)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

}
