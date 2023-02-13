package com.example.ejercicioapinoticias

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var lista: MutableList<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        newsRecyclerView = findViewById(R.id.rvMain)
        adapter = NewsAdapter()
        newsRecyclerView.adapter = adapter
        newsRecyclerView.layoutManager = LinearLayoutManager(this)

        textView = findViewById(R.id.tvNo)

        textView.visibility = View.VISIBLE
        textView.text = "Cargando noticias..."
        lista = mutableListOf()

        val api = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val newsApi = api.create(NewsApi::class.java)
        newsApi.getTopHeadlines("us", "0449fc9bb3fc43caa89f1b1064210789", 34)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {

                    val noticias = response.body()?.articles
                    if (noticias != null) {
                        lista.addAll(noticias)
                    }

                    if (response.isSuccessful) {
                        textView.visibility = View.GONE
                        adapter.updateNews(lista)

                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    // Maneja el error aquí
                }
            })
    }

}