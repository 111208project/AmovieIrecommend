package com.example.a112208.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a112208.MovieinfoActivity
import com.example.a112208.R
import com.example.a112208.data.Movie

class MovieAdapter(private val context: Context, private var movieList: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]

        // 設定影片名稱
        holder.textMovieName.text = movie.movie_name

        // 設定影片風格
        holder.textMovieStyle.text = movie.movie_style

        // 設定影片圖片（這裡使用 Glide 作為例子，你需要添加相應的庫）
        Glide.with(context)
            .load(movie.imageUrl)
            .placeholder(R.drawable.action) // 占位圖片
            .error(R.drawable.background2) // 錯誤圖片
            .into(holder.imageMovie)

        // 設置點擊事件
        holder.itemLayout.setOnClickListener {
            // 在這裡定義點擊事件的處理邏輯

            // 例如，你可以創建一個 Intent 來跳轉到另一個畫面
            val intent = Intent(context, MovieinfoActivity::class.java)

            // 在這裡你可以傳遞一些額外的資訊給 MovieInfoActivity
            // intent.putExtra("key", value)

            // 啟動新的畫面
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textMovieName: TextView = itemView.findViewById(R.id.textMovieName)
        val textMovieStyle: TextView = itemView.findViewById(R.id.textMovieStyle)
        val imageMovie: ImageView = itemView.findViewById(R.id.imageMovie)
        val itemLayout: LinearLayout = itemView.findViewById(R.id.itemLayout)

    }

    fun setData(newMovieList: List<Movie>) {
        movieList = newMovieList
        notifyDataSetChanged()
    }
}
