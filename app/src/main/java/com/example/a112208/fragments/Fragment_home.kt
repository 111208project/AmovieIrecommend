package com.example.a112208.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.a112208.R

class Fragment_home : Fragment() {
    private lateinit var recommendedContent: List<String>

    companion object {
        fun newInstance(recommendedContent: ArrayList<String>): Fragment_home {
            val fragment = Fragment_home()
            val args = Bundle()
            args.putStringArrayList("recommendedContent", recommendedContent)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recommendedContent = it.getStringArrayList("recommendedContent") ?: emptyList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recommendedContentTextView: TextView = view.findViewById(R.id.tvRecommendedContent)

        // 將推薦內容設定到 TextView 中
        val recommendedContentString = recommendedContent.joinToString(", ")
        recommendedContentTextView.text = "推薦內容：$recommendedContentString"

        return view
    }
}
