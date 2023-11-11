package com.example.a112208.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.a112208.R

class Fragment_home : Fragment() {
    private lateinit var tvRecommendedContent: TextView

    companion object {
        fun newInstance(userInterests: ArrayList<String>): Fragment_home {
            val fragment = Fragment_home()
            val args = Bundle()
            args.putStringArrayList("userInterests", userInterests)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        tvRecommendedContent = view.findViewById(R.id.tvRecommendedContent)

        // 获取传递过来的用户兴趣数据
        val userInterests = arguments?.getStringArrayList("userInterests")
        // TODO: 获取推荐内容数据（如果有的话）

        // 将用户兴趣数据和推荐内容数据设置到 TextView 中
        val interestsText = userInterests?.joinToString(", ") ?: "无兴趣数据"
        tvRecommendedContent.text = "用戶興趣：$interestsText"

        return view
    }
}
