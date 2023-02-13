package com.example.ejercicioapinoticias

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.tvTitle)
    val description: TextView = itemView.findViewById(R.id.tvDescripcion)
    val image: ImageView = itemView.findViewById(R.id.ivMain)
}
