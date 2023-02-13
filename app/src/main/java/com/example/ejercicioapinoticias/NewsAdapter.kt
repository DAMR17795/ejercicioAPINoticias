package com.example.ejercicioapinoticias

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter: RecyclerView.Adapter<NewsViewHolder>() {

    private val news = mutableListOf<News>()

    fun updateNews(newNews: List<News>) {
        news.clear()
        news.addAll(newNews)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_manus, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = news[position]
        holder.title.text = currentNews.title
        holder.description.text = currentNews.description
        // Puedes usar una biblioteca de carga de imágenes como Glide para cargar la imagen desde un URL
        // Glide.with(holder.image.context).load(currentNews.imageUrl).into(holder.image)
        Glide.with(holder.image.context)
            .load(currentNews.urlToImage)
            .into(holder.image)
    }

}