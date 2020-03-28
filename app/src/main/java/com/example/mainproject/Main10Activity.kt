package com.example.mainproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.Client.MovieClient
import com.example.mainproject.adapter.movieadapter.SimilarMoviesAdapter
import com.example.mainproject.model.modelmovies.similarmovies.ResultsItem
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_main10.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Main10Activity : AppCompatActivity() {

    val list = arrayListOf<ResultsItem>()
    val similaradapter = SimilarMoviesAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main10)

        var movie_id = intent.getStringExtra("movieid")
//        val movieid = intent.getStringExtra("id")

        similarrView.apply {
            layoutManager = LinearLayoutManager(this@Main10Activity,RecyclerView.VERTICAL,false)
            adapter = similaradapter
        }
        similaradapter.onItemClick = {
            val i = Intent(this,Main12Activity::class.java)
            i.putExtra("ids",it.id.toString())
            startActivity(i)
            Toast.makeText(this,it.title+"\n"+it.id,Toast.LENGTH_SHORT).show()
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){ MovieClient.api.getMovieTrailers(movie_id.toString()) }

            if (response.isSuccessful){
                if (response.body()?.results.isNullOrEmpty()){
                    GlobalScope.launch(Dispatchers.Main) {
                        Toast.makeText(this@Main10Activity,"Trailer not available",Toast.LENGTH_LONG).show()
                    }
                }
                else if (movie_id == response.body()?.id.toString()){
                    Log.i("asddd",response.body()?.id.toString())
                    response.body()?.let { res->
                        res.results?.let { Log.i("asd",it.toString())
                            Log.i("asdd",movie_id)}
                        runOnUiThread{
                            lifecycle.addObserver(youtube_player_view)
                            youtube_player_view.addYouTubePlayerListener(object :
                                AbstractYouTubePlayerListener(){
                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                    val videoId = res.results?.get(0)?.key?.toString()
                                    youTubePlayer.loadVideo(videoId.toString(),0F)
                                    super.onReady(youTubePlayer)
                                }
                            })
                            tv1.text = "Similar Movies "
                        }
                    }
                }
            }
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){ MovieClient.api.getSimilarMovies(movie_id.toString()) }
            if (response.isSuccessful){
                response.body()?.let {res->
                    res.results?.let { list.addAll(it)
                        Log.i("asf",it.toString())
                    }
                    runOnUiThread{ similaradapter.notifyDataSetChanged() }
                }
            }
        }

    }
}
