package com.example.a112208.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a112208.R
import com.example.a112208.adapters.MovieAdapter
import com.example.a112208.api.ApiClient
import com.example.a112208.api.ApiService
import com.example.a112208.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.URL

class FragmentHome : Fragment() {


    // 添加一個 lateinit 屬性，用於保存 RecyclerView 實例
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 找到 RecyclerView 並初始化
        recyclerView = view.findViewById(R.id.recyclerViewMovies)
        // 確保 recyclerView 不為 null 後再進行其他操作
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 初始化 movieAdapter
        movieAdapter = MovieAdapter(requireContext(), listOf())
        recyclerView.adapter = movieAdapter

        // 呼叫 Flask API 取得電影清單
        GlobalScope.launch(Dispatchers.Main) {
            val movieList = fetchMovieList()
            movieAdapter.setData(movieList) // 設置資料
        }

        return view
    }

    private suspend fun fetchMovieList(): List<Movie> = withContext(Dispatchers.IO) {
        val url = URL("http://140.131.114.157:5000/get-sorted-movie-list") // 替換成你的 Flask API 端點
        val jsonString = url.readText()
        val jsonArray = JSONArray(jsonString)

        val movieList = mutableListOf<Movie>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val movie = Movie(
                jsonObject.getInt("id"),
                jsonObject.getString("movie_name"),
                jsonObject.getString("movie_style"),
                jsonObject.getString("imageUrl")
            )
            movieList.add(movie)
        }

        movieList
    }
}