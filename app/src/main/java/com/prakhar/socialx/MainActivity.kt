package com.prakhar.socialx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var myAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchData()
    }

    //Dummy data
    private fun fetchData(){
        val news: Call<News> = NewsService.newsInstance.getHeadlines(country = "in", page = 1)
        news.enqueue(object : Callback<News?> {
            override fun onResponse(call: Call<News?>, response: Response<News?>) {
                val newsResponse: News? = response.body()
                if(newsResponse!=null){
                    Log.d("Response", newsResponse.toString())

                    myAdapter = NewsListAdapter(this@MainActivity, newsResponse.articles)
                    recyclerView.adapter = myAdapter
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(call: Call<News?>, t: Throwable) {
                Log.d("Error", "error in fetching news", t)
            }
        })
    }

}